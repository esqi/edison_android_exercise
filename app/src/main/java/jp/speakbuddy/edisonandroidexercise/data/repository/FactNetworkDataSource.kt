package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.data.model.Fact

interface FactNetworkDataSource {
    suspend fun fetchFact(): Result<Fact>
}