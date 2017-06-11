package com.jameskbride.search.diagonal

class DiagonalTopCoordinateBuilder(word: String, puzzle: Array<Array<String>>) : DiagonalCoordinateBuilder(word, puzzle) {

    override fun getWordIndices(startingColumn: Int, rowRange: IntRange): List<Pair<Int,Int>> {
        var currentColumnIndex = startingColumn
        return rowRange.map { rowIndex ->
            Pair(++currentColumnIndex, rowIndex + startingColumn )
        }
    }

    override fun mapVectors(columnIndex: Int): String {
        var currentColumnIndex = columnIndex
        var currentRowIndex = puzzle.indices.first
        var vector: String = ""
        var maxRowIndex = puzzle.indices.last
        while (maxRowIndex > -1 && currentColumnIndex in puzzle[0].indices) {
            vector += puzzle[currentRowIndex][currentColumnIndex]
            currentRowIndex++
            currentColumnIndex++
            maxRowIndex--
        }

        return vector
    }
}