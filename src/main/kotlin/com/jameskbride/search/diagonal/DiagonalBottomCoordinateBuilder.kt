package com.jameskbride.search.diagonal

class DiagonalBottomCoordinateBuilder(val word: String, val puzzle: Array<Array<String>>) {
    fun buildCoordinates(vectors: List<String>): List<String> {
        val coordinates: List<String> = vectors.mapIndexed{ startingRowIndex, vector ->
            val startingColumn = vector.indexOf(word)
            mapWordIndices(startingColumn, startingRowIndex)
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
            Pair(currentColumnIndex++, rowIndex + startingColumn)
        }
    }

    fun buildVectors(): MutableList<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        val rowRange: IntRange = IntRange(0, puzzle.indices.last)
        collatedVectors.addAll(rowRange.map { rowIndex ->
            mapVectors(rowIndex)
        })

        return collatedVectors
    }

    private fun mapVectors(rowIndex: Int): String {
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