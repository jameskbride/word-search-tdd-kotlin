class WordSearch(val words: List<String>, val puzzle: Array<Array<String>>) {
    fun search(): List<String> {
        val output: MutableList<String?> = mutableListOf()

        words.forEach { word -> puzzle.indices.map { rowIndex ->
                val collatedRow: String = puzzle[rowIndex].map { it }.reduce({ accum, char -> "$accum$char" })
                val result: String? = searchHorizontally(rowIndex, word, collatedRow) ?: searchReverseHorizontally(rowIndex, word.reversed(), collatedRow)
                output.add(result)
            }
        }

        return output.filterNotNull()
    }

    private fun searchHorizontally(rowIndex: Int, word: String, collatedRow: String): String? {
        val foundIndex: Int = collatedRow.indexOf(word)
        if (foundIndex > -1) {
            val wordIndices: IntRange = IntRange(foundIndex, foundIndex + word.length - 1)
            val coordinates: String = wordIndices.map { "($it,$rowIndex)," }.reduce({ accum, coords ->
                accum + coords.removeSuffix(",")
            })
            var foundString: String = "$word: $coordinates"

            return foundString
        }

        return null
    }

    private fun searchReverseHorizontally(rowIndex: Int, word: String, collatedRow: String): String? {
        val foundIndex: Int = collatedRow.indexOf(word)
        if (foundIndex > -1) {
            val wordIndices: IntProgression = IntRange(foundIndex, foundIndex + word.length - 1).reversed()
            val coordinates: String = wordIndices.map { "($it,$rowIndex)," }.reduce({ accum, coords ->
                accum + coords.removeSuffix(",")
            })
            var foundString: String = "${word.reversed()}: $coordinates"

            return foundString
        }

        return null
    }
}