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

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RoundTest {

    @Test
    fun parseShouldCorrectRedComponent() {
        val round = Round.parse("12 red")
        round.red shouldBe 12
        round.green shouldBe 0
        round.blue shouldBe 0
    }

    @Test
    fun parseShouldCorrectGreenComponent() {
        val round = Round.parse("6 green")
        round.red shouldBe 0
        round.green shouldBe 6
        round.blue shouldBe 0
    }

    @Test
    fun parseShouldCorrectBlueComponent() {
        val round = Round.parse("3 blue")
        round.red shouldBe 0
        round.green shouldBe 0
        round.blue shouldBe 3
    }

    @Test
    fun parseShouldIncludeAllComponents() {
        val round = Round.parse(" 1 red, 2 green, 6 blue")
        round.blue shouldBe 6
        round.green shouldBe 2
        round.red shouldBe 1
    }

    @Test
    fun parseShouldIncludeAllRepeatedComponents() {
        val round = Round.parse(" 1 red, 2 green, 6 blue, 1 red, 2 green, 6 blue")
        round.blue shouldBe 12
        round.green shouldBe 4
        round.red shouldBe 2
    }
}