package com.jameskbride.search.diagonal.ascending

class DiagonalAscendingTopCoordinateBuilder(puzzle: Array<Array<String>>) : DiagonalAscendingCoordinateBuilder(puzzle) {

    fun buildTopVectorCoordinates(): List<List<Pair<Int, Int>>> {
        val rowRange: IntRange = IntRange(0, puzzle.indices.last)
        val rowRanges: List<IntProgression> = rowRange.reversed().map { rowIndex ->
            IntRange(0, rowIndex).reversed()
        }

        val columnRange: IntRange = IntRange(0, puzzle[0].indices.last)
        val columnRanges: List<IntProgression> = columnRange.reversed().map { columnIndex ->
            IntRange(0, columnIndex)
        }

        val vectorsCoords = columnRanges.zip(rowRanges)
                .map { it.first.zip(it.second) }
        return vectorsCoords
    }
}