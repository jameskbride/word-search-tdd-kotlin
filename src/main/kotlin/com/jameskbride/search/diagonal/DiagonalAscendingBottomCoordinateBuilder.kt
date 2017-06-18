package com.jameskbride.search.diagonal 

class DiagonalAscendingBottomCoordinateBuilder(puzzle: Array<Array<String>>) : DiagonalCoordinateBuilder(puzzle) {
    override fun getWordIndices(startingColumn: Int, rowRange: IntRange): List<Pair<Int, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mapVector(rowIndex: Int): String {
        return "ANX"
    }
}