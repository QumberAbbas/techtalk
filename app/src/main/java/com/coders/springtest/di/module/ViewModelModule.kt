package com.coders.springtest.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coders.springtest.di.annotations.ViewModelKey
import com.coders.springtest.di.factory.ViewModelFactory
import com.coders.springtest.feature_post.viewmodel.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    internal abstract fun postViewModel(viewModel: PostViewModel): ViewModel

}