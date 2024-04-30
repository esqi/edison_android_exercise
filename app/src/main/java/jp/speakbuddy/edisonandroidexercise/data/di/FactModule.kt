package jp.speakbuddy.edisonandroidexercise.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.data.local.FactLocalDataSourceImpl
import jp.speakbuddy.edisonandroidexercise.data.network.FactNetworkDataSourceImpl
import jp.speakbuddy.edisonandroidexercise.data.repository.FactLocalDataSource
import jp.speakbuddy.edisonandroidexercise.data.repository.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.data.repository.FactRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class FactModule {
    @Binds
    abstract fun bindFactNetworkDataSource(factNetworkDataSourceImpl: FactNetworkDataSourceImpl): FactNetworkDataSource

    @Binds
    abstract fun bindFactLocalDataSource(factLocalDataSourceImpl: FactLocalDataSourceImpl): FactLocalDataSource

    @Binds
    abstract fun bindFactRepository(factRepositoryImpl: FactRepositoryImpl): FactRepository
}