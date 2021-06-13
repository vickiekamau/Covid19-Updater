package com.example.jokam.network.data

import android.annotation.SuppressLint
import java.text.ParseException

class GlobalData(
    var country: String,
    var todayCase: Int,
    var cases: Int,
    var todayDeaths: Int,
    var deaths: Int,
    var todayRecovered: Int,
    var recovered: Int,
    var Date: Long
) {
    @SuppressLint("SimpleDateFormat")
    fun formatDate(): String {
        var str: String = ""
        try {
            val sdf = java.text.SimpleDateFormat("yyyy-MM-dd")
            val date = java.util.Date(Date * 1000)
            str = sdf.format(date)
        } catch (e: ParseException) {
            e.printStackTrace();
        }
        return str
    }


    /**fun FormatDate(): String {
        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val outputPattern = "yyyy-MM-dd"
        var date: Date
        var str: String = ""
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

        try {
        date = inputFormat.parse(Date)
        str = outputFormat.format(date)
        } catch (e:ParseException) {
            e.printStackTrace();
        }
        return  str
    }*/
}