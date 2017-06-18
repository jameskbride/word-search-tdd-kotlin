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

    describe("mapping columns") {
        it("maps a vector on the center line") {
            val builder: DiagonalAscendingBottomCoordinateBuilder =
                    DiagonalAscendingBottomCoordinateBuilder(puzzle)

            val result = builder.mapVector(0)

            assertEquals(result, "ANX")
        }

        it("maps a vector off of the center line") {
            val builder: DiagonalAscendingBottomCoordinateBuilder =
                    DiagonalAscendingBottomCoordinateBuilder(puzzle)

            val result = builder.mapVector(1)

            assertEquals("BY", result)
        }
    }
})

