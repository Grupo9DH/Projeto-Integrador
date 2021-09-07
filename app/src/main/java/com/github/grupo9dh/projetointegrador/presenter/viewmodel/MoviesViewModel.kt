package com.github.grupo9dh.projetointegrador.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.grupo9dh.projetointegrador.data.model.Movie
import com.github.grupo9dh.projetointegrador.domain.ListMoviesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MoviesViewModel(private val listMoviesUseCase: ListMoviesUseCase) : ViewModel() {

    private val _movies = MutableLiveData<State>()
    val movies: LiveData<State> = _movies

    fun getMoviesList() {
        viewModelScope.launch {
            listMoviesUseCase()
                .onStart {
                    _movies.postValue(State.Loading)
                }
                .catch {
                    _movies.postValue(State.Error(it))
                }
                .collect {
                    _movies.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val moviesList: List<Movie>) : State()
        data class Error(val error: Throwable) : State()
    }
}