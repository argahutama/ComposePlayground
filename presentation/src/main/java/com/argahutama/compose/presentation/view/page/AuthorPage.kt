package com.argahutama.compose.presentation.view.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.argahutama.compose.common.R
import com.argahutama.compose.presentation.view.component.AuthorInfoRow
import com.argahutama.compose.presentation.viewmodel.AuthorViewModel

@Composable
fun AuthorPage(
    modifier: Modifier = Modifier,
    viewModel: AuthorViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Box {
            AsyncImage(
                model = state.value?.imageUrl.orEmpty(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall,
                text = stringResource(id = R.string.author_page_title),
            )
        }

        AuthorInfoRow(
            name = state.value?.name.orEmpty(),
            email = state.value?.email.orEmpty(),
            occupation = state.value?.occupation.orEmpty(),
            company = state.value?.company.orEmpty(),
            city = state.value?.city.orEmpty(),
        )

        Spacer(modifier = Modifier.height(36.dp))
    }
}