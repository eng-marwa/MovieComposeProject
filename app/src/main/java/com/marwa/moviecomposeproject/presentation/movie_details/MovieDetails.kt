package com.marwa.moviecomposeproject.presentation.movie_details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marwa.moviecomposeproject.data.model.Cast
import com.marwa.moviecomposeproject.data.model.Movie
import com.marwa.moviecomposeproject.ui.theme.AppTypography

@Composable
fun MovieDetailsScreen(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ViewImage(backdropPath = movie.backdropPath)
        Spacer(modifier = Modifier.height(16.dp))
        MovieInfo(movie = movie)
        Spacer(modifier = Modifier.height(16.dp))
//        CastSection(cast = movie.cast)
    }
}

@Composable
fun ViewImage(backdropPath: String) {
    Box(Modifier.fillMaxWidth().height(300.dp)) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${backdropPath}",
            contentDescription = "movie poster",
        )
    }
}

@Composable
fun MovieInfo(movie: Movie) {
    Column {
        Text(
            text = movie.title,
//            style = AppTypography.h4,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = movie.releaseDate, style = AppTypography.movieTitle)
            Text(text = movie.language, style = AppTypography.movieTitle)
            Text(text = "${movie.voteAverage}", style = AppTypography.movieTitle)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "movie", style = AppTypography.movieTitle)
    }
}

@Composable
fun CastSection(cast: List<Cast>) {
    Column {
        Text(
            text = "Cast",
//            style = AppTypography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(10) { actor ->
                CastItem(cast[actor])
            }
        }
    }
}

@Composable
fun CastItem(cast: Cast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 16.dp)
    ) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/9Rj8l6gElLpRL7Kj17iZhrT5Zuw.jpg",
            contentDescription = "movie poster",
//            placeholder = painterResource(id = R.drawable.placeholder),
//            error = painterResource(id = R.drawable.error),
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
//        Text(text = actor.name, style = AppTypography.body2)
    }
}
