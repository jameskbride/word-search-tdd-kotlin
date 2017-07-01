package com.jameskbride.search.diagonal.ascending

import com.jameskbride.search.diagonal.DiagonalSearch

data class Vector(val coords: List<Pair<Int, Int>>, val letters: String)

data class WordCoordinates(val coords: List<Pair<Int, Int>>)

class DiagonalAscendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {
    fun execute(): List<String?> {
        val vectors: List<Vector> = buildVectors()
        val wordCoordinates: List<WordCoordinates> = findWord(vectors, word)

        return wordCoordinates.map { (coords) ->
            val coordStrings = buildCoordinatesString(coords)
            "$word: $coordStrings"
        }
    }

    private fun findWord(vectors: List<Vector>, word: String): List<WordCoordinates> {
        val foundWordCoords: List<WordCoordinates> = vectors
                .filter { vector -> vector.letters.indexOf(word) > -1 }
                .map { vector -> buildWordCoordinates(vector, word) }
        return foundWordCoords
    }

    private fun buildWordCoordinates(vector: Vector, word: String): WordCoordinates {
        val fromIndex: Int = vector.letters.indexOf(word)
        val toIndex = fromIndex + word.length

        return WordCoordinates(vector.coords.subList(fromIndex, toIndex))
    }

    private fun buildCoordinatesString(coords: List<Pair<Int, Int>>) = coords.map { "(${it.first},${it.second})" }.joinToString(",")

    private fun buildVectors(): List<Vector> {
        val vectorsCoords = buildVectorCoordinates()
        val vectors: List<Vector> = vectorsCoords.map { coords ->
            val vectorString = coords.map { puzzle[it.second][it.first] }
                    .reduce { letters, letter -> letters + letter }
            Vector(coords, vectorString)
        }
        return vectors
    }

    private fun buildVectorCoordinates(): List<List<Pair<Int, Int>>> {
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


