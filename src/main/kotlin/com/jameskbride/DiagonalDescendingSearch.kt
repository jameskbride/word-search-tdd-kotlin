package com.jameskbride

class DiagonalDescendingSearch(val word: String, val puzzle: Array<Array<String>>) {

    fun execute(): List<String?> {
        val matchingVectors: List<String> = buildDiagonalVectors().filter {vector -> vector.indexOf(word) > -1}
        val coordinates: List<String?> = buildCoordinates(matchingVectors)

        return buildCoordinateString(coordinates)
    }

    private fun buildCoordinateString(coordinates: List<String?>) = coordinates.map { coords -> "$word: $coords" }

    private fun buildCoordinates(matchingVectors: List<String>): List<String?> {
        val coordinates: List<String?> = matchingVectors.map { vector ->
            val startingRow = vector.indexOf(word)
            val rowRange: IntRange = IntRange(startingRow, startingRow + word.indices.last)
            var currentColumnIndex = rowRange.first
            rowRange.map { rowIndex -> "($rowIndex,${currentColumnIndex++})" }.joinToString(",")
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