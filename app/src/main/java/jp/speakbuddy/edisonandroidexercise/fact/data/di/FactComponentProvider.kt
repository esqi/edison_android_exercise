package jp.speakbuddy.edisonandroidexercise.fact.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.fact.data.network.FactService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object FactComponentProvider {

    @Provides
    fun provideFactService(retrofit: Retrofit): FactService =
        retrofit.create(FactService::class.java)
}