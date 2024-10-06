package com.marwa.moviecomposeproject.presentation.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.marwa.moviecomposeproject.BuildConfig
import com.marwa.moviecomposeproject.data.model.MovieData

@Composable
fun MovieDetailsScreen(movie: MovieData) {
    println(movie.backdropPath)
    LazyColumn {
        item {
            ViewImage(movie.backdropPath)
//            MovieInfo()
//            CastSection()
        }
    }
}

@Composable
fun ViewImage(backdropPath: String) {
   Box(modifier = Modifier
       .fillMaxWidth()
       .height(300.dp)
       .background(color = Color.Red)){
       AsyncImage(
           model = BuildConfig.IMG_URL + backdropPath,
           contentDescription = "movie details",
           modifier = Modifier
               .width(85.dp)
               .height(120.dp)
       )
   }
}

@Composable
fun MovieInfo() {

}

@Composable
fun CastSection() {

}

@Composable
fun CastItem() {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewDetails() {
    ViewImage("https://image.tmdb.org/t/p/w500/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg")
}