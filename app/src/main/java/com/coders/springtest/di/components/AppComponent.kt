package com.coders.springtest.di.components

import com.coders.springtest.SpringMediaApplication
import com.coders.springtest.di.module.ActivityBuilderModule
import com.coders.springtest.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class
    ]
)

interface AppComponent : AndroidInjector<SpringMediaApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SpringMediaApplication): Builder
        fun build(): AppComponent
    }
}
