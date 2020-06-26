package com.mindorks.bootcamp.instagram.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mindorks.bootcamp.instagram.di.component.ActivityComponent
import com.mindorks.bootcamp.instagram.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {
    override fun provideLayoutId(): Int  = 0

    override fun injectDependencies(activityComponent: ActivityComponent) {

    }

    override fun setupView(savedInstanceState: Bundle?) {

    }
}