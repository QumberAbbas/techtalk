package com.coders.springtest.di.module

import com.coders.springtest.di.scope.ActivityScope
import com.coders.springtest.feature_post.view.PostActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun postActivity(): PostActivity
}