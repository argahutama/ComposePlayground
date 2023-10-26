package com.argahutama.compose.presentation.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthorInfoRow(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    occupation: String,
    company: String,
    city: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = email,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "$occupation @$company",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = city,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}