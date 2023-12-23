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

package adventofcode.day07

import adventofcode.util.readAsString
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day07 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day07-example.txt")) shouldBe 6440
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day07.txt")) shouldBe 248812215
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day07-example.txt")) shouldBe 5905
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day07.txt")) shouldBe 250057090
    }

    private fun calculatePart1(input: String): Int {
        val hands = input.lines().map { CamelCardsHand.parse(it) }.sortedBy { it.value }
        return hands.mapIndexed { index, hand -> (index + 1) * hand.bid }.sum()
    }
    private fun calculatePart2(input: String): Int {
        val hands = input.lines().map { CamelCardsHand.parse(it) }.sortedBy { it.wildCardValue }
        return hands.mapIndexed { index, hand -> (index + 1) * hand.bid }.sum()
    }
}