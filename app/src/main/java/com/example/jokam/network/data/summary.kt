package com.example.jokam.network.data

import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class Summary(

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("Countries")
	val countries: List<CountriesItem?>? = null,

	@field:SerializedName("ID")
	val iD: String? = null,

	@field:SerializedName("Global")
	val global: Global? = null,

	@field:SerializedName("Date")
	val date: String? = null
)

data class Premium(
	val any: Any? = null
)

data class Global(

	@field:SerializedName("NewRecovered")
	val newRecovered: Int? = null,

	@field:SerializedName("NewDeaths")
	val newDeaths: Int? = null,

	@field:SerializedName("TotalRecovered")
	val totalRecovered: Int? = null,

	@field:SerializedName("TotalConfirmed")
	val totalConfirmed: Int? = null,

	@field:SerializedName("NewConfirmed")
	val newConfirmed: Int? = null,

	@field:SerializedName("TotalDeaths")
	val totalDeaths: Int? = null,

	@field:SerializedName("Date")
	val date: String? = null
)

data class CountriesItem(

	@field:SerializedName("NewRecovered")
	val newRecovered: Int? = null,

	@field:SerializedName("NewDeaths")
	val newDeaths: Int? = null,

	@field:SerializedName("TotalRecovered")
	val totalRecovered: Int? = null,

	@field:SerializedName("TotalConfirmed")
	val totalConfirmed: Int? = null,

	@field:SerializedName("Country")
	val country: String? = null,

	@field:SerializedName("Premium")
	val premium: Premium? = null,

	@field:SerializedName("ID")
	val iD: String? = null,

	@field:SerializedName("CountryCode")
	val countryCode: String? = null,

	@field:SerializedName("Slug")
	val slug: String? = null,

	@field:SerializedName("NewConfirmed")
	val newConfirmed: Int? = null,

	@field:SerializedName("TotalDeaths")
	val totalDeaths: Int? = null,

	@field:SerializedName("Date")
	val date: String? = null
) {

	fun formatDate(): String {
		val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
		val outputPattern = "yyyy-MM-dd"
		var date1: Date
		var str: String = ""
		val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
		val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

		try {
			date1 = inputFormat.parse(date)
			str = outputFormat.format(date1)
		} catch (e: ParseException) {
			e.printStackTrace();
		}
		return str
	}
}
