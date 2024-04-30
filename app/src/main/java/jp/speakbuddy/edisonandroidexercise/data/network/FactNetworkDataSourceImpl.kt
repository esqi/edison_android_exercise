package jp.speakbuddy.edisonandroidexercise.data.network

import jp.speakbuddy.edisonandroidexercise.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.data.repository.FactNetworkDataSource
import javax.inject.Inject

class FactNetworkDataSourceImpl @Inject constructor(private val factService: FactService) :
    FactNetworkDataSource {
    override suspend fun fetchFact(): Result<Fact> = runCatching { factService.getFact() }
}