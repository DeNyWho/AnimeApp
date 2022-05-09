package com.example.anibox.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AndroidKtorClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpKtorClient