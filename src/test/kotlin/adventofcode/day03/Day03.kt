/*
 * Copyright (c) 2023 James Carman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package adventofcode.day03

import adventofcode.util.geom.plane.Point2D
import adventofcode.util.grid.TextGrid
import adventofcode.util.readAsString
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day03 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day03-example.txt")) shouldBe 4361
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day03.txt")) shouldBe 533775
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day03-example.txt")) shouldBe 467835
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day03.txt")) shouldBe 78236071
    }

    private fun calculatePart1(input: String): Int {
        val grid = TextGrid(input.lines())
        val partNumbers = mutableSetOf<PartNumber>()
        val symbolPoints = mutableSetOf<Point2D>()

        for (y in 0 until grid.height()) {
            var partNumber = PartNumber()
            for (x in 0 until grid.width()) {
                val c = grid[x, y]
                if (c.isDigit()) {
                    partNumber = partNumber.addDigitAt(Point2D(x, y), c)
                } else {
                    if (!partNumber.isEmpty()) {
                        partNumbers.add(partNumber)
                        partNumber = PartNumber()
                    }
                    if (c != '.') {
                        symbolPoints.add(Point2D(x, y))
                    }
                }
            }
            if (!partNumber.isEmpty()) {
                partNumbers.add(partNumber)
            }
        }

        return partNumbers
            .filter { partNumber -> symbolPoints.any { symbolPoint -> partNumber.isAdjacentTo(symbolPoint) } }
            .sumOf { it.value }
    }

    private fun calculatePart2(input: String): Int {
        val grid = TextGrid(input.lines())
        val partNumbers = mutableSetOf<PartNumber>()
        val gearPoints = mutableSetOf<Point2D>()

        for (y in 0 until grid.height()) {
            var partNumber = PartNumber()
            for (x in 0 until grid.width()) {
                val c = grid[x, y]
                if (c.isDigit()) {
                    partNumber = partNumber.addDigitAt(Point2D(x, y), c)
                } else {
                    if (!partNumber.isEmpty()) {
                        partNumbers.add(partNumber)
                        partNumber = PartNumber()
                    }
                    if (c == '*') {
                        gearPoints.add(Point2D(x, y))
                    }
                }
            }
            if (!partNumber.isEmpty()) {
                partNumbers.add(partNumber)
            }
        }
        return gearPoints.sumOf { gearPoint ->
            val adjacentPartNumbers = partNumbers.filter { partNumber -> partNumber.isAdjacentTo(gearPoint) }
            when (adjacentPartNumbers.size) {
                2 -> adjacentPartNumbers.map { it.value }.reduce(Int::times)
                else -> 0
            }
        }

    }
}