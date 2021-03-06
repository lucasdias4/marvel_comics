package com.lucasdias.marvelcomics

import android.app.Application
import com.lucasdias.base.di.baseModule
import com.lucasdias.core.di.coreModule
import com.lucasdias.data.di.dataModule
import com.lucasdias.domain.di.domainModule
import com.lucasdias.extensions.BuildConfig
import com.lucasdias.feature_comic.di.comicModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            logger(setupDependencyInjectionLogger())
            modules(
                listOf(
                    baseModule,
                    comicModule,
                    coreModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }

    private fun setupDependencyInjectionLogger(): Logger =
        if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
