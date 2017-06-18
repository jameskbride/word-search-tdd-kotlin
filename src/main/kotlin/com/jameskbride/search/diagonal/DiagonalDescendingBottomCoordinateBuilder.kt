package com.jameskbride.search.diagonal

class DiagonalBottomCoordinateBuilder(puzzle: Array<Array<String>>) : DiagonalCoordinateBuilder(puzzle) {

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
}