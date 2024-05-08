package jp.speakbuddy.edisonandroidexercise.fact.data.network

import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact
import kotlinx.serialization.Serializable

@Serializable
data class FactResponse(override val fact: String, val length: Int) : Fact