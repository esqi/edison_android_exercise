package jp.speakbuddy.edisonandroidexercise.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact

interface FactNetworkDataSource {
    suspend fun fetchFact(): Result<Fact>
}