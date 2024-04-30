package jp.speakbuddy.edisonandroidexercise.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.data.network.FactService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object FactComponentProvider {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://catfact.ninja/"

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideRetrofit(@BaseUrl baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    fun provideFactService(retrofit: Retrofit): FactService =
        retrofit.create(FactService::class.java)
}