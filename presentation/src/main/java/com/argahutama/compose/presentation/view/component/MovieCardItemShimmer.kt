package com.argahutama.compose.presentation.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.argahutama.compose.domain.entity.MovieEntity
import com.valentinilk.shimmer.shimmer

@Composable
fun MovieCardItemShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.LightGray)
            .shimmer()
            .padding(bottom = 5.dp)
    ) {
        MovieCardItem(movie = MovieEntity()) {}
    }
}