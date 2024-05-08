package jp.speakbuddy.edisonandroidexercise.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val factNetworkDataSource: FactNetworkDataSource,
    private val factLocalDataSource: FactLocalDataSource,
) : FactRepository {
    override suspend fun getFact(): Result<Fact> =
        factNetworkDataSource.fetchFact()
            .onSuccess {
                factLocalDataSource.saveFact(it)
            }
            .recoverCatching { throwable ->
                factLocalDataSource.getCachedFact() ?: throw throwable
            }
}