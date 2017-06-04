class VerticalSearch(val word: String, val puzzle: Array<Array<String>>) {

    fun execute(): List<String?> {
        val columnIndices: IntRange = IntRange(0, puzzle[0].size - 1)
        return columnIndices.map { columnIndex ->
            val collatedColumn: String = puzzle.indices.map { puzzle[it][columnIndex] }.reduce({accum, char -> "$accum$char"})
            searchCardinally(columnIndex, word, collatedColumn) ?:
                    searchCardinally(columnIndex, word, collatedColumn, reversed = true)
        }.filterNotNull()
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
        val coordinates: String = wordIndices.map { "($index,$it)," }.reduce({ accum, coords -> accum + coords.removeSuffix(",")})

        return "$word: $coordinates"
    }
}