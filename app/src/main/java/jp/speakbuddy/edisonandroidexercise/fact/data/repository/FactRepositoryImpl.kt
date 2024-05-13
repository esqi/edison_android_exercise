package jp.speakbuddy.edisonandroidexercise.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FactRepositoryImpl @Inject constructor(
    private val factNetworkDataSource: FactNetworkDataSource,
    private val factLocalDataSource: FactLocalDataSource,
) : FactRepository {
    override fun getFact(): Flow<Fact> = factNetworkDataSource.fetchFact().onEach {
        factLocalDataSource.saveFact(it)
    }.catch { throwable ->
        val fact = factLocalDataSource.getCachedFact() ?: throw throwable
        emit(fact)
    }
}