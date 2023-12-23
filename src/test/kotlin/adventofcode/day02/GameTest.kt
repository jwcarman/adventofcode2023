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

package adventofcode.day02

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun parseShouldExtractRoundsCorrectly() {
        val game = Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        game.id shouldBe 1
        game.rounds shouldHaveSize 3

        game.rounds[0].red shouldBe 4
        game.rounds[0].green shouldBe 0
        game.rounds[0].blue shouldBe 3

        game.rounds[1].red shouldBe 1
        game.rounds[1].green shouldBe 2
        game.rounds[1].blue shouldBe 6

        game.rounds[2].red shouldBe 0
        game.rounds[2].green shouldBe 2
        game.rounds[2].blue shouldBe 0
    }

    @Test
    fun maxRedShouldReturnMaxRedValue() {
        val game = Game.parse("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")
        game.maxRed() shouldBe 20
    }

    @Test
    fun maxGreenShouldReturnMaxGreenValue() {
        val game = Game.parse("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")
        game.maxGreen() shouldBe 13
    }

    @Test
    fun maxBlueShouldReturnMaxBlueValue() {
        val game = Game.parse("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")
        game.maxBlue() shouldBe 6
    }

    @Test
    fun calculatePowerShouldReturnCorrectPower() {
        val game = Game.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        game.calculatePower() shouldBe 48
    }
}