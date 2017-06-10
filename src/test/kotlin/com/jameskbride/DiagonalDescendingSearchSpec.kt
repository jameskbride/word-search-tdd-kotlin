package com.jameskbride

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object DiagonalDescendingSearchSpec: Spek({

    describe("searching diagonally descending") {

        describe("search forward") {
            val puzzle: Array<Array<String>> =
                    arrayOf(
                            arrayOf("O","T", "X"),
                            arrayOf("I","N", "Y"),
                            arrayOf("A","B", "C")
                    )
            it("can find one word ") {

                val search: DiagonalDescendingSearch = DiagonalDescendingSearch("ON", puzzle)

                val results: List<String?> = search.execute()

                Assert.assertEquals(1, results.size)
                Assert.assertEquals("ON: (0,0),(1,1)", results[0])
            }

            it("can find a word off below the center diagonal") {
                val search: DiagonalDescendingSearch = DiagonalDescendingSearch("IB", puzzle)

                val results: List<String?> = search.execute()

                Assert.assertEquals(1, results.size)
                Assert.assertEquals("IB: (0,1),(1,2)", results[0])
            }

            it("can find a word in the middle of a vector") {
                val search: DiagonalDescendingSearch = DiagonalDescendingSearch("NC", puzzle)

                val results: List<String?> = search.execute()

                Assert.assertEquals(1, results.size)
                Assert.assertEquals("NC: (1,1),(2,2)", results[0])
            }
        }
    }
})
