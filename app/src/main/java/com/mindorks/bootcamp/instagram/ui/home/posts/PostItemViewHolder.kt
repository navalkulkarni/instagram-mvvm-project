package com.mindorks.bootcamp.instagram.ui.home.posts

import android.view.View
import android.view.ViewGroup
import com.mindorks.bootcamp.instagram.R
import com.mindorks.bootcamp.instagram.data.model.Post
import com.mindorks.bootcamp.instagram.di.component.ViewHolderComponent
import com.mindorks.bootcamp.instagram.ui.base.BaseItemViewHolder

class PostItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<Post, PostItemViewModel>(R.layout.item_view_post, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {

    }
}