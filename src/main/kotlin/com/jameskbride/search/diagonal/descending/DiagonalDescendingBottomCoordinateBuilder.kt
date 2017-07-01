package com.jameskbride.search.diagonal.descending

class DiagonalDescendingBottomCoordinateBuilder(puzzle: Array<Array<String>>) : DiagonalDescendingCoordinateBuilder(puzzle) {

    override fun getWordIndices(startingColumn: Int, rowRange: IntProgression): List<Pair<Int,Int>> {
        var currentColumnIndex = startingColumn
        return rowRange.map { rowIndex ->
            Pair(currentColumnIndex++, rowIndex + startingColumn)
        }
    }

    override fun mapVector(rowIndex: Int): String {
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

    override fun getRowRange(startingRowIndex: Int, word: String) = IntRange(startingRowIndex, startingRowIndex + word.indices.last)

}