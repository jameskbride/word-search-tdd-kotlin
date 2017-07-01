package com.jameskbride.search.diagonal.ascending

class DiagonalAscendingBottomCoordinateBuilder(puzzle: Array<Array<String>>) : DiagonalAscendingCoordinateBuilder(puzzle) {

    fun buildBottomVectorCoordinates(): List<List<Pair<Int, Int>>> {
        val columnRange: IntRange = IntRange(0, puzzle[0].indices.last)
        val columnRanges: List<IntRange> = columnRange.map { columnIndex ->
            IntRange(columnIndex, puzzle[0].indices.last)
        }

        val rowRange: IntRange = IntRange(0, puzzle.indices.last)
        val rowRanges: List<IntProgression> = rowRange.map { rowIndex ->
            IntRange(rowIndex, puzzle.indices.last).reversed()
        }

        val vectorsCoords = columnRanges.zip(rowRanges)
                .map { it.first.zip(it.second) }
        return vectorsCoords
    }
}