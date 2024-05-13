package jp.speakbuddy.edisonandroidexercise.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact

interface FactLocalDataSource {
    suspend fun saveFact(fact: Fact)
    suspend fun getCachedFact(): Fact?
}