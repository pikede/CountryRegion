package com.example.countryregion.network

import com.example.countryregion.models.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): Response<List<CountryResponse>>
}