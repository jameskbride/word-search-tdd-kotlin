package com.jameskbride.search.diagonal

class DiagonalTopCoordinateBuilder(val word: String, val puzzle: Array<Array<String>>) {
    fun buildCoordinates(vectors: List<String>): List<String?> {
        val coordinates: List<String?> = vectors.map{ vector ->
            val startingColumn = vector.indexOf(word)
            if (startingColumn > -1) {
                val wordIndices: List<Pair<Int,Int>> = getWordIndices(word, startingColumn)
                wordIndices.map { (x, y) -> "($x,$y)"}.joinToString(",")
            } else {
                null
            }
        }

        return coordinates
    }

    private fun getWordIndices(word: String, startingColumn: Int): List<Pair<Int,Int>> {
        var currentColumnIndex = startingColumn
        val rowRange: IntRange = IntRange(startingColumn, startingColumn + word.length - 1)
        return rowRange.map { rowIndex ->
            Pair(++currentColumnIndex, rowIndex )
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