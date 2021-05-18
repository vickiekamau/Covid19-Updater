package com.example.jokam

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokam.adapter.ModelListAdapter
import com.example.jokam.databinding.ActivityDashboardBinding
import com.example.jokam.network.data.Country
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {
    private lateinit var activityDashboardBinding: ActivityDashboardBinding
    private lateinit var mprogressBar: ProgressBar
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        activityDashboardBinding  = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(activityDashboardBinding.root)
        setSupportActionBar(activityDashboardBinding.appBar.appToolbar)
        supportActionBar?.title = "Dashboard"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        mprogressBar = progressBar
        progressBar.visibility = View.VISIBLE

        val data = Observer<Country>{
            recyclerview.adapter = ModelListAdapter(it.Countries)
            progressBar.visibility = View.GONE
        }

        (viewModel as MyViewModel).callAPI().observe(this,data)
    }
}