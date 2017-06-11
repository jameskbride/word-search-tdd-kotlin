package com.jameskbride

class DiagonalDescendingSearch(val word: String, val puzzle: Array<Array<String>>) {

    fun execute(): List<String?> {
        val bottomCoordinateBuilder: DiagonalBottomCoordinateBuilder = DiagonalBottomCoordinateBuilder(word, puzzle)
        val bottomHalfDiagonalVectors: List<String> = bottomCoordinateBuilder.buildBottomHalfDiagonalVectors()

        val topCoordinateBuilder: DiagonalTopHalfCoordinateBuilder = DiagonalTopHalfCoordinateBuilder(word, puzzle)
        val topHalfDiagonalVectors: List<String> = topCoordinateBuilder.buildTopHalfDiagonalVectors()

        val coordinates: List<String?> =
                (
                    bottomCoordinateBuilder.buildBottomHalfCoordinates(bottomHalfDiagonalVectors) +
                    topCoordinateBuilder.buildTopHalfCoordinates(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors))
                ).filterNotNull().distinct()

            return buildCoordinateString(coordinates)
    }

    private fun buildCoordinateString(coordinates: List<String?>) = coordinates.map { coords -> "$word: $coords" }

}