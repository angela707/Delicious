package com.panini.delicious

import android.app.Application
import com.panini.core.di.coreDataModule
import com.panini.delicious.di.appModule
import com.panini.recipe_list.di.recipesModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class DeliciousApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@DeliciousApp)
            modules(
                appModule,
                coreDataModule,
                recipesModule
            )
        }
    }
}