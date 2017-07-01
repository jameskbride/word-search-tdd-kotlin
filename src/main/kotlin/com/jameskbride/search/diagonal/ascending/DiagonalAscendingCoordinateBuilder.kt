package com.jameskbride.search.diagonal.ascending

open class DiagonalAscendingCoordinateBuilder(val puzzle: Array<Array<String>>) {

    fun findWord(vectors: List<Vector>, word: String, reversed: Boolean = false): List<WordCoordinates> {
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

    fun buildVectors(vectorCoordinates: List<List<Pair<Int, Int>>>): List<Vector> {
        val vectors: List<Vector> = vectorCoordinates.map { coords ->
            val vectorString = coords.map { puzzle[it.second][it.first] }
                    .reduce { letters, letter -> letters + letter }
            Vector(coords, vectorString)
        }
        return vectors
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
}