class WordSearch(val words: List<String>, val puzzle: Array<Array<String>>) {
    fun search(): List<String> {
        val output: MutableList<String?> = mutableListOf()

        words.forEach { word ->
            output.addAll(puzzle.indices.map { rowIndex ->
                val collatedRow: String = puzzle[rowIndex].map { it }.reduce({ accum, char -> "$accum$char" })
                searchCardinally(rowIndex, word, collatedRow, direction = Direction.HORIZONTAL) ?:
                        searchCardinally(rowIndex, word, collatedRow, direction = Direction.HORIZONTAL, reversed = true)
            })

            val columnIndices: IntRange = IntRange(0, puzzle[0].size - 1)
            output.addAll(columnIndices.map { columnIndex ->
                val collatedColumn: String = puzzle.indices.map { puzzle[it][columnIndex] }.reduce({accum, char -> "$accum$char"})
                searchCardinally(columnIndex, word, collatedColumn, direction = Direction.VERTICAL) ?:
                        searchCardinally(columnIndex, word, collatedColumn, direction = Direction.VERTICAL, reversed = true)
            })
        }

        return output.filterNotNull()
    }

    private fun searchCardinally(index: Int, word: String, collatedWord: String, reversed: Boolean = false, direction: Direction): String? {
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
            buildCoordinateString(wordIndices, index, word, direction)
        } else {
            null
        }
    }

    private fun getWordIndices(foundIndex: Int, word: String): IntRange {
         return IntRange(foundIndex, foundIndex + word.length - 1)
    }

    private fun buildCoordinateString(wordIndices: IntProgression, index: Int, word: String, direction: Direction): String {
        val coordinateMapper = when(direction) {
            Direction.HORIZONTAL -> wordIndices.map { "($it,$index)," }
            Direction.VERTICAL -> wordIndices.map { "($index,$it)," }
        }
        val coordinates: String = coordinateMapper.reduce({ accum, coords -> accum + coords.removeSuffix(",")})

        return "$word: $coordinates"
    }

    private enum class Direction {
        HORIZONTAL, VERTICAL
    }
}