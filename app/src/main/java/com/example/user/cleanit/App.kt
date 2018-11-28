package com.example.user.cleanit

import android.support.multidex.MultiDexApplication
import com.example.user.cleanit.di.cleanIt
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, cleanIt)
    }

    companion object {
        @JvmStatic var instance: App? = null
            private set
    }
}
