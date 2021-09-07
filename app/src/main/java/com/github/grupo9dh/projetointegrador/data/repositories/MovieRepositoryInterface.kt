package com.github.grupo9dh.projetointegrador.data.repositories

import com.github.grupo9dh.projetointegrador.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {
    suspend fun listMovies(): Flow<List<Movie>>
}