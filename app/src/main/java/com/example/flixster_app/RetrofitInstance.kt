package com.example.flixster_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {
    @GET("movie/popular")  // Ensure the endpoint is correct
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val api: TMDbService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDbService::class.java)
    }
}

data class MovieResponse(val results: List<Movie>)
