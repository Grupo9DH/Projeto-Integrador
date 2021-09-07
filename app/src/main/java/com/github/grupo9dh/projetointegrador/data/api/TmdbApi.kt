package com.github.grupo9dh.projetointegrador.data.api

import com.github.grupo9dh.projetointegrador.BuildConfig
import com.github.grupo9dh.projetointegrador.data.model.Movie
import com.github.grupo9dh.projetointegrador.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface TmdbApi {
    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&language=pt-BR&sort_by=popularity.desc")
    suspend fun listMovies(): MovieResponse
}