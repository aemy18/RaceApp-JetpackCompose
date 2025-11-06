package com.practical.task.di

import com.practical.task.data.remote.ApiService
import com.practical.task.data.remote.KtorClient
import com.practical.task.data.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = KtorClient.httpClient

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): ApiService = ApiService(client)

    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepository =
        HomeRepository(apiService)
}