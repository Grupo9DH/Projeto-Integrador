package com.github.grupo9dh.projetointegrador.domain

import com.github.grupo9dh.projetointegrador.data.model.Movie
import com.github.grupo9dh.projetointegrador.data.repositories.MovieRepositoryInterface
import com.github.grupo9dh.projetointegrador.util.UseCase
import kotlinx.coroutines.flow.Flow

class ListMoviesUseCase(
    private val repository: MovieRepositoryInterface
) : UseCase.NoParam<List<Movie>>() {
    override suspend fun execute(): Flow<List<Movie>> {
        return repository.listMovies()
    }
}