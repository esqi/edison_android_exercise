package jp.speakbuddy.edisonandroidexercise.fact.data.network

import retrofit2.http.GET

interface FactService {
    @GET("fact")
    suspend fun getFact(): FactResponse
}