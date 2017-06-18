package com.jameskbride.search.diagonal

class DiagonalDescendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {

    fun execute(): List<String?> {
        val bottomCoordinateBuilder: DiagonalBottomCoordinateBuilder = DiagonalBottomCoordinateBuilder(puzzle)
        val bottomHalfDiagonalVectors: List<String> = bottomCoordinateBuilder.buildLetterVectors()

        val topCoordinateBuilder: DiagonalTopCoordinateBuilder = DiagonalTopCoordinateBuilder(puzzle)
        val topHalfDiagonalVectors: List<String> = topCoordinateBuilder.buildLetterVectors()

        val coordinates: List<String?> = (
                    bottomCoordinateBuilder.findWord(bottomHalfDiagonalVectors, word) +
                    bottomCoordinateBuilder.findWord(bottomHalfDiagonalVectors, word, reversed = true) +
                    topCoordinateBuilder.findWord(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors), word) +
                    topCoordinateBuilder.findWord(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors), word, reversed = true)
                ).filterNotNull().distinct()

            return buildCoordinateString(word, coordinates)
    }


}