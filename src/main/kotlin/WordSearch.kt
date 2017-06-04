class WordSearch(val words: List<String>, val puzzle: Array<Array<String>>) {
    fun search(): List<String> {
        val output: MutableList<String?> = mutableListOf()

        words.forEach { word ->
            output.addAll(puzzle.indices.map { rowIndex ->
                val collatedRow: String = puzzle[rowIndex].map { it }.reduce({ accum, char -> "$accum$char" })
                searchHorizontally(rowIndex, word, collatedRow) ?:
                    searchHorizontally(rowIndex, word, collatedRow, reversed = true)
            })

            val columnIndices: IntRange = IntRange(0, puzzle[0].size - 1)
            output.addAll(columnIndices.map { columnIndex ->
                val collatedColumn: String = puzzle.indices.map { puzzle[it][columnIndex] }.reduce({accum, char -> "$accum$char"})
                searchVertically(columnIndex, word, collatedColumn) ?:
                        searchVertically(columnIndex, word, collatedColumn, reversed = true)
            })
        }

        return output.filterNotNull()
    }

    private fun searchVertically(columnIndex: Int, word: String, collatedColumn: String, reversed: Boolean = false): String? {
        val directedWord = when { reversed -> word.reversed() else -> word }
        val getWordIndicesWithDirection = when {
            reversed -> {foundIndex: Int, word: String -> getWordIndices(foundIndex, word).reversed()}
            else -> {foundIndex: Int, word: String -> getWordIndices(foundIndex, word)}
        }

        val foundIndex: Int = collatedColumn.indexOf(directedWord)
        return if (foundIndex > -1) {
            val wordIndices: IntProgression = getWordIndicesWithDirection(foundIndex, directedWord)
            buildVerticalCoordinateString(wordIndices, columnIndex, word)
        } else {
            null
        }
    }

    private fun searchHorizontally(rowIndex: Int, word: String, collatedRow: String, reversed: Boolean = false): String? {
        val directedWord = when { reversed -> word.reversed() else -> word }
        val getWordIndicesWithDirection = when {
            reversed -> {foundIndex: Int, word: String -> getWordIndices(foundIndex, word).reversed()}
            else -> {foundIndex: Int, word: String -> getWordIndices(foundIndex, word)}
        }

        val foundIndex: Int = collatedRow.indexOf(directedWord)
        return if (foundIndex > -1) {
            val wordIndices: IntProgression = getWordIndicesWithDirection(foundIndex, directedWord)
            buildHorizontalCoordinateString(wordIndices, rowIndex, word)
        } else {
            null
        }
    }

    private fun getWordIndices(foundIndex: Int, word: String): IntRange {
         return IntRange(foundIndex, foundIndex + word.length - 1)
    }

    private fun buildHorizontalCoordinateString(wordIndices: IntProgression, rowIndex: Int, word: String): String {
        val coordinates: String = wordIndices.map { "($it,$rowIndex)," }.reduce({ accum, coords ->
            accum + coords.removeSuffix(",")
        })

        return "$word: $coordinates"
    }

    private fun buildVerticalCoordinateString(wordIndices: IntProgression, index: Int, word: String): String {
        val coordinates: String = wordIndices.map { "($index,$it)," }.reduce({ accum, coords ->
            accum + coords.removeSuffix(",")
        })

        return "$word: $coordinates"
    }
}