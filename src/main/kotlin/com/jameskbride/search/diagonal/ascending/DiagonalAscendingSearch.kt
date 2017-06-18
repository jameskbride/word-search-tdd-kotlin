package com.jameskbride.search.diagonal.ascending

import com.jameskbride.search.diagonal.DiagonalSearch

class DiagonalAscendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {
    fun execute(): List<String?> {
        val bottomCoordinateBuilder: DiagonalAscendingBottomCoordinateBuilder = DiagonalAscendingBottomCoordinateBuilder(puzzle)
        val bottomHalfDiagonalVectors: List<String> = bottomCoordinateBuilder.buildLetterVectors()

        val coordinates: List<String?> = (
                bottomCoordinateBuilder.findWord(bottomHalfDiagonalVectors, word) +
                        bottomCoordinateBuilder.findWord(bottomHalfDiagonalVectors, word, reversed = true)
                ).filterNotNull().distinct()

        return buildCoordinateString(word, coordinates)
    }
}


