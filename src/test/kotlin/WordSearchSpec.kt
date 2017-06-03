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

        it("it should read the list of words to find") {
            val expectedWords: List<String> = listOf("BONES", "KIRK")

            val wordSearch: WordSearch = WordSearch(expectedWords, puzzle)

            assertTrue(wordSearch.words.containsAll(expectedWords))
        }

        it("it should read the puzzle") {
            val wordSearch: WordSearch = WordSearch(listOf("BONES"), puzzle)

            assertArrayEquals(puzzle, wordSearch.puzzle)
        }
    }
})
