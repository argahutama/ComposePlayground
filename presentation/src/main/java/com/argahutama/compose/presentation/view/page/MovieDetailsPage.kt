package com.argahutama.compose.presentation.view.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.argahutama.compose.common.theme.util.showToast
import com.argahutama.compose.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsPage(viewModel: MovieDetailsViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = Unit) {
        viewModel.errorMessage.collect { message ->
            if (!message.isNullOrEmpty()) {
                context.showToast(message)
            }
        }
    }
}