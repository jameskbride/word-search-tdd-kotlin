package com.jameskbride.search.diagonal

class DiagonalTopHalfCoordinateBuilder(val word: String, val puzzle: Array<Array<String>>) {
    fun buildTopHalfDiagonalVectors(): List<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        val columnRange: IntRange = IntRange(0, puzzle[0].indices.last)
        collatedVectors.addAll(columnRange.map { columnIndex ->
            mapDiagonalVectorsByColumn(columnIndex)
        })

        return collatedVectors
    }

    fun mapDiagonalVectorsByColumn(columnIndex: Int): String {
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

    fun buildTopHalfCoordinates(matchingVectors: List<String>): List<String?> {
        val coordinates: List<String?> = matchingVectors.map{ vector ->
            val startingColumn = vector.indexOf(word)
            if (startingColumn > -1) {
                var currentColumnIndex = startingColumn
                val rowRange: IntRange = IntRange(startingColumn, startingColumn + word.length - 1)
                rowRange.map { rowIndex ->
                    currentColumnIndex++
                    "(${currentColumnIndex},${rowIndex})"
                }.joinToString(",")
            } else {
                null
            }
        }

        return coordinates
    }
}