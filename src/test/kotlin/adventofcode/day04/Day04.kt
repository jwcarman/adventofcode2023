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

package adventofcode.day04

import adventofcode.util.readAsString
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day04 {
    @Test
    fun example1() {
        calculatePart1(readAsString("day04-example.txt")) shouldBe 13
    }

    @Test
    fun part1() {
        calculatePart1(readAsString("day04.txt")) shouldBe 27845
    }

    @Test
    fun example2() {
        calculatePart2(readAsString("day04-example.txt")) shouldBe 30
    }

    @Test
    fun part2() {
        calculatePart2(readAsString("day04.txt")) shouldBe 9496801
    }


    private fun calculatePart1(input: String): Int = input.lines().map { ScratchCard.parse(it) }.sumOf { it.score() }
    private fun calculatePart2(input: String): Int {
        val cards = input.lines().map { ScratchCard.parse(it) }
        val queue = cards.toMutableList()
        val cardCounts = mutableMapOf<Int,Int>()
        cards.forEach { cardCounts[it.cardNumber] = 1 }

        while (queue.isNotEmpty()) {
            val card = queue.removeFirst()
            val nCopies = cardCounts[card.cardNumber]!!
            val matchCount = card.matchCount()
            for (i in 1..matchCount) {
                cardCounts[card.cardNumber+i] = cardCounts[card.cardNumber+i]!! + nCopies
            }
        }
        return cardCounts.values.sum()
    }
}