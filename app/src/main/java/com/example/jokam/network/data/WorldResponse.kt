package com.example.jokam.network.data

import com.google.gson.annotations.SerializedName

data class WorldResponse(

	@field:SerializedName("WorldResponse")
	val worldResponse: List<WorldResponseItem?>? = null
)

data class WorldResponseItem(

	@field:SerializedName("Last Update")
	val lastUpdate: String? = null,

	@field:SerializedName("New Deaths_text")
	val newDeathsText: String? = null,

	@field:SerializedName("Active Cases_text")
	val activeCasesText: String? = null,

	@field:SerializedName("Total Deaths_text")
	val totalDeathsText: String? = null,

	@field:SerializedName("New Cases_text")
	val newCasesText: String? = null,

	@field:SerializedName("Total Recovered_text")
	val totalRecoveredText: String? = null,

	@field:SerializedName("Total Cases_text")
	val totalCasesText: String? = null,

	@field:SerializedName("Country_text")
	val countryText: String? = null
)
