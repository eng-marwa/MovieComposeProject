package com.marwa.moviecomposeproject.presentation.movies.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marwa.moviecomposeproject.BuildConfig
import com.marwa.moviecomposeproject.R
import com.marwa.moviecomposeproject.core.di.ViewState
import com.marwa.moviecomposeproject.data.model.Movie
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.presentation.common.AppBarIcon
import com.marwa.moviecomposeproject.presentation.common.MovieTag
import com.marwa.moviecomposeproject.presentation.common.Rating
import com.marwa.moviecomposeproject.presentation.common.Section
import com.marwa.moviecomposeproject.presentation.movies.viewmodel.MovieViewModel
import com.marwa.moviecomposeproject.ui.theme.AppTypography
import com.marwa.moviecomposeproject.ui.theme.seeMoreColor
import com.marwa.moviecomposeproject.ui.theme.strokeColor
import org.koin.androidx.compose.getViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    onItemClick: (movie: Movie) -> Unit,
) {
    val viewModel: MovieViewModel = getViewModel()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            MovieAppBar()
        }
        item {
            NowShowingMovies(viewModel, onItemClick = { onItemClick(it) })
        }
        item {
            PopularMovies(viewModel, onItemClick = { onItemClick(it) })
        }
    }
}

@Composable
fun ShowError(error: String) {
    Text(
        text = error,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun ShowLoading() {
    Text(
        text = "Loading...",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun MovieAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        AppBarIcon(
            modifier = Modifier
                .weight(0.10f),
            icon = painterResource(R.drawable.menu),
            description = "Menu"
        )

        Text(
            text = "FilmKu",
            modifier = Modifier.weight(0.80f),
            textAlign = TextAlign.Center,
            style = AppTypography.appTitle
        )
        AppBarIcon(
            modifier = Modifier
                .weight(0.10f),
            icon = painterResource(R.drawable.notif),
            description = "Notification"
        )
    }
}


@Composable
fun NowShowingMovies(viewModel: MovieViewModel, onItemClick: (movie: Movie) -> Unit) {
    val nowShowingState by viewModel.nowShowingMovies.collectAsState()

    Column {
        Section("Now Showing", true)
        Spacer(modifier = Modifier.height(16.dp))
        when (nowShowingState) {
            is ViewState.Loaded -> {
                nowShowingState.data?.let {
                    NowShowingMoviesContent(
                        it,
                        onItemClick = onItemClick
                    )
                }
            }

            is ViewState.Loading -> {
                ShowLoading()
            }

            is ViewState.Error -> {
                ShowError("Unable to load movies")
            }
        }
    }
}

@Composable
fun NowShowingMoviesContent(
    movieResponse: MovieResponse,
    onItemClick: (movie: Movie) -> Unit
) {
    LazyRow {
        items(movieResponse.results) { movie ->
            NowShowingMovieItem(movie, onItemClick = { onItemClick(it) })
        }
    }

}

@Composable
fun NowShowingMovieItem(
    movie: Movie,
    onItemClick: (movie: Movie) -> Unit
) {
    Column(modifier = Modifier.padding(end = 16.dp)) {
        AsyncImage(
            model = BuildConfig.IMG_URL + movie.posterPath,
            contentDescription = "now showing movie",
            modifier = Modifier
                .width(143.dp)
                .height(212.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable { onItemClick(movie) }, contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = movie.title,
            style = AppTypography.movieTitle, overflow = TextOverflow.Ellipsis, maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Rating(movie)

    }

}


@Composable
fun SeeMore(modifier: Modifier = Modifier) {
    Text(
        text = "See more",
        modifier = modifier
            .border(
                width = 1.dp,
                color = strokeColor,
                shape = RoundedCornerShape(16.dp) // Rounded corners
            )
            .background(
                color = seeMoreColor,
                shape = RoundedCornerShape(16.dp) // Rounded corners
            )
            .padding(horizontal = 8.dp, vertical = 4.dp), color = strokeColor
    )


}

@Composable
fun PopularMovies(viewModel: MovieViewModel, onItemClick: (movie: Movie) -> Unit) {
    val popularState by viewModel.popularMovies.collectAsState()
    Column {
        Section("Popular Movies", true)
        when (popularState) {
            is ViewState.Loaded -> {
                popularState.data?.let {
                    PopularMoviesContent(
                        it,
                        viewModel,
                        onItemClick = onItemClick
                    )
                }
            }

            is ViewState.Loading -> {
                ShowLoading()
            }

            is ViewState.Error -> {
                ShowError("Unable to load movies")
            }
        }
    }

}

@Composable
fun PopularMoviesContent(
    movieResponse: MovieResponse,
    viewModel: MovieViewModel,
    onItemClick: (movie: Movie) -> Unit
) {
    Column {
        movieResponse.results.forEach { movie ->
            PopularMovieItem(movie, onItemClick = { onItemClick(it) })
        }
    }
}


@Composable
fun PopularMovieItem(movie: Movie, onItemClick: (movie: Movie) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        AsyncImage(
            model = BuildConfig.IMG_URL + movie.posterPath,
            contentDescription = "popular movie",
            modifier = Modifier
                .width(85.dp)
                .height(120.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable { onItemClick(movie) }, contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = movie.title,
                style = AppTypography.movieTitle,
                overflow = TextOverflow.Visible,
                maxLines = 2,
                modifier = Modifier.width(117.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Rating(movie)


            Spacer(modifier = Modifier.height(8.dp))
            MovieTag(movie)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "rating",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(movie.releaseDate, style = AppTypography.dateStyle)
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MovieScreenPreview() {
    // NowShowingMovies()
}