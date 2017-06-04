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
                        arrayOf("B","O","N","E","S"),
                        arrayOf("K","I","R","K", "Z"),
                        arrayOf("Z","Z","Z","Z","Z"),
                        arrayOf("Z","Z","Z","Z","Z"),
                        arrayOf("Z","Z","Z","Z","Z")
                )

        it("reads the list of words to find") {
            val expectedWords: List<String> = listOf("BONES", "KIRK")

            val wordSearch: WordSearch = WordSearch(expectedWords, puzzle)

            assertTrue(wordSearch.words.containsAll(expectedWords))
        }

        it("reads the puzzle") {
            val wordSearch: WordSearch = WordSearch(listOf("BONES"), puzzle)

            assertArrayEquals(puzzle, wordSearch.puzzle)
        }
    }

    describe("searching horizontally") {
        it("can find one word by searching forward") {
            val puzzle: Array<Array<String>> =
                    arrayOf(
                            arrayOf("O","N"),
                            arrayOf("Z","Z")
                    )
            val toFind: String = "ON"

            val wordSearch: WordSearch = WordSearch(listOf(toFind), puzzle)

            val results: List<String> = wordSearch.search()
            assertEquals(1, results.size)
            assertEquals("ON: (0,0),(1,0)", results[0])
        }

        it("can find one word by searching backwards") {
            val puzzle: Array<Array<String>> =
                    arrayOf(
                            arrayOf("O","N"),
                            arrayOf("Z","Z")
                    )
            val toFind: String = "NO"

            val wordSearch: WordSearch = WordSearch(listOf(toFind), puzzle)

            val results: List<String> = wordSearch.search()
            assertEquals(1, results.size)
            assertEquals("NO: (1,0),(0,0)", results[0])
        }

        it("can find multiple word by searching forward") {
            val puzzle: Array<Array<String>> =
                    arrayOf(
                            arrayOf("O","N"),
                            arrayOf("I","T")
                    )

            val wordSearch: WordSearch = WordSearch(listOf("ON", "IT"), puzzle)

            val results: List<String> = wordSearch.search()
            assertEquals(2, results.size)
            assertEquals("ON: (0,0),(1,0)", results[0])
            assertEquals("IT: (0,1),(1,1)", results[1])
        }
    }
})
