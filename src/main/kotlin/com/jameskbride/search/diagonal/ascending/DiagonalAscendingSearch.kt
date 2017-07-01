package com.jameskbride.search.diagonal.ascending

import com.jameskbride.search.diagonal.DiagonalSearch

class DiagonalAscendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {
    fun execute(): List<String?> {

        val columnRange: IntRange = IntRange(0, puzzle[0].indices.last)
        val columnRanges: List<IntRange> = columnRange.mapIndexed { index, columnIndex ->
            IntRange(columnIndex, puzzle[0].indices.last)
        }

        val rowRange: IntRange = IntRange(0, puzzle.indices.last)
        val rowRanges:List<IntProgression> = rowRange.map { rowIndex ->
            IntRange(rowIndex, puzzle.indices.last).reversed()
        }

        val vectorsCoords = columnRanges.zip(rowRanges)
                .map { it.first.zip(it.second) }

        data class Vector(val coords: List<Pair<Int, Int>>, val letters: String)

        val vectors: List<Vector> = vectorsCoords.map { coords ->
            val vectorString = coords.map {puzzle[it.second][it.first]}
                    .reduce { letters, letter -> letters + letter }
            Vector(coords, vectorString)
        }

        val foundWordCoords: List<List<Pair<Int,Int>>> = vectors
                .filter { it.letters.indexOf(word) > -1 }
                .map {
                    val startingIndex: Int = it.letters.indexOf(word)
                    val coords: List<Pair<Int,Int>> = it.coords.subList(startingIndex, startingIndex + word.length)

                    coords
                }

        return foundWordCoords.map { coords ->
            val coordStrings = coords.map { coordPair -> "(${coordPair.first},${coordPair.second})"}.joinToString(",")
            "$word: $coordStrings"
        }

    }
}


