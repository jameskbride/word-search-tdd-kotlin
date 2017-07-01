package com.jameskbride.search.diagonal.descending

import com.jameskbride.search.diagonal.DiagonalSearch

class DiagonalDescendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {

    fun execute(): List<String?> {
        val bottomCoordinateBuilder: DiagonalDescendingBottomCoordinateBuilder = DiagonalDescendingBottomCoordinateBuilder(puzzle)
        val bottomHalfDiagonalVectors: List<String> = bottomCoordinateBuilder.buildLetterVectors()

        val descendingTopCoordinateBuilder: DiagonalDescendingTopCoordinateBuilder = DiagonalDescendingTopCoordinateBuilder(puzzle)
        val topHalfDiagonalVectors: List<String> = descendingTopCoordinateBuilder.buildLetterVectors()

        val coordinates: List<String?> = (
                    bottomCoordinateBuilder.findWord(bottomHalfDiagonalVectors, word) +
                    bottomCoordinateBuilder.findWord(bottomHalfDiagonalVectors, word, reversed = true) +
                    descendingTopCoordinateBuilder.findWord(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors), word) +
                    descendingTopCoordinateBuilder.findWord(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors), word, reversed = true)
                ).filterNotNull().distinct()

            return buildCoordinateString(word, coordinates)
    }
}