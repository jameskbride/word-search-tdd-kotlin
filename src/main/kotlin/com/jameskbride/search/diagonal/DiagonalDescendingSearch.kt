package com.jameskbride.search.diagonal

class DiagonalDescendingSearch(val word: String, val puzzle: Array<Array<String>>) {

    fun execute(): List<String?> {
        val bottomCoordinateBuilder: DiagonalBottomCoordinateBuilder = DiagonalBottomCoordinateBuilder(puzzle)
        val bottomHalfDiagonalVectors: List<String> = bottomCoordinateBuilder.buildVectors()

        val topCoordinateBuilder: DiagonalTopCoordinateBuilder = DiagonalTopCoordinateBuilder(puzzle)
        val topHalfDiagonalVectors: List<String> = topCoordinateBuilder.buildVectors()

        val coordinates: List<String?> = (
                    bottomCoordinateBuilder.buildCoordinates(bottomHalfDiagonalVectors, word) +
                    bottomCoordinateBuilder.buildCoordinates(bottomHalfDiagonalVectors, word, reversed = true) +
                    topCoordinateBuilder.buildCoordinates(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors), word) +
                    topCoordinateBuilder.buildCoordinates(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors), word, reversed = true)
                ).filterNotNull().distinct()

            return buildCoordinateString(coordinates)
    }

    private fun buildCoordinateString(coordinates: List<String?>) = coordinates.map { coords -> "$word: $coords" }

}