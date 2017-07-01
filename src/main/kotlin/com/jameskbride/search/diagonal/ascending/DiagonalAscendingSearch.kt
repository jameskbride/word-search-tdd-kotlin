package com.jameskbride.search.diagonal.ascending

import com.jameskbride.search.diagonal.DiagonalSearch

class DiagonalAscendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {
    fun execute(): List<String?> {
        val bottomCoordinateBuilder: DiagonalAscendingBottomCoordinateBuilder = DiagonalAscendingBottomCoordinateBuilder(puzzle)
        val bottomVectors: List<Vector> = bottomCoordinateBuilder.buildVectors(bottomCoordinateBuilder.buildBottomVectorCoordinates())

        val topCoordinateBuilder: DiagonalAscendingTopCoordinateBuilder = DiagonalAscendingTopCoordinateBuilder(puzzle)
        val topVectors: List<Vector> = topCoordinateBuilder.buildVectors(topCoordinateBuilder.buildTopVectorCoordinates())

        val wordCoordinates: List<WordCoordinates> = (
                bottomCoordinateBuilder.findWord(bottomVectors, word) +
                bottomCoordinateBuilder.findWord(bottomVectors, word, reversed = true) +
                topCoordinateBuilder.findWord(topVectors.minus(bottomVectors), word) +
                topCoordinateBuilder.findWord(topVectors.minus(bottomVectors), word, reversed = true)
            )

        val bottomCoordinates: List<String?> = wordCoordinates.map { (coords) ->
            val coordStrings = buildCoordinatesString(coords)
            "$word: $coordStrings"
        }

        return bottomCoordinates
    }

    private fun buildCoordinatesString(coords: List<Pair<Int, Int>>) = coords.map { "(${it.first},${it.second})" }.joinToString(",")
}


