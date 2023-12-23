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

package adventofcode.day09

import adventofcode.util.readAsString
import adventofcode.util.splitByWhitespace
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day09 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day09-example.txt")) shouldBe 114L
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day09.txt")) shouldBe 2038472161L
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day09-example.txt")) shouldBe 2L
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day09.txt")) shouldBe 1091L
    }


    private fun calculatePart1(input: String): Long {

        return input.lines().sumOf { line ->
            createSequence(line.splitByWhitespace().map { it.toLong() })
                .takeWhile { diffs -> diffs.any { it != 0L } }
                .map { it.last() }
                .sum()
        }
    }

    private fun createSequence(seed: List<Long>) =
        generateSequence(seed) { it.windowed(2).map { (left, right) -> right - left } }

    private fun calculatePart2(input: String): Long {
        return input.lines().sumOf { line ->
            createSequence(line.splitByWhitespace().map { it.toLong() })
                .takeWhile { diffs -> diffs.any { it != 0L } }
                .map { it.first() }
                .toList()
                .reversed()
                .reduce{ acc, i -> i - acc}
        }
    }
}