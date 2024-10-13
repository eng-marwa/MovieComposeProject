package com.marwa.moviecomposeproject.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marwa.moviecomposeproject.data.model.Movie
import com.marwa.moviecomposeproject.ui.theme.tagBgColor
import com.marwa.moviecomposeproject.ui.theme.tagTextColor


@Composable
fun MovieTag(movie: Movie) {
    LazyRow {
        items(if (movie.genreIds.size >= 3) 3 else movie.genreIds.size) { genreId ->
            TagItem(modifier = Modifier, movie.genreIds[genreId])
        }
    }
}
@Composable
fun TagItem(modifier: Modifier, genreId: Int) {
    Text(
        text = "$genreId",
        modifier = modifier
            .padding(end = 8.dp)
            .border(
                width = 1.dp,
                color = tagBgColor,
                shape = RoundedCornerShape(16.dp) // Rounded corners
            )
            .background(
                color = tagBgColor,
                shape = RoundedCornerShape(16.dp) // Rounded corners
            )
            .padding(horizontal = 16.dp, vertical = 4.dp), color = tagTextColor
    )
}
