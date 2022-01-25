package com.example.blank.data.remote.services
import com.example.blank.data.remote.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountriesService {
    @GET("/rest/v2/all")
    fun listCountries(): Call<List<Country>>
}