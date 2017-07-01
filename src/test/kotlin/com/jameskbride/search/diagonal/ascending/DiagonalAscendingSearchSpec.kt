package com.jameskbride.search.diagonal.ascending

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object DiagonalAscendingSearchSpec: Spek({

    describe("Searching diagonally ascending") {
        describe("searching forward") {
            val puzzle: Array<Array<String>> =
                    arrayOf(
                            arrayOf("O","T", "X"),
                            arrayOf("I","N", "Y"),
                            arrayOf("A","B", "C")
                    )

            it("can find a word by searching the center line") {
                val wordSearch: DiagonalAscendingSearch = DiagonalAscendingSearch("AN", puzzle)

                val results: List<String?> = wordSearch.execute()

                Assert.assertEquals(1, results.size)
                Assert.assertEquals("AN: (0,2),(1,1)", results[0])
            }

            it("can find a word by searching off the center line") {
                val wordSearch: DiagonalAscendingSearch = DiagonalAscendingSearch("BY", puzzle)

                val results: List<String?> = wordSearch.execute()
                println(results)

                Assert.assertEquals(1, results.size)
                Assert.assertEquals("BY: (1,2),(2,1)", results[0])
            }
        }
    }
})