package jp.speakbuddy.edisonandroidexercise.data.local

import jp.speakbuddy.edisonandroidexercise.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.data.proto.FactLocalData

data class FactLocal(private val factLocalData: FactLocalData) : Fact {
    override val fact: String by factLocalData::fact
}