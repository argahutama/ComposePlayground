package com.argahutama.compose.domain.usecase

import com.argahutama.compose.domain.entity.AuthorEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAuthorUseCase @Inject constructor() {
    operator fun invoke() = AuthorEntity()
}