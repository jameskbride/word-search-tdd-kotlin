package com.jameskbride.search.diagonal.ascending

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object DiagonalAscendingSearchSpec: Spek({
    describe("searching forward") {
        val puzzle: Array<Array<String>> =
                arrayOf(
                        arrayOf("O","T", "X"),
                        arrayOf("I","N", "Y"),
                        arrayOf("A","B", "C")
                )

        it("can find one word on the center") {

            val search: DiagonalAscendingSearch = DiagonalAscendingSearch("AN", puzzle)

            val results: List<String?> = search.execute()

            Assert.assertEquals(1, results.size)
            Assert.assertEquals("AN: (0,2),(1,1)", results[0])
        }
    }
})
