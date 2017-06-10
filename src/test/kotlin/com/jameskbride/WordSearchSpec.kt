package com.jameskbride

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object WordSearchSpec: Spek({

    describe("loading word search") {
        val puzzle: Array<Array<String>> =
                arrayOf(
                        arrayOf("O","N"),
                        arrayOf("I","T")
                )

        it("reads the list of words to find") {
            val expectedWords: List<String> = listOf("ON", "IT")

            val wordSearch: WordSearch = WordSearch(expectedWords, puzzle)

            assertTrue(wordSearch.words.containsAll(expectedWords))
        }

        it("reads the puzzle") {
            val wordSearch: WordSearch = WordSearch(listOf("ON"), puzzle)

            assertArrayEquals(puzzle, wordSearch.puzzle)
        }
    }

    describe("searching horizontally") {
        val puzzle: Array<Array<String>> =
                arrayOf(
                        arrayOf("O","N"),
                        arrayOf("I","T")
                )
        it("can find one word by searching forward") {
            val wordSearch: WordSearch = WordSearch(listOf("ON"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(1, results.size)
            assertEquals("ON: (0,0),(1,0)", results[0])
        }

        it("can find one word by searching backwards") {
            val wordSearch: WordSearch = WordSearch(listOf("NO"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(1, results.size)
            assertEquals("NO: (1,0),(0,0)", results[0])
        }

        it("can find multiple words by searching forward") {
            val wordSearch: WordSearch = WordSearch(listOf("ON", "IT"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(2, results.size)
            assertEquals("ON: (0,0),(1,0)", results[0])
            assertEquals("IT: (0,1),(1,1)", results[1])
        }

        it("can find multiple words by searching backwards") {
            val wordSearch: WordSearch = WordSearch(listOf("NO", "TI"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(2, results.size)
            assertEquals("NO: (1,0),(0,0)", results[0])
            assertEquals("TI: (1,1),(0,1)", results[1])
        }
    }

    describe("searching vertically") {
        val puzzle: Array<Array<String>> =
                arrayOf(
                        arrayOf("O","N"),
                        arrayOf("I","T")
                )

        it("can find one word by searching forward") {
            val wordSearch: WordSearch = WordSearch(listOf("OI"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(1, results.size)
            assertEquals("OI: (0,0),(0,1)", results[0])
        }

        it("can find one word by searching backwards") {
            val wordSearch: WordSearch = WordSearch(listOf("IO"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(1, results.size)
            assertEquals("IO: (0,1),(0,0)", results[0])
        }

        it("can find multiple words by searching forward") {
            val wordSearch: WordSearch = WordSearch(listOf("OI", "NT"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(2, results.size)
            assertEquals("OI: (0,0),(0,1)", results[0])
            assertEquals("NT: (1,0),(1,1)", results[1])
        }

        it("can find multiple words by searching backwards") {
            val wordSearch: WordSearch = WordSearch(listOf("IO", "TN"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(2, results.size)
            assertEquals("IO: (0,1),(0,0)", results[0])
            assertEquals("TN: (1,1),(1,0)", results[1])
        }
    }

    describe("searching diagonally descending") {
        val puzzle: Array<Array<String>> =
                arrayOf(
                        arrayOf("O","T", "X"),
                        arrayOf("I","N", "Y"),
                        arrayOf("A","B", "C")
                )

        it("can find one word by searching forward") {
            val wordSearch: WordSearch = WordSearch(listOf("ON"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(1, results.size)
            assertEquals("ON: (0,0),(1,1)", results[0])
        }

        it("can find multiple words by searching forward") {
            val wordSearch: WordSearch = WordSearch(listOf("ON", "IB"), puzzle)

            val results: List<String> = wordSearch.search()

            assertEquals(2, results.size)
            assertEquals("ON: (0,0),(1,1)", results[0])
            assertEquals("IB: (0,1),(1,2)", results[1])
        }
    }
})
