package com.mindorks.bootcamp.instagram.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mindorks.bootcamp.instagram.data.model.User
import com.mindorks.bootcamp.instagram.data.repository.UserRepository
import com.mindorks.bootcamp.instagram.utils.common.Event
import com.mindorks.bootcamp.instagram.utils.common.Resource
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import com.mindorks.bootcamp.instagram.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var loggingInObserver : Observer<Boolean>

    @Mock
    private lateinit var launchDummyObserver : Observer<Event<Map<String,String>>>

    @Mock
    private lateinit var messageStringIdObserver : Observer<Resource<Int>>

    private lateinit var testScheduler : TestScheduler

    private lateinit var loginViewModel : LoginViewModel

    @Before
    fun setUp()
    {
        val compositeDisposable : CompositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        loginViewModel = LoginViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            userRepository
        )
        loginViewModel.loggingIn.observeForever(loggingInObserver)
        loginViewModel.launchMain.observeForever(launchDummyObserver)
        loginViewModel.messageStringId.observeForever(messageStringIdObserver)

    }

    @Test
    fun givenServerResponse200_whenLogin_shouldLaunchDummyActivity(){
        val email = "test@gmail.com"
        val password= "password"
        val user = User("1",name = "test",email = email, accessToken = "accessToken")
        loginViewModel.emailField.value = email
        loginViewModel.passwordField.value = password
        doReturn(true).`when`(networkHelper).isNetworkConnected()
        doReturn(Single.just(user))
            .`when`(userRepository)
            .doUserLogin(email, password)
        loginViewModel.onLogin()
        testScheduler.triggerActions()
        verify(userRepository).saveCurrentUser(user)
        assert(loginViewModel.loggingIn.value == false)
        assert(loginViewModel.launchMain.value == Event(hashMapOf<String,String>()))
        verify(loggingInObserver).onChanged(true)
        verify(loggingInObserver).onChanged(false)
        verify(launchDummyObserver).onChanged(Event(hashMapOf()))
    }

    @After
    fun clear(){
        loginViewModel.loggingIn.removeObserver(loggingInObserver)
        loginViewModel.messageStringId.removeObserver(messageStringIdObserver)
        loginViewModel.launchMain.removeObserver(launchDummyObserver)
    }
}