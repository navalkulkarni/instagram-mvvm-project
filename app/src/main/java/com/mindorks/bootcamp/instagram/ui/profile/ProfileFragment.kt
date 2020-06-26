package com.mindorks.bootcamp.instagram.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mindorks.bootcamp.instagram.R
import com.mindorks.bootcamp.instagram.di.component.FragmentComponent
import com.mindorks.bootcamp.instagram.ui.base.BaseFragment
import com.mindorks.bootcamp.instagram.ui.home.HomeFragment
import com.mindorks.bootcamp.instagram.ui.home.HomeViewModel


class ProfileFragment : BaseFragment<ProfileViewModel>() {

    companion object {
        const val TAG = "ProfileFragment"

        fun newInstance() : ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int  = R.layout.fragment_photo

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()
    }

    override fun setupView(view: View) {

    }

}