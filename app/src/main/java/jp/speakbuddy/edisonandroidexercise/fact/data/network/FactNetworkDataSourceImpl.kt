package jp.speakbuddy.edisonandroidexercise.fact.data.network

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.fact.data.repository.FactNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FactNetworkDataSourceImpl @Inject constructor(private val factService: FactService) :
    FactNetworkDataSource {
    override fun fetchFact(): Flow<Fact> = flow { emit(factService.getFact()) }
}