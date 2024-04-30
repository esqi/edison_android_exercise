package jp.speakbuddy.edisonandroidexercise.data.network

import jp.speakbuddy.edisonandroidexercise.data.model.Fact
import kotlinx.serialization.Serializable

@Serializable
data class FactResponse(override val fact: String, val length: Int) : Fact