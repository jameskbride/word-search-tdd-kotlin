package com.jameskbride

class DiagonalDescendingSearch(val word: String, val puzzle: Array<Array<String>>) {

    fun execute(): List<String?> {
        val bottomHalfDiagonalVectors: List<String> = buildBottomHalfDiagonalVectors()
        val topHalfDiagonalVectors: List<String> = buildTopHalfDiagonalVectors()
        val coordinates: List<String?> =
                (
                    buildBottomHalfCoordinates(bottomHalfDiagonalVectors) +
                    buildTopHalfCoordinates(topHalfDiagonalVectors.minus(bottomHalfDiagonalVectors))
                ).filterNotNull().distinct()

            return buildCoordinateString(coordinates)
    }

    private fun buildTopHalfDiagonalVectors(): List<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        val columnRange: IntRange = IntRange(0, puzzle[0].indices.last)
        collatedVectors.addAll(columnRange.map { columnIndex ->
            mapDiagonalVectorsByColumn(columnIndex)
        })

        return collatedVectors
    }

    private fun mapDiagonalVectorsByColumn(columnIndex: Int): String {
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

    private fun buildCoordinateString(coordinates: List<String?>) = coordinates.map { coords -> "$word: $coords" }

    private fun buildBottomHalfCoordinates(matchingVectors: List<String>): List<String?> {
        val coordinates: List<String?> = matchingVectors.mapIndexed{ index, vector ->
            val startingColumn = vector.indexOf(word)
            if (startingColumn > -1) {
                var currentColumnIndex = startingColumn
                val rowRange: IntRange = IntRange(index, index + word.indices.last)
                rowRange.map { rowIndex -> "(${currentColumnIndex++},${rowIndex + startingColumn})" }.joinToString(",")
            } else {
                null
            }
        }

        return coordinates
    }

    private fun buildTopHalfCoordinates(matchingVectors: List<String>): List<String?> {
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

    private fun buildBottomHalfDiagonalVectors(): MutableList<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        val rowRange: IntRange = IntRange(0, puzzle.indices.last)
        collatedVectors.addAll(rowRange.map { rowIndex ->
            mapDiagonalVectorsByRow(rowIndex)
        })

        return collatedVectors
    }

    private fun mapDiagonalVectorsByRow(rowIndex: Int): String {
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