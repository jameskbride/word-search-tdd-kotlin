package com.jameskbride.search.diagonal 

class DiagonalAscendingBottomCoordinateBuilder(puzzle: Array<Array<String>>) : DiagonalCoordinateBuilder(puzzle) {
    override fun getWordIndices(startingColumn: Int, rowRange: IntRange): List<Pair<Int, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mapVector(columnIndex: Int): String {
        var currentColumnIndex = columnIndex
        var currentRowIndex = puzzle.indices.last
        var vector: String = ""
        var maxRowIndex = puzzle.indices.last
        while (maxRowIndex > -1 && currentColumnIndex in puzzle[0].indices) {
            vector += puzzle[currentRowIndex][currentColumnIndex]
            currentRowIndex--
            currentColumnIndex++
        }

        return vector
    }
}