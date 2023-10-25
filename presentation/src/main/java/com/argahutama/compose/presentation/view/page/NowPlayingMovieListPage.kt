package com.argahutama.compose.presentation.view.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.argahutama.compose.common.theme.component.SearchView
import com.argahutama.compose.common.theme.util.showToast
import com.argahutama.compose.presentation.view.component.MovieCardItem
import com.argahutama.compose.presentation.view.component.MovieCardItemShimmer
import com.argahutama.compose.presentation.viewmodel.NowPlayingMovieListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NowPlayingMovieListPage(viewModel: NowPlayingMovieListViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()
    val query = remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
        viewModel.errorMessage.collect { message ->
            if (!message.isNullOrEmpty()) {
                context.showToast(message)
            }
        }
    }

    LazyColumn {
        stickyHeader {
            Box(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                SearchView {
                    viewModel.updateFilteredListState(it)
                }
            }
        }

        if (state.value.isLoading) {
            items(5) {
                MovieCardItemShimmer()
            }
        } else {
            items(state.value.data, key = { it.id }) { movie ->
                MovieCardItem(movie = movie)
            }
        }
    }
}