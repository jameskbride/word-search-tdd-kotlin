package com.jameskbride.search.diagonal

class DiagonalTopCoordinateBuilder(val word: String, val puzzle: Array<Array<String>>) {
    fun buildCoordinates(vectors: List<String>): List<String?> {
        val coordinates: List<String?> = vectors.map{ vector ->
            val startingColumn = vector.indexOf(word)
            mapWordIndices(startingColumn, startingColumn)
        }.filterNotNull()

        return coordinates
    }

    private fun mapWordIndices(startingColumn: Int, startingRowIndex: Int): String? {
        return if (startingColumn > -1) {
            val rowRange: IntRange = getRowRange(startingRowIndex, word)
            val wordIndices: List<Pair<Int, Int>> = getWordIndices(startingColumn, rowRange)
            wordIndices.map { (x, y) -> "($x,$y)" }.joinToString(",")
        } else {
            null
        }
    }

    private fun getRowRange(startingRowIndex: Int, word: String) = IntRange(startingRowIndex, startingRowIndex + word.indices.last)

    private fun getWordIndices(startingColumn: Int, rowRange: IntRange): List<Pair<Int,Int>> {
        var currentColumnIndex = startingColumn
        return rowRange.map { rowIndex ->
            Pair(++currentColumnIndex, rowIndex + startingColumn )
        }
    }

    fun buildVectors(): List<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        val columnRange: IntRange = IntRange(0, puzzle[0].indices.last)
        collatedVectors.addAll(columnRange.map { columnIndex ->
            mapVectors(columnIndex)
        })

        return collatedVectors
    }

    fun mapVectors(columnIndex: Int): String {
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