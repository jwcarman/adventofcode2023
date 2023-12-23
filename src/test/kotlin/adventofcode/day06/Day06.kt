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

package adventofcode.day06

import adventofcode.util.readAsString
import adventofcode.util.removeWhitespace
import adventofcode.util.splitByWhitespace
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day06 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day06-example.txt")) shouldBe 288L
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day06.txt")) shouldBe 512295L
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day06-example.txt")) shouldBe 71503L
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day06.txt")) shouldBe 36530883L
    }


    private fun calculatePart1(input: String): Long {
        val lines = input.lines()
        val times = lines[0].removePrefix("Time:").trim().splitByWhitespace().map { it.toLong() }
        val distances = lines[1].removePrefix("Distance:").trim().splitByWhitespace().map { it.toLong() }
        return times.zip(distances)
            .map { (time, distance) -> countWinningChargeTimes(time, distance) }
            .fold(1L) { acc, i -> acc * i }
    }
    private fun calculatePart2(input: String): Long {
        val lines = input.lines()
        val time = lines[0].removePrefix("Time:").removeWhitespace().toLong()
        val distance = lines[1].removePrefix("Distance:").removeWhitespace().toLong()
        return countWinningChargeTimes(time, distance)
    }

    private fun countWinningChargeTimes(time: Long, distance: Long): Long {
        val count =
            (time / 2 downTo 1).map { chargeTime -> chargeTime * (time - chargeTime) }.takeWhile { it > distance }
                .count()
        return count * 2 - ((time + 1) % 2)
    }
}