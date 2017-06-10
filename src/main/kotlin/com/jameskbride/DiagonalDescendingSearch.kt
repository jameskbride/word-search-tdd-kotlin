package com.jameskbride

class DiagonalDescendingSearch(val word: String, val puzzle: Array<Array<String>>) {

    fun execute(): List<String?> {
        val diagonalVectors: List<String> = buildDiagonalVectors()
        val coordinates: List<String?> = buildCoordinates(diagonalVectors).filterNotNull()

        return buildCoordinateString(coordinates)
    }

    private fun buildCoordinateString(coordinates: List<String?>) = coordinates.map { coords -> "$word: $coords" }

    private fun buildCoordinates(matchingVectors: List<String>): List<String?> {
        val coordinates: List<String?> = matchingVectors.mapIndexed{ index, vector ->
            val startingColumn = vector.indexOf(word)
            if (startingColumn > -1) {
                var currentColumnIndex = startingColumn
                val rowRange: IntRange = IntRange(index, index + word.indices.last)
                rowRange.map { rowIndex -> "(${currentColumnIndex++},$rowIndex)" }.joinToString(",")
            } else {
                null
            }
        }
        return coordinates
    }

    private fun buildDiagonalVectors(): MutableList<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        val rowRange: IntRange = IntRange(0, puzzle.indices.last)
        collatedVectors.addAll(rowRange.map { rowIndex ->
            var currentRowIndex = rowIndex
            var currentColumnIndex = 0
            var vector: String = ""
            while (currentRowIndex in puzzle.indices && currentColumnIndex in puzzle[0].indices) {
                vector += puzzle[currentRowIndex][currentColumnIndex]
                currentRowIndex++
                currentColumnIndex++
            }

            vector
        })

        return collatedVectors
    }
}