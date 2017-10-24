package ealanhill.me.contacts

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context
}