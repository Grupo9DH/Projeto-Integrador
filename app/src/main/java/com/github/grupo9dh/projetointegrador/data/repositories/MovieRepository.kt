package com.github.grupo9dh.projetointegrador.data.repositories

import com.github.grupo9dh.projetointegrador.data.api.TmdbApi
import com.github.grupo9dh.projetointegrador.data.model.Movie
import kotlinx.coroutines.flow.flow

class MovieRepository(private val tmdbApi: TmdbApi) : MovieRepositoryInterface {
    override suspend fun listMovies() = flow {
        val movieResponse = tmdbApi.listMovies()
        emit(movieResponse.movies)
    }
}