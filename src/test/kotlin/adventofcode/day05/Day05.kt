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

package adventofcode.day05

import adventofcode.util.intersection
import adventofcode.util.readAsString
import adventofcode.util.whitespace
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day05 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day05-example.txt")) shouldBe 35L
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day05.txt")) shouldBe 84470622L
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day05-example.txt")) shouldBe 46L
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day05.txt")) shouldBe 26714516L
    }

    private fun calculatePart1(input: String): Long {
        val seeds = input.substringBefore("\n\n")
            .removePrefix("seeds: ")
            .split(whitespace)
            .map { it.toLong() }
        val squashed = parseSquashedSections(input)
        return seeds.minOf { squashed.map(it) }
    }


    private fun calculatePart2(input: String): Long {
        val seedRanges = input
            .substringBefore("\n\n")
            .removePrefix("seeds: ")
            .split(whitespace)
            .map { it.toLong() }
            .chunked(2)
            .map { (it[0]..<it[0] + it[1]) }


        val squashed = parseSquashedSections(input)

        val seeds = seedRanges.flatMap { seedRange ->
            squashed.mappings
                .map { it.inputRange.intersection(seedRange) }
                .filter { !it.isEmpty() }
        }.map { it.first }
        return seeds.minOf { squashed.map(it) }
    }

    private fun parseSquashedSections(input: String) = AlmanacSection.parseList(input.substringAfter("\n\n"))
        .reduce { acc, section -> acc + section }
}