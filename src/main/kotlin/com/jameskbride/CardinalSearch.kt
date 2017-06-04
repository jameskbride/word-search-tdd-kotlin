package com.jameskbride

open abstract class CardinalSearch(val word: String, val puzzle: Array<Array<String>>) {

    protected fun searchCardinally(index: Int, word: String, collatedWord: String, reversed: Boolean = false): String? {
        val directedWord = when {
            reversed -> word.reversed()
            else -> word
        }
        val getWordIndicesWithDirection = when {
            reversed -> { foundIndex: Int, word: String -> getWordIndices(foundIndex, word).reversed()}
            else -> { foundIndex: Int, word: String -> getWordIndices(foundIndex, word) }
        }

        val foundIndex: Int = collatedWord.indexOf(directedWord)
        return if (foundIndex > -1) {
            val wordIndices: IntProgression = getWordIndicesWithDirection(foundIndex, directedWord)
            buildCoordinateString(wordIndices, index, word)
        } else {
            null
        }
    }

    private fun getWordIndices(foundIndex: Int, word: String): IntRange {
        return IntRange(foundIndex, foundIndex + word.length - 1)
    }

    private fun buildCoordinateString(wordIndices: IntProgression, index: Int, word: String): String {
        val coordinates: String = buildCoordinates(wordIndices, index).joinToString(",")

        return "$word: $coordinates"
    }

    protected abstract fun buildCoordinates(wordIndices: IntProgression, index: Int) : List<String>
}