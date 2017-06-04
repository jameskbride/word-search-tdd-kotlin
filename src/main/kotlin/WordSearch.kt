class WordSearch(val words: List<String>, val puzzle: Array<Array<String>>) {
    fun search(): List<String> {
        val output: MutableList<String> = mutableListOf()
        for (word in words) {
            for (rowIndex in puzzle.indices) {
                val collatedRow: String = puzzle[rowIndex].map { it }.reduce({accum, char -> "$accum$char"})
                val foundIndex: Int = collatedRow.indexOf(word)
                if (foundIndex > -1) {
                    val wordIndices: IntRange = IntRange(foundIndex, foundIndex + word.length -1)
                    val coordinates: String = wordIndices.map { "($it,$rowIndex)," }.reduce({accum, coords ->
                        accum + coords.removeSuffix(",")
                    })
                    var foundString: String = "$word: $coordinates"
                    output.add(foundString)
                }
            }
        }

        return output
    }
}