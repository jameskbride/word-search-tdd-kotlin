class HorizontalSearch(word: String, puzzle: Array<Array<String>>) : CardinalSearch(word, puzzle){

    fun execute(): List<String?> {
        return puzzle.indices.map { rowIndex ->
            val collatedRow: String = puzzle[rowIndex].map { it }.reduce({ accum, char -> "$accum$char" })
            searchCardinally(rowIndex, word, collatedRow) ?:
                    searchCardinally(rowIndex, word, collatedRow, reversed = true)
        }.filterNotNull()
    }

    override fun buildCoordinates(wordIndices: IntProgression, index: Int) = wordIndices.map { "($it,$index)," }
}