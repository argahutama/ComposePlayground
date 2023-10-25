package com.argahutama.compose.presentation.view.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.argahutama.compose.common.theme.util.formatDate
import com.argahutama.compose.common.theme.util.showToast
import com.argahutama.compose.presentation.view.component.MovieDetailHeader
import com.argahutama.compose.presentation.view.component.MovieDetailInfoRow
import com.argahutama.compose.presentation.viewmodel.MovieDetailsViewModel
import com.argahutama.compose.common.R as commonResource

@Composable
fun MovieDetailsPage(
    id: String,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData(id)
        viewModel.errorMessage.collect { message ->
            if (!message.isNullOrEmpty()) {
                context.showToast(message)
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        item {
            MovieDetailHeader(movie = state.value.data)
        }

        item {
            Text(
                text = stringResource(id = commonResource.string.overview_label),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                )
            )
            state.value.data?.overview?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                    )
                )
            }
        }

        item {
            Text(
                text = stringResource(id = commonResource.string.additional_information_label),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(12.dp)
            )
        }

        item {
            MovieDetailInfoRow(
                label = stringResource(id = commonResource.string.original_title_label),
                value = state.value.data?.originalTitle,
            )
        }

        item {
            MovieDetailInfoRow(
                label = stringResource(id = commonResource.string.release_date_label),
                value = state.value.data?.releaseDate?.formatDate(
                    from = "yyyy-MM-dd",
                    to = "dd MMMM yyyy",
                ),
            )
        }

        item {
            MovieDetailInfoRow(
                label = stringResource(id = commonResource.string.popularity_label),
                value = state.value.data?.popularity?.toInt()?.toString(),
            )
        }

        item {
            MovieDetailInfoRow(
                label = stringResource(id = commonResource.string.vote_count_label),
                value = state.value.data?.voteCount?.toInt()?.toString(),
            )
        }

        item {
            MovieDetailInfoRow(
                label = stringResource(id = commonResource.string.vote_average_label),
                value = state.value.data?.voteAverage?.toInt()?.toString(),
            )
        }

        item {
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}