package jp.speakbuddy.edisonandroidexercise.fact.data.network

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.fact.data.repository.FactNetworkDataSource
import javax.inject.Inject

class FactNetworkDataSourceImpl @Inject constructor(private val factService: FactService) :
    FactNetworkDataSource {
    override suspend fun fetchFact(): Result<Fact> = runCatching { factService.getFact() }
}