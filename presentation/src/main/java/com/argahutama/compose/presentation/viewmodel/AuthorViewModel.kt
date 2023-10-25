package com.argahutama.compose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.argahutama.compose.domain.entity.AuthorEntity
import com.argahutama.compose.domain.usecase.GetAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel @Inject constructor(
    private val getAuthorUseCase: GetAuthorUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<AuthorEntity?>(null)
    val state = _state.asStateFlow()

    fun fetchData() {
        _state.update { getAuthorUseCase() }
    }
}