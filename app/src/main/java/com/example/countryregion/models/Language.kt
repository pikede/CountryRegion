package com.example.countryregion.models

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("code") val code: String? = null,
    @SerializedName("name") val name: String? = null
)