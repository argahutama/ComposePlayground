package com.argahutama.compose.domain.usecase

import com.argahutama.compose.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(id: String) = repository.getMovieDetails(id)
}