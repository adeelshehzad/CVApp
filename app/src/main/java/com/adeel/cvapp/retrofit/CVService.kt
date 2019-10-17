package com.adeel.cvapp.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface CVService {
    @GET("cv.json")
    fun getCVData(): Call<CVResponse>
}