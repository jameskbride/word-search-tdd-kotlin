package com.jameskbride
class VerticalSearch(word: String, puzzle: Array<Array<String>>) : CardinalSearch(word, puzzle) {

    fun execute(): List<String?> {
        val columnIndices: IntRange = IntRange(0, puzzle[0].size - 1)
        return columnIndices.map { columnIndex ->
            val collatedColumn: String = puzzle.indices.map { puzzle[it][columnIndex] }.reduce({accum, char -> "$accum$char"})
            searchCardinally(columnIndex, word, collatedColumn) ?:
                    searchCardinally(columnIndex, word, collatedColumn, reversed = true)
        }.filterNotNull()
    }

    override fun buildCoordinates(wordIndices: IntProgression, index: Int) = wordIndices.map { "($index,$it)" }
}