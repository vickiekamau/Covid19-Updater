package com.example.jokam

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokam.adapter.ModelListAdapter
import com.example.jokam.databinding.ActivityDashboardBinding
import com.example.jokam.network.data.WorldResponseItem
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.*

class Dashboard : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var activityDashboardBinding: ActivityDashboardBinding
    private lateinit var mprogressBar: ProgressBar
    private lateinit var viewModel: ViewModel
    private lateinit var list: List<WorldResponseItem>
    var modelAdapter: ModelListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        activityDashboardBinding  = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(activityDashboardBinding.root)
        setSupportActionBar(activityDashboardBinding.appBar.appToolbar)
        supportActionBar?.title = "Dashboard"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modelAdapter = ModelListAdapter(getCountries())

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerview.adapter = modelAdapter

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        mprogressBar = progressBar
        progressBar.visibility = View.VISIBLE

        val data = Observer<List<WorldResponseItem>>{
            recyclerview.adapter = ModelListAdapter(it)
            progressBar.visibility = View.GONE
        }

        (viewModel as MyViewModel).callAPI().observe(this,data)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)

        val search: MenuItem? = menu?.findItem(R.id.nav_search)
        val searchView: SearchView = search?.actionView as SearchView

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            //modelAdapter?.filter?.filter(query)
            searchCountry(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
       // if(query != null){
         //  searchCountry(query)
        //}
        //modelAdapter?.filter?.filter(query)
        return true
    }

    private fun searchCountry(query:String) {
        //var list: List<CountriesItem>? = null
        //var list = List<CountriesItem>()
        val data = Observer<List<WorldResponseItem>>{
            ModelListAdapter(it).filter?.filter(query).also { recyclerview.adapter }
            progressBar.visibility = View.GONE
        }

        (viewModel as MyViewModel).callAPI().observe(this,data)

    }
    private fun getCountries(): List<WorldResponseItem> {
        val mdList = ArrayList<WorldResponseItem>()

        return mdList
    }


}



