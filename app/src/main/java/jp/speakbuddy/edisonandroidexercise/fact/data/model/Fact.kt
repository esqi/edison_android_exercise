package jp.speakbuddy.edisonandroidexercise.fact.data.model

interface Fact {
    val fact: String
}

val Fact.length
    get() = fact.length

val Fact.isShowLength
    get() = length > 100

val Fact.isMultipleCats
    get() = fact.windowed(3).count { it.equals("cat", true) } > 1

val Fact.containCats
    get() = fact.contains("cats")