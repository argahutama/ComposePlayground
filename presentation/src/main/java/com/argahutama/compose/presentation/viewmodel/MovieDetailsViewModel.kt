package com.argahutama.compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argahutama.compose.domain.usecase.GetMovieDetailsUseCase
import com.argahutama.compose.domain.util.ResourceState
import com.argahutama.compose.presentation.view.state.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asSharedFlow()

    fun fetchData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            when (val result = getMovieDetailsUseCase(id)) {
                is ResourceState.Success -> {
                    _state.update { it.copy(data = result.data) }
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
}