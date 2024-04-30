package jp.speakbuddy.edisonandroidexercise.data.repository

import jp.speakbuddy.edisonandroidexercise.data.model.Fact

interface FactRepository {
    suspend fun getFact(): Fact?
}