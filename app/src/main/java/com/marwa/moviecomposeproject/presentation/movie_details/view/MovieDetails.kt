package com.marwa.moviecomposeproject.presentation.movie_details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marwa.moviecomposeproject.BuildConfig
import com.marwa.moviecomposeproject.R
import com.marwa.moviecomposeproject.core.di.ViewState
import com.marwa.moviecomposeproject.data.model.Cast
import com.marwa.moviecomposeproject.data.model.CastResponse
import com.marwa.moviecomposeproject.data.model.Movie
import com.marwa.moviecomposeproject.domain.entity.MovieEntity
import com.marwa.moviecomposeproject.presentation.common.AppBarIcon
import com.marwa.moviecomposeproject.presentation.common.MovieTag
import com.marwa.moviecomposeproject.presentation.common.Rating
import com.marwa.moviecomposeproject.presentation.common.Section
import com.marwa.moviecomposeproject.presentation.movie_details.viewmodel.MovieDetailsViewModel
import com.marwa.moviecomposeproject.presentation.movies.view.ShowError
import com.marwa.moviecomposeproject.presentation.movies.view.ShowLoading
import com.marwa.moviecomposeproject.ui.theme.AppTypography
import com.marwa.moviecomposeproject.ui.theme.savedBookmarkColor
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetailsScreen(movie: Movie) {
    val detailsViewModel = getViewModel<MovieDetailsViewModel>()
    detailsViewModel.getMovieCast(movie.id)
    Box {

        ViewImage(movie.backdropPath)
        MovieInfo(movie, detailsViewModel)

    }
}

@Composable
fun ViewImage(backdropPath: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(310.dp)
    ) {
        AsyncImage(
            model = BuildConfig.IMG_URL + backdropPath,
            contentDescription = "movie details",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop

        )
        Box(modifier = Modifier.align(Alignment.Center)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AppBarIcon(
                    modifier = Modifier
                        .size(45.dp),
                    icon = painterResource(R.drawable.button_play),
                    description = "play"
                )
                Spacer(Modifier.height(8.dp))
                Text("Play Trailer", style = AppTypography.playTrailer)
            }
        }
    }
}

@Composable
fun MovieInfo(movie: Movie, detailsViewModel: MovieDetailsViewModel) {
    var iconColor by remember { mutableStateOf(Color.Black) }

    Box(
        modifier = Modifier
            .padding(top = 250.dp)
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = movie.title,
                    style = AppTypography.movieTitleDetails,
                    overflow = TextOverflow.Visible,
                    maxLines = 2,
                    modifier = Modifier.weight(0.70f)
                )
                Spacer(modifier = Modifier.weight(0.20f))
                AppBarIcon(
                    modifier = Modifier
                        .weight(0.10f)
                        .clickable {
                            iconColor = savedBookmarkColor
                            detailsViewModel.saveMovie(
                                MovieEntity(
                                    id = null,
                                    movie.title,
                                    movie.posterPath,
                                    movie.backdropPath, movie.overview,
                                ),

                                )
                        },
                    iconColor = ColorFilter.tint(iconColor),
                    icon = painterResource(R.drawable.bookmark),
                    description = "bookmark",
                )
            }
            Spacer(Modifier.height(8.dp))
            Rating(movie)
            Spacer(Modifier.height(16.dp))
            MovieTag(movie)
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                MovieData("Release Date", movie.releaseDate)
                MovieData("Language", movie.language)
                MovieData("Rating", "${movie.voteAverage}")
            }
            Spacer(Modifier.height(24.dp))
            Description(movie)
            CastSection(detailsViewModel)
        }

    }
}

@Composable
fun MovieData(label: String, value: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(label, style = AppTypography.ratingStyle)
        Spacer(Modifier.height(4.dp))
        Text(value, style = AppTypography.dateStyle)
    }
}

@Composable
fun Description(movie: Movie) {
    Column {
        Section("Description", false)
        Spacer(Modifier.height(8.dp))
        Text(movie.overview, minLines = 1, style = AppTypography.overviewText)
    }

}


@Composable
fun CastSection(detailsViewModel: MovieDetailsViewModel) {
    Column {
        Section("Cast", true)
        Spacer(Modifier.height(16.dp))
        val movieCastState by detailsViewModel.movieCast.collectAsState()
        println(movieCastState.data?.results?.size)
        when (movieCastState) {
            is ViewState.Loaded -> {
                movieCastState.data?.let {
                    CastContent(
                        it,
                    )
                }
            }

            is ViewState.Loading -> {
                ShowLoading()
            }

            is ViewState.Error -> {
                ShowError("Unable to movie cast")
            }
        }
    }
}

@Composable
fun CastContent(castResponse: CastResponse) {
    LazyRow {
        items(castResponse.results) { cast ->
            CastItem(cast)
        }
    }
}

@Composable
fun CastItem(cast: Cast) {
    Column(modifier = Modifier.padding(end = 8.dp)) {
        AsyncImage(
            model = if (cast.profilePath != null) BuildConfig.IMG_URL + cast.profilePath else "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
            contentDescription = "movie actor",
            modifier = Modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop


        )
        Spacer(Modifier.height(8.dp))
        Text(
            cast.name,
            modifier = Modifier.width(80.dp),
            style = AppTypography.movieTitle,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOverlappedCornerBox() {
    MaterialTheme {
    }
}
