package com.marwa.moviecomposeproject.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.marwa.moviecomposeproject.data.model.Movie
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MovieNavType : NavType<Movie>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Movie? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): Movie {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun serializeAsValue(value: Movie): String {
        return Json.encodeToString(value)
    }
}
