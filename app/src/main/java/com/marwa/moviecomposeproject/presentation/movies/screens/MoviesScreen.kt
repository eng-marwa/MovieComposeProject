package com.marwa.moviecomposeproject.presentation.movies.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marwa.moviecomposeproject.R
import com.marwa.moviecomposeproject.core.di.ViewState
import com.marwa.moviecomposeproject.data.model.Movie
import com.marwa.moviecomposeproject.data.model.MovieResponse
import com.marwa.moviecomposeproject.presentation.movies.viewmodel.MovieViewModel
import com.marwa.moviecomposeproject.ui.theme.AppTypography
import com.marwa.moviecomposeproject.ui.theme.seeMoreColor
import com.marwa.moviecomposeproject.ui.theme.strokeColor
import com.marwa.moviecomposeproject.ui.theme.tagBgColor
import com.marwa.moviecomposeproject.ui.theme.tagTextColor
import org.koin.androidx.compose.getViewModel

@Composable
fun MoviesScreen(modifier: Modifier = Modifier, onItemClick: (Movie) -> Unit) {
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
            NowShowingMovies(viewModel, onItemClick = {
                onItemClick(it)
            })
        }
        item {
            PopularMovies(viewModel, onItemClick = {
                onItemClick(it)
            })
        }
    }
}

@Composable
fun showError() {

}

@Composable
fun ShowLoading() {

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
fun AppBarIcon(modifier: Modifier, icon: Painter, description: String) {
    Image(
        painter = icon,
        contentDescription = description,
        modifier = modifier
    )
}

@Composable
fun NowShowingMovies(viewModel: MovieViewModel, onItemClick: (Movie) -> Unit) {
    val nowShowingState by viewModel.nowShowingMovies.collectAsState()
    when (nowShowingState) {
        is ViewState.Loading -> {
            ShowLoading()
        }

        is ViewState.Error -> {
            showError()
        }

        is ViewState.Loaded -> {
            nowShowingState.data?.let {
                NowShowingMoviesContent(it, onItemClick = onItemClick)
            }

        }

        else -> {
            // Show empty
        }
    }
}

@Composable
fun NowShowingMoviesContent(nowShowingResponse: MovieResponse, onItemClick: (Movie) -> Unit) {

    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Section("Now Showing")
        Spacer(
            Modifier
                .height(16.dp)
        )
        LazyRow {
            items(nowShowingResponse.results) { it ->
                NowShowingMovieItem(it, onItemClick = {
                    onItemClick(it)
                })
            }
        }

    }
}


@Composable
fun NowShowingMovieItem(movie: Movie, onItemClick: (Movie) -> Unit) {
    Column(modifier = Modifier.padding(end = 16.dp)) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
            contentDescription = "movie poster",
//            placeholder = painterResource(id = R.drawable.placeholder),
//            error = painterResource(id = R.drawable.error),
            modifier = Modifier
                .width(143.dp)
                .height(212.dp)
                .clickable { onItemClick(movie) }

        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = movie.title,
            style = AppTypography.movieTitle,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            maxLines = 1, modifier = Modifier.width(143.dp)
        )
        Spacer(Modifier.height(8.dp))
        Rating(movie.voteAverage)
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
fun PopularMovies(viewModel: MovieViewModel, onItemClick: (Movie) -> Unit) {
    val popularState by viewModel.popularMovies.collectAsState()
    when (popularState) {
        is ViewState.Loading -> {
            ShowLoading()
        }

        is ViewState.Error -> {
            showError()
        }

        is ViewState.Loaded -> {
            popularState.data?.let {
                PopularMoviesContent(popularResponse = it, onItemClick = onItemClick)
            }

        }

        else -> {
            // Show empty
        }
    }

}

@Composable
fun PopularMoviesContent(popularResponse: MovieResponse, onItemClick: (Movie) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Section("Popular Movies")
        Column {
            popularResponse.results.forEach { it ->
                PopularMovieItem(it, onItemClick = {
                    onItemClick(it)
                })
            }
        }
    }
}

@Composable
fun Section(sectionTitle: String) {
    Column {
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = sectionTitle,
                textAlign = TextAlign.Start,
                style = AppTypography.appTitle
            )
            SeeMore()
        }
    }
}

@Composable
fun PopularMovieItem(movie: Movie, onItemClick: (Movie) -> Unit) {
    Spacer(Modifier.height(16.dp))
    Row {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
            contentDescription = "movie poster",
//            placeholder = painterResource(id = R.drawable.placeholder),
//            error = painterResource(id = R.drawable.error),
            modifier = Modifier
                .width(85.dp)
                .height(120.dp)
                .clickable { onItemClick(movie) }
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                movie.title,
                style = AppTypography.movieTitle,
                overflow = TextOverflow.Visible,
                minLines = 2, modifier = Modifier.width(117.dp)
            )
            Spacer(Modifier.height(8.dp))
            Rating(movie.voteAverage)
            Spacer(Modifier.height(8.dp))
            MovieTag()
            Spacer(Modifier.height(8.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "rating",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Spacer(Modifier.width(4.dp))
                Text("8.5", style = AppTypography.dateStyle)
            }

        }
    }
}

@Composable
fun MovieTag() {
    Row {
        TagItem(modifier = Modifier)
        TagItem(modifier = Modifier)
        TagItem(modifier = Modifier)
    }
}

@Composable
fun TagItem(modifier: Modifier) {
    Text(
        text = "See",
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

@Composable
fun Rating(voteAverage: Double) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "rating",
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text("$voteAverage", style = AppTypography.ratingStyle)
    }
}

