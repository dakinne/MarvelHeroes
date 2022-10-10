package com.noox.marvelheroes.core

import com.noox.marvelheroes.BuildConfig
import com.noox.marvelheroes.character.di.characterModule
import com.noox.marvelheroes.comic.di.comicModule
import com.noox.marvelheroes.core.di.coreModule
import com.noox.marvelheroes.event.di.eventModule
import com.noox.marvelheroes.serie.di.serieModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application: android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Application)
            modules(
                coreModule,
                comicModule,
                serieModule,
                eventModule,
                characterModule
            )
        }
    }
}
