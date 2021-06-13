package com.example.jokam.network.data

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.ParseException

data class ResponseCovid19(

	@field:SerializedName("continent")
	val continent: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("recoveredPerOneMillion")
	val recoveredPerOneMillion: Double? = null,

	@field:SerializedName("cases")
	val cases: Int? = null,

	@field:SerializedName("critical")
	val critical: Int? = null,

	@field:SerializedName("oneCasePerPeople")
	val oneCasePerPeople: Int? = null,

	@field:SerializedName("active")
	val active: Int? = null,

	@field:SerializedName("testsPerOneMillion")
	val testsPerOneMillion: Int? = null,

	@field:SerializedName("population")
	val population: Int? = null,

	@field:SerializedName("undefined")
	val undefined: Int? = null,

	@field:SerializedName("oneDeathPerPeople")
	val oneDeathPerPeople: Int? = null,

	@field:SerializedName("recovered")
	val recovered: Int? = null,

	@field:SerializedName("oneTestPerPeople")
	val oneTestPerPeople: Int? = null,

	@field:SerializedName("tests")
	val tests: Int? = null,

	@field:SerializedName("criticalPerOneMillion")
	val criticalPerOneMillion: Double? = null,

	@field:SerializedName("deathsPerOneMillion")
	val deathsPerOneMillion: Int? = null,

	@field:SerializedName("todayRecovered")
	val todayRecovered: Int? = null,

	@field:SerializedName("casesPerOneMillion")
	val casesPerOneMillion: Int? = null,

	@field:SerializedName("countryInfo")
	val countryInfo: CountryInfo? = null,

	@field:SerializedName("updated")
	val updated: Long? = null,

	@field:SerializedName("deaths")
	val deaths: Int? = null,

	@field:SerializedName("activePerOneMillion")
	val activePerOneMillion: Double? = null,

	@field:SerializedName("todayCases")
	val todayCases: Int? = null,

	@field:SerializedName("todayDeaths")
	val todayDeaths: Int? = null
) {
	@SuppressLint("SimpleDateFormat")
	fun formatDate(): String {
		var str: String = ""
		try {
			val sdf = java.text.SimpleDateFormat("yyyy-MM-dd")
			val date = updated?.let { java.util.Date(it.times(1000)) }
			str = sdf.format(date)
		} catch (e: ParseException) {
			e.printStackTrace();
		}
		return str
	}
}

data class CountryInfo(

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("_id")
	val id: Int? = null,

	@field:SerializedName("iso2")
	val iso2: String? = null,

	@field:SerializedName("lat")
	val lat: Int? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Int? = null,

	@field:SerializedName("iso3")
	val iso3: String? = null
)
