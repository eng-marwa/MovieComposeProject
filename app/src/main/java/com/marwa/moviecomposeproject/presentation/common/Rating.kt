package com.marwa.moviecomposeproject.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marwa.moviecomposeproject.R
import com.marwa.moviecomposeproject.data.model.Movie
import com.marwa.moviecomposeproject.ui.theme.AppTypography

@Composable
fun Rating(movie: Movie) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "rating",
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text("${movie.voteAverage}", style = AppTypography.ratingStyle)
        Spacer(Modifier.width(4.dp))
    }


}