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

package adventofcode.day01

import adventofcode.util.readAsString
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day01 {

    @Test
    fun example1() {
        calculatePart1(readAsString("day01-example1.txt")) shouldBe 142
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day01.txt")) shouldBe 56049
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day01-example2.txt")) shouldBe 281
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day01.txt")) shouldBe 54530
    }


    private fun calculatePart1(input: String): Int = input.lines()
            .map { it.digitsOnly() }
            .map { s -> "${s.first()}${s.last()}" }
            .map { s -> s.toInt() }
            .sum()

    private fun String.digitsOnly(): String = this.filter { it.isDigit() }

    private fun String.replaceDigitWords(): String = replace("one", "one1one")
        .replace("two", "two2two")
        .replace("three", "three3three")
        .replace("four", "four4four")
        .replace("five", "five5five")
        .replace("six", "six6six")
        .replace("seven", "seven7seven")
        .replace("eight", "eight8eight")
        .replace("nine", "nine9nine")


    private fun calculatePart2(input: String): Int = input.lines()
        .map { it.replaceDigitWords() }
        .map { it.digitsOnly() }
        .map { s -> "${s.first()}${s.last()}" }
        .map { s -> s.toInt() }
        .sum()

}