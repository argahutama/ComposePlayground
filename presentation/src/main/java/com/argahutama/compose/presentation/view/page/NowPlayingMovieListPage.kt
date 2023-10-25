package com.argahutama.compose.presentation.view.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.argahutama.compose.common.R
import com.argahutama.compose.common.theme.component.SearchView
import com.argahutama.compose.common.theme.util.showToast
import com.argahutama.compose.presentation.view.component.MovieCardItem
import com.argahutama.compose.presentation.view.component.MovieCardItemShimmer
import com.argahutama.compose.presentation.viewmodel.NowPlayingMovieListViewModel

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun NowPlayingMovieListPage(
    viewModel: NowPlayingMovieListViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToAuthor: () -> Unit,
) {
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

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineSmall,
                    text = stringResource(id = R.string.app_name),
                )
                IconButton(
                    onClick = navigateToAuthor,
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_baseline_person_24),
                        stringResource(id = R.string.navigate_to_author_label),
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
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
                    MovieCardItem(
                        movie = movie,
                        navigateToDetail = navigateToDetail,
                    )
                }
            }
        }
    }
}