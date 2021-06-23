package com.will.bumblebeer.infraestructure

import android.app.Application
import com.will.bumblebeer.data.di.dataModule
import com.will.bumblebeer.domain.di.domainModule
import com.will.bumblebeer.ui.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BumblebeerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BumblebeerApplication)

            modules(domainModule + presentationModule + dataModule)
        }
    }
}