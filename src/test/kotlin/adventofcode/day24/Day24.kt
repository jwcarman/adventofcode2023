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

package adventofcode.day24

import adventofcode.util.readAsString
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day24 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day24-example.txt")) shouldBe 0L
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day24.txt")) shouldBe 0L
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day24-example.txt")) shouldBe 0L
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day24.txt")) shouldBe 0L
    }


    private fun calculatePart1(input: String): Long = 0
    private fun calculatePart2(input: String): Long = 0
}