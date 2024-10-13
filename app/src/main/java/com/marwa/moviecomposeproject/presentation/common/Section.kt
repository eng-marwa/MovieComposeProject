package com.marwa.moviecomposeproject.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.marwa.moviecomposeproject.presentation.movies.view.SeeMore
import com.marwa.moviecomposeproject.ui.theme.AppTypography

@Composable
fun Section(sectionTitle: String, seeMore: Boolean = false) {
    Column {
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = sectionTitle,
                textAlign = TextAlign.Start,
                style = AppTypography.appTitle
            )
            if (seeMore) SeeMore()
        }
    }
}
