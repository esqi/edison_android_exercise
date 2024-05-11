package jp.speakbuddy.edisonandroidexercise.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    fun getFact(): Flow<Fact>
}