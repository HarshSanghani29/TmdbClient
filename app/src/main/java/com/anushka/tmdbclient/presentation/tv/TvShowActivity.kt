package com.anushka.tmdbclient.presentation.tv

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityTvShowBinding
import com.anushka.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowBinding

    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_tv_show)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
        (application as Injector).createTvShowSubComponent()
            .inject(this)
        tvShowViewModel = ViewModelProvider(this, factory)
            .get(TvShowViewModel::class.java)
        val responseLiveData = tvShowViewModel.getTvShows()
        responseLiveData.observe(this, Observer {
            Log.i("MYTAG", it.toString())
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.tvShowRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TvShowAdapter()
        binding.tvShowRecyclerView.adapter = adapter
        displayPopularTvShows()
    }

    private fun displayPopularTvShows() {
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.getTvShows()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowProgressBar.visibility = View.GONE
            } else {
                binding.tvShowProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
//        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_update->{
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateTvShows(){
        binding.tvShowProgressBar.visibility = View.VISIBLE
        val response = tvShowViewModel.updateTvShows()
        response.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.tvShowProgressBar.visibility = View.GONE
            } else {
                binding.tvShowProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
            }
        })
    }
}