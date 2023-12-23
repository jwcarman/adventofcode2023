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

package adventofcode.day08

import adventofcode.util.Repeater
import adventofcode.util.lcm
import adventofcode.util.readAsString
import adventofcode.util.removeAll
import adventofcode.util.splitByWhitespace
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day08 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day08-example1.txt")) shouldBe 2L
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day08.txt")) shouldBe 13771L
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day08-example2.txt")) shouldBe 6L
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day08.txt")) shouldBe 13129439557681L
    }


    private fun calculatePart1(input: String): Long {
        val instructions = parseInstructions(input)
        val mappings = parseMappings(input)
        return followPath(mappings, instructions, "AAA")
    }

    private fun calculatePart2(input: String): Long {
        val instructions = parseInstructions(input)
        val mappings = parseMappings(input)
        val initialNodes = mappings.keys.filter { it.endsWith("A") }
        return initialNodes
            .map { initialNode -> followPath(mappings, instructions, initialNode) { it.endsWith("Z") } }
            .lcm()
    }

    private fun parseInstructions(input: String) = input.substringBefore("\n\n").toCharArray().toList()

    private fun parseMappings(input: String) = input.substringAfter("\n\n").lines()
        .map { it.removeAll("= \\(", ",", "\\)") }
        .map { it.splitByWhitespace() }
        .associate { (key, left, right) -> key to (left to right) }

    private fun followPath(
        path: Map<String, Pair<String, String>>,
        instructions: List<Char>,
        initialNode: String,
        stopPredicate: (String) -> Boolean = { it == "ZZZ" }
    ): Long {
        val repeater = Repeater(instructions)
        var currentNode = initialNode
        var count = 0L
        do {
            val instruction = repeater.next()
            val (left, right) = path[currentNode]!!
            currentNode = if (instruction == 'L') left else right
            count++
        } while (!stopPredicate(currentNode))
        return count
    }
}