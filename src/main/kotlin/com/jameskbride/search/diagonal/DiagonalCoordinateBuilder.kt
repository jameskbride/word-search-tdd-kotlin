package com.jameskbride.search.diagonal

abstract class DiagonalCoordinateBuilder(val puzzle: Array<Array<String>>) {

    fun buildLetterVectors(): MutableList<String> {
        var collatedVectors: MutableList<String> = mutableListOf()
        collatedVectors.addAll(IntRange(0, puzzle.indices.last).map { index ->
            mapVectors(index)
        })

        return collatedVectors
    }

    fun findWord(vectors: List<String>, word: String, reversed: Boolean = false): List<String> {
        val coordinates: List<String> = vectors.mapIndexed{ startingRowIndex, vector ->
            val directedWord = getDirectedWord(reversed, word)
            val startingColumn = vector.indexOf(directedWord)
            when {
                foundWord(startingColumn) -> {
                    val wordIndices: List<Pair<Int, Int>> = getDirectedWordIndices(reversed, startingColumn, startingRowIndex, directedWord)
                    wordIndices.map { (x, y) -> "($x,$y)" }.joinToString(",")
                }
                else -> { null }
            }

        }.filterNotNull()

        return coordinates
    }

    private fun getDirectedWordIndices(reversed: Boolean, startingColumn: Int, startingRowIndex: Int, directedWord: String): List<Pair<Int, Int>> {
        val wordIndices: List<Pair<Int, Int>> = when {
            reversed -> mapWordIndices(startingColumn, startingRowIndex, directedWord).reversed()
            else -> mapWordIndices(startingColumn, startingRowIndex, directedWord)
        }
        return wordIndices
    }

    private fun getDirectedWord(reversed: Boolean, word: String): String {
        val directedWord = when {
            reversed -> word.reversed()
            else -> word
        }
        return directedWord
    }

    private fun foundWord(startingColumn: Int) = startingColumn > -1

    private fun mapWordIndices(startingColumn: Int, startingRowIndex: Int, word: String): List<Pair<Int,Int>> {
        val rowRange: IntRange = getRowRange(startingRowIndex, word)
        return getWordIndices(startingColumn, rowRange)
    }

    private fun getRowRange(startingRowIndex: Int, word: String) = IntRange(startingRowIndex, startingRowIndex + word.indices.last)

    abstract fun getWordIndices(startingColumn: Int, rowRange: IntRange): List<Pair<Int,Int>>

    abstract fun mapVectors(rowIndex: Int): String
}