package jp.speakbuddy.edisonandroidexercise.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact

interface FactRepository {
    suspend fun getFact(): Fact?
}