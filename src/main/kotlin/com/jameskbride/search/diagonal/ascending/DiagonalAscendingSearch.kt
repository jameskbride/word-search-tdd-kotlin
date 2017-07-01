package com.jameskbride.search.diagonal.ascending

import com.jameskbride.search.diagonal.DiagonalSearch

data class Vector(val coords: List<Pair<Int, Int>>, val letters: String)

data class WordCoordinates(val coords: List<Pair<Int, Int>>)

class DiagonalAscendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {
    fun execute(): List<String?> {
        val vectors: List<Vector> = buildVectors()
        val wordCoordinates: List<WordCoordinates> = findWord(vectors, word)
        val reversedWordCoordinates: List<WordCoordinates> = findWord(vectors, word, reversed = true)

        val coordinates: List<WordCoordinates> = (
                wordCoordinates +
                reversedWordCoordinates
                )
        val bottomCoordinates: List<String?> = coordinates.map { (coords) ->
            val coordStrings = buildCoordinatesString(coords)
            "$word: $coordStrings"
        }

        return bottomCoordinates
    }

    private fun findWord(vectors: List<Vector>, word: String, reversed: Boolean = false): List<WordCoordinates> {
        val foundWordCoords: List<WordCoordinates> = vectors
                .filter { vector ->
                    when {
                        reversed -> vector.letters.reversed().indexOf(word) > -1
                        else -> vector.letters.indexOf(word) > -1
                    }
                }
                .map { vector -> buildWordCoordinates(vector, word, reversed) }
        return foundWordCoords
    }

    private fun buildWordCoordinates(vector: Vector, word: String, reversed: Boolean = false): WordCoordinates {

        val coords = when {
            reversed -> {
                val fromIndex: Int = vector.letters.reversed().indexOf(word)
                val toIndex = fromIndex + word.length
                vector.coords.reversed().subList(fromIndex, toIndex)
            }
            else -> {
                val fromIndex: Int = vector.letters.indexOf(word)
                val toIndex = fromIndex + word.length
                vector.coords.subList(fromIndex, toIndex)
            }
        }
        return WordCoordinates(coords)
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


