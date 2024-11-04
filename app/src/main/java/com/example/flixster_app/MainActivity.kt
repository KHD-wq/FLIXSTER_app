package com.example.flixster_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private val apiKey = "6c4e104af64b2de07bd1f740e3c2de4b"  // Replace with your actual TMDb API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.movie_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchMovies(recyclerView)
    }

    private fun fetchMovies(recyclerView: RecyclerView) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies(apiKey)
                withContext(Dispatchers.Main) {
                    recyclerView.adapter = MovieAdapter(response.results)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Failed to load movies: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

