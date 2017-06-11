package com.jameskbride.search.diagonal

class DiagonalBottomCoordinateBuilder(val word: String, val puzzle: Array<Array<String>>) {
    fun buildCoordinates(vectors: List<String>): List<String> {
        val coordinates: List<String> = vectors.mapIndexed{ startingRowIndex, vector ->
            val startingColumn = vector.indexOf(word)
            if (startingColumn > -1) {
                val wordIndices: List<Pair<Int,Int>> = getWordIndices(word, startingColumn, startingRowIndex)
                wordIndices.map { (x, y) -> "($x,$y)"}.joinToString(",")
            } else {
                null
            }
        }.filterNotNull()

        return coordinates
    }

    private fun getWordIndices(word: String, startingColumn: Int, startingRowIndex: Int): List<Pair<Int,Int>> {
        var currentColumnIndex = startingColumn
        val rowRange: IntRange = IntRange(startingRowIndex, startingRowIndex + word.indices.last)
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