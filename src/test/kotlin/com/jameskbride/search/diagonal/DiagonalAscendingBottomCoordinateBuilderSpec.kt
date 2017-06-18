package com.jameskbride.search.diagonal

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object DiagonalAscendingBottomCoordinateBuilderSpec: Spek({

    val puzzle: Array<Array<String>> =
            arrayOf(
                    arrayOf("O","T", "X"),
                    arrayOf("I","N", "Y"),
                    arrayOf("A","B", "C")
            )

    describe("given the last row") {
        it("map a vector") {
            val builder: DiagonalAscendingBottomCoordinateBuilder =
                    DiagonalAscendingBottomCoordinateBuilder(puzzle)

            val result = builder.mapVector(2)

            assertEquals(result, "ANX")
        }
    }
})

