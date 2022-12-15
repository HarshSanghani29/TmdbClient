package com.anushka.tmdbclient.presentation.artist

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
import com.anushka.tmdbclient.databinding.ActivityArtistBinding
import com.anushka.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistBinding

    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_artist)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        (application as Injector).createArtistSubComponent()
            .inject(this)
        artistViewModel = ViewModelProvider(this, factory)
            .get(ArtistViewModel::class.java)
        val responseLiveData = artistViewModel.getArtists()
        responseLiveData.observe(this, Observer {
            Log.i("MYTAG", it.toString())
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter =  ArtistAdapter()
        binding.artistRecyclerView.adapter = adapter
        displayPopularArtists()
    }

    private fun displayPopularArtists() {
        binding.artistProgressBar.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.getArtists()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistProgressBar.visibility = View.GONE
            } else {
                binding.artistProgressBar.visibility = View.GONE
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
                updateArtists()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateArtists(){
        binding.artistProgressBar.visibility = View.VISIBLE
        val response = artistViewModel.updateArtists()
        response.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.artistProgressBar.visibility = View.GONE
            } else {
                binding.artistProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
            }
        })
    }

}