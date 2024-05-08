package jp.speakbuddy.edisonandroidexercise.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
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