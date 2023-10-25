package com.argahutama.compose.presentation.view.page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.argahutama.compose.common.theme.util.showToast
import com.argahutama.compose.presentation.viewmodel.NowPlayingMovieListViewModel

@Composable
fun NowPlayingMovieListPage(viewModel: NowPlayingMovieListViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
        viewModel.errorMessage.collect { message ->
            if (!message.isNullOrEmpty()) {
                context.showToast(message)
            }
        }
    }

    LazyColumn {
        items(state.value.data, key = { it.id }) { movie ->
            Text(text = movie.title)
        }
    }
}