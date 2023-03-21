package com.example.countryregion.repository

import android.util.Log
import com.example.countryregion.models.CountryResponse
import com.example.countryregion.network.NetworkService
import com.example.countryregion.getErrorMessage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response

class Repository {
    private val api by lazy { NetworkService().api }

    suspend fun getAllCountries(): Response<List<CountryResponse>> {
        return try {
            api.getCountries()
        } catch (e: Exception) {
            Log.e("**logged at Repository", e.getErrorMessage())
            Response.error<List<CountryResponse>>(
                400, ResponseBody.create(
                    e.getErrorMessage().toMediaType(),
                    "response"
                )
            )
        }
    }
}