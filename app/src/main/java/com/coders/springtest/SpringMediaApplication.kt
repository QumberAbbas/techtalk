package com.coders.springtest

import com.coders.springtest.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * This is custom application class for initialization of the dagger components modules
 */
class SpringMediaApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}