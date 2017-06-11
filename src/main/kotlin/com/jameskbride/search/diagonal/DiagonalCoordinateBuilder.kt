package com.jameskbride.search.diagonal

abstract class DiagonalCoordinateBuilder(val word: String, val puzzle: Array<Array<String>>) {
    fun getRowRange(startingRowIndex: Int, word: String) = IntRange(startingRowIndex, startingRowIndex + word.indices.last)

    abstract fun getWordIndices(startingColumn: Int, rowRange: IntRange): List<Pair<Int,Int>>

    abstract fun mapVectors(rowIndex: Int): String
    fun buildCoordinates(vectors: List<String>): List<String> {
        val coordinates: List<String> = vectors.mapIndexed{ startingRowIndex, vector ->
            val startingColumn = vector.indexOf(word)
            mapWordIndices(startingColumn, startingRowIndex)
        }.filterNotNull()

        return coordinates
    }

    fun mapWordIndices(startingColumn: Int, startingRowIndex: Int): String? {
        return if (startingColumn > -1) {
            val rowRange: IntRange = getRowRange(startingRowIndex, word)
            val wordIndices: List<Pair<Int, Int>> = getWordIndices(startingColumn, rowRange)
            wordIndices.map { (x, y) -> "($x,$y)" }.joinToString(",")
        } else {
            null
        }
    }
}