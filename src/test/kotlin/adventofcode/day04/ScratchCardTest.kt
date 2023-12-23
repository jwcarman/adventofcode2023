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

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScratchCardTest {
    @Test
    fun scoreShouldBeZeroWithZeroMatches() {
        val scratchCard = ScratchCard(1, setOf(1, 2, 3), setOf(4, 5, 6))
        scratchCard.score() shouldBe 0
    }

    @Test
    fun scoreShouldBeOneWithOneMatch() {
        val scratchCard = ScratchCard(1, setOf(1, 2, 3), setOf(1, 5, 6))
        scratchCard.score() shouldBe 1
    }

    @Test
    fun scoreShouldBeTwoWithTwoMatches() {
        val scratchCard = ScratchCard(1, setOf(1, 2, 3), setOf(1, 2, 6))
        scratchCard.score() shouldBe 2
    }

    @Test
    fun scoreShouldBeFourWithThreeMatches() {
        val scratchCard = ScratchCard(1, setOf(1, 2, 3), setOf(1, 2, 3))
        scratchCard.score() shouldBe 4
    }

    @Test
    fun scoreShouldBeEightWithFourMatches() {
        val scratchCard = ScratchCard(1, setOf(1, 2, 3, 4), setOf(1, 2, 3, 4))
        scratchCard.score() shouldBe 8
    }

    @Test
    fun parseShouldExtractData() {
        val scratchCard = ScratchCard.parse("Card   1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        scratchCard.winningNumbers shouldBe setOf(41, 48, 83, 86, 17)
        scratchCard.numbers shouldBe setOf(83, 86, 6, 31, 17, 9, 48, 53)
        scratchCard.score() shouldBe 8
        scratchCard.cardNumber shouldBe 1
    }
}