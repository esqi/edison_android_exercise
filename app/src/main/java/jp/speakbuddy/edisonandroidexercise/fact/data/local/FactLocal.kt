package jp.speakbuddy.edisonandroidexercise.fact.data.local

import jp.speakbuddy.edisonandroidexercise.fact.data.proto.FactLocalData
import jp.speakbuddy.edisonandroidexercise.fact.data.model.Fact

data class FactLocal(private val factLocalData: FactLocalData) : Fact {
    override val fact: String by factLocalData::fact
}