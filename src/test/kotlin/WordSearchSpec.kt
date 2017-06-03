import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertTrue
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object WordSearchSpec: Spek({

    describe("loading word search") {
        it("it should read the list of words to find") {
            val expectedWords: List<String> = listOf("BONES", "KIRK")

            val wordSearch: WordSearch = WordSearch(expectedWords)

            assertTrue(wordSearch.words.containsAll(expectedWords))
        }
    }
})
