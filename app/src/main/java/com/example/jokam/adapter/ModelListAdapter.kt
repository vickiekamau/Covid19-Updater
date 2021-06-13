package com.example.jokam.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jokam.R
import com.example.jokam.network.data.WorldResponseItem
import com.example.jokam.support.InputValidator


open class ModelListAdapter(var list: List<WorldResponseItem>?) : RecyclerView.Adapter<ModelListAdapter.MyViewHolder>(), Filterable {
    private var data:List<WorldResponseItem>? = list

    var countryFilterList = ArrayList<WorldResponseItem>()
    /*init {
        countryFilterList = list as ArrayList<WorldResponseItem>?
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view)
    }



    override fun getItemCount(): Int {
        var listCount = list?.size
        Log.d("List Size", listCount.toString())
        return list!!.size

    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val validator = InputValidator()
        holder.country.text = list!!.get(position).countryText
        holder.totalConfirmed.text = list!!.get(position).totalCasesText
        holder.totalRecovered.text = list!!.get(position).totalRecoveredText
        holder.totalDeaths.text = list!!.get(position).totalDeathsText
        holder.dateUpdated.text = list!!.get(position).lastUpdate


    }
    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item){
         val country: TextView = item.findViewById(R.id.txtCountryName)
         val totalConfirmed : TextView = item.findViewById(R.id.txtConfirmCases)
         val totalRecovered: TextView = item.findViewById(R.id.txtRecovered)
         val totalDeaths:TextView = item.findViewById(R.id.txtTotalDeaths)
         val dateUpdated:TextView = item.findViewById(R.id.dateUpdated)

    }

    override fun getFilter(): Filter? {
        return object : Filter() {
             override fun performFiltering(charSequence: CharSequence): FilterResults? {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    countryFilterList = list as ArrayList<WorldResponseItem>
                } else {
                    //val filteredList: List<WorldResponseItem> = ArrayList()
                    val filteredList = ArrayList<WorldResponseItem>()
                    for (row in list!!) {
                        //change this to filter according to your case
                        if (row.countryText?.toLowerCase()!!.contains(charString.toLowerCase())) {
                            filteredList.add(row)
                            Log.d("Country", row.toString())

                        }
                    }
                    countryFilterList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

             override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                countryFilterList = filterResults?.values as ArrayList<WorldResponseItem>
                notifyDataSetChanged()
            }
        }
    }

}