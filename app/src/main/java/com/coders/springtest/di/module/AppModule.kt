package com.coders.springtest.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.coders.springtest.SpringMediaApplication
import com.coders.springtest.db.AppDatabase
import com.coders.springtest.db.dao.PostDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: SpringMediaApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApplication(application: SpringMediaApplication): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }


    @Singleton
    @Provides
    fun provideDb(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app-db").build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(database: AppDatabase): PostDao {
        return database.postDao()
    }

}
