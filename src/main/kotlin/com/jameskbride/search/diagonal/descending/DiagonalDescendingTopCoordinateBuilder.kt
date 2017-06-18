package com.jameskbride.search.diagonal.descending

import com.jameskbride.search.diagonal.DiagonalCoordinateBuilder

class DiagonalDescendingTopCoordinateBuilder(puzzle: Array<Array<String>>) : DiagonalCoordinateBuilder(puzzle) {

    override fun getWordIndices(startingColumn: Int, rowRange: IntProgression): List<Pair<Int,Int>> {
        var currentColumnIndex = startingColumn
        return rowRange.map { rowIndex ->
            Pair(++currentColumnIndex, rowIndex + startingColumn )
        }
    }

    override fun mapVector(columnIndex: Int): String {
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

    override fun getRowRange(startingRowIndex: Int, word: String) = IntRange(startingRowIndex, startingRowIndex + word.indices.last)

}