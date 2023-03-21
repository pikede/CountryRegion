package com.example.countryregion.models

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("capital") val capital: String? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("currency") val currency: Currency? = null,
    @SerializedName("flag") val flag: String? = null,
    @SerializedName("language") val language: Language? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("region") val region: String? = null
)
