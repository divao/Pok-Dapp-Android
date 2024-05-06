package com.divao.domain.di

import javax.inject.Qualifier

@Qualifier
annotation class MainScheduler

@Qualifier
annotation class BackgroundScheduler