package com.jameskbride.search.diagonal

class DiagonalBottomCoordinateBuilder(word: String, puzzle: Array<Array<String>>) : DiagonalCoordinateBuilder(word, puzzle) {

    fun buildVectors(): MutableList<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        collatedVectors.addAll(IntRange(0, puzzle.indices.last).map { index ->
            mapVectors(index)
        })

        return collatedVectors
    }

    override fun getWordIndices(startingColumn: Int, rowRange: IntRange): List<Pair<Int,Int>> {
        var currentColumnIndex = startingColumn
        return rowRange.map { rowIndex ->
            Pair(currentColumnIndex++, rowIndex + startingColumn)
        }
    }

    override fun mapVectors(rowIndex: Int): String {
        var currentRowIndex = rowIndex
        var currentColumnIndex = 0
        var vector: String = ""
        while (currentRowIndex in puzzle.indices && currentColumnIndex in puzzle[0].indices) {
            vector += puzzle[currentRowIndex][currentColumnIndex]
            currentRowIndex++
            currentColumnIndex++
        }

        return vector
    }
}