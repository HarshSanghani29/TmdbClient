package com.anushka.tmdbclient.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.anushka.tmdbclient.R
import com.anushka.tmdbclient.databinding.ActivityMainBinding
import com.anushka.tmdbclient.presentation.artist.ArtistActivity
import com.anushka.tmdbclient.presentation.movie.MovieActivity
import com.anushka.tmdbclient.presentation.tv.TvShowActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.movieButton.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }
        binding.artistsButton.setOnClickListener {
            val intent = Intent(this, ArtistActivity::class.java)
            startActivity(intent)
        }
        binding.tvButton.setOnClickListener {
            val intent = Intent(this, TvShowActivity::class.java)
            startActivity(intent)
        }
    }
}