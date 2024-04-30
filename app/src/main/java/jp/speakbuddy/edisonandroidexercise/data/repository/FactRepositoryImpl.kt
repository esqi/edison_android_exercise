package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.data.model.Fact
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val factNetworkDataSource: FactNetworkDataSource,
    private val factLocalDataSource: FactLocalDataSource,
) : FactRepository {
    override suspend fun getFact(): Fact? = factNetworkDataSource.fetchFact().fold(onSuccess = {
        factLocalDataSource.saveFact(it)
        it
    }) {
        factLocalDataSource.getCachedFact()
    }
}