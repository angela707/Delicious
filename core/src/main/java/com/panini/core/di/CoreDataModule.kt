package com.panini.core.di

import com.panini.core.data.database.DatabaseFactory
import com.panini.core.data.database.DatabaseFactoryImpl
import com.panini.core.data.networking.HttpClientFactory
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }

    single<DatabaseFactory> {
        DatabaseFactoryImpl(context = get())
    }
}