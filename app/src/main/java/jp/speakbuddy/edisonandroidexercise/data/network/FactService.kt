package jp.speakbuddy.edisonandroidexercise.data.network

import retrofit2.http.GET

interface FactService {
    @GET("fact")
    suspend fun getFact(): FactResponse
}