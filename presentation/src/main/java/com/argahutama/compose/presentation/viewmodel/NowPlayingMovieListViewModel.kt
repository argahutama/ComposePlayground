package com.argahutama.compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argahutama.compose.domain.entity.MovieEntity
import com.argahutama.compose.domain.usecase.GetNowPlayingMoviesUseCase
import com.argahutama.compose.domain.util.ResourceState
import com.argahutama.compose.presentation.view.state.PopularMovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieListViewModel @Inject constructor(
    private val nowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {
    private var query = ""

    private val originalList = mutableListOf<MovieEntity>()

    private val _state = MutableStateFlow(PopularMovieListState())
    val state = _state.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asSharedFlow()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            when (val result = nowPlayingMoviesUseCase()) {
                is ResourceState.Success -> {
                    originalList.clear()
                    originalList.addAll(result.data)
                    updateFilteredListState(query)
                }

                is ResourceState.Error -> {
                    val message = result.message.orEmpty()
                    _state.update { it.copy(isError = true) }
                    _errorMessage.emit(message)
                }
            }

            _state.update { it.copy(isLoading = false) }
        }
    }

    fun updateFilteredListState(query: String) {
        this.query = query
        _state.update {
            it.copy(data = originalList.filter(::getFilterPredicate))
        }
    }

    private fun getFilterPredicate(movieEntity: MovieEntity): Boolean {
        val extendedString = movieEntity.title + movieEntity.originalTitle + movieEntity.overview
        return extendedString.contains(query, ignoreCase = true)
    }
}