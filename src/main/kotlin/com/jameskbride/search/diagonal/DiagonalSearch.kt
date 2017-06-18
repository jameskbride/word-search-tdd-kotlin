package com.jameskbride.search.diagonal

open class DiagonalSearch {
    fun buildCoordinateString(word: String, coordinates: List<String?>) = coordinates.map { coords -> "$word: $coords" }

}