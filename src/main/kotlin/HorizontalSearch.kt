class HorizontalSearch(val word: String, val puzzle: Array<Array<String>>) {

    fun execute(): List<String?> {
        return puzzle.indices.map { rowIndex ->
            val collatedRow: String = puzzle[rowIndex].map { it }.reduce({ accum, char -> "$accum$char" })
            searchCardinally(rowIndex, word, collatedRow) ?:
                    searchCardinally(rowIndex, word, collatedRow, reversed = true)
        }
    }

    private fun searchCardinally(index: Int, word: String, collatedWord: String, reversed: Boolean = false): String? {
        val directedWord = when {
            reversed -> word.reversed()
            else -> word
        }
        val getWordIndicesWithDirection = when {
            reversed -> {foundIndex: Int, word: String -> getWordIndices(foundIndex, word).reversed()}
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
        val coordinates: String = wordIndices.map { "($it,$index)," }.reduce({ accum, coords -> accum + coords.removeSuffix(",")})

        return "$word: $coordinates"
    }
}