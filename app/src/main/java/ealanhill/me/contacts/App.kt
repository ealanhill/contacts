package ealanhill.me.contacts

import android.app.Application
import ealanhill.me.contacts.network.ApiModule
import ealanhill.me.contacts.network.ContactsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    companion object {
        lateinit var COMPONENT: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://s3.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        COMPONENT = DaggerAppComponent.builder()
                .apiModule(ApiModule(retrofit.create(ContactsApi::class.java)))
                .contextModule(ContextModule(this))
                .build()
    }
}