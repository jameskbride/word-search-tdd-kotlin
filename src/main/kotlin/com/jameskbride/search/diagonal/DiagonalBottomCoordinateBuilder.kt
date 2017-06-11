package com.jameskbride.search.diagonal

class DiagonalBottomCoordinateBuilder(val word: String, val puzzle: Array<Array<String>>) {
    fun buildCoordinates(matchingVectors: List<String>): List<String?> {
        val coordinates: List<String?> = matchingVectors.mapIndexed{ index, vector ->
            val startingColumn = vector.indexOf(word)
            if (startingColumn > -1) {
                var currentColumnIndex = startingColumn
                val rowRange: IntRange = IntRange(index, index + word.indices.last)
                rowRange.map { rowIndex ->
                    "(${currentColumnIndex++},${rowIndex + startingColumn})"
                }.joinToString(",")
            } else {
                null
            }
        }

        return coordinates
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