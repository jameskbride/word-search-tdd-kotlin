class WordSearch(val words: List<String>, val puzzle: Array<Array<String>>) {
    fun search(): List<String> {
        val output: MutableList<String?> = mutableListOf()

        words.forEach { word -> puzzle.indices.map { rowIndex ->
                val collatedRow: String = puzzle[rowIndex].map { it }.reduce({ accum, char -> "$accum$char" })
                val result: String? =
                        searchHorizontally(rowIndex, word, collatedRow) ?:
                        searchHorizontally(rowIndex, word, collatedRow, reversed = true)
                output.add(result)
            }
        }

        return output.filterNotNull()
    }

    private fun searchHorizontally(rowIndex: Int, word: String, collatedRow: String, reversed: Boolean = false): String? {
        val directedWord = when { reversed -> word.reversed() else -> word }
        val getWordIndicesWithDirection = when {
            reversed -> {foundIndex: Int, word: String -> getWordIndices(foundIndex, word).reversed()}
            else -> {foundIndex: Int, word: String -> getWordIndices(foundIndex, word)}
        }

        val foundIndex: Int = collatedRow.indexOf(directedWord)
        if (foundIndex > -1) {
            val wordIndices: IntProgression = getWordIndicesWithDirection(foundIndex, directedWord)
            val foundString: String = buildCoordinateString(wordIndices, rowIndex, word)

            return foundString
        }

        return null
    }

    private fun getWordIndices(foundIndex: Int, word: String): IntRange {
        val wordIndices: IntRange = IntRange(foundIndex, foundIndex + word.length - 1)
        return wordIndices
    }

    private fun buildCoordinateString(wordIndices: IntProgression, rowIndex: Int, word: String): String {
        val coordinates: String = wordIndices.map { "($it,$rowIndex)," }.reduce({ accum, coords ->
            accum + coords.removeSuffix(",")
        })
        val foundString: String = "${word}: $coordinates"

        return foundString
    }
}