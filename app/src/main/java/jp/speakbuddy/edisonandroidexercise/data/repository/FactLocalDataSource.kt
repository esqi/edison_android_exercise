package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.data.model.Fact

interface FactLocalDataSource {
    suspend fun saveFact(fact: Fact)
    suspend fun getCachedFact(): Fact?
}