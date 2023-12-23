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

import adventofcode.util.removeAll
import adventofcode.util.splitByWhitespace

private const val HIGH_CARD = "11111"
private const val ONE_PAIR = "2111"
private const val TWO_PAIR = "221"
private const val THREE_OF_A_KIND = "311"
private const val FULL_HOUSE = "32"
private const val FOUR_OF_A_KIND = "41"
private const val FIVE_OF_A_KIND = "5"

data class CamelCardsHand(val hand: String, val bid: Int = 0){

    val value = "${hand.handTypePrefix()}${hand.hexify()}".toLong(16)
    val wildCardValue = "${hand.wildCardHandTypePrefix()}${hand.wildCardHexify()}".toLong(16)

    private fun Char.hexify(): Char {
        return when (this) {
            'A' -> 'E'
            'K' -> 'D'
            'Q' -> 'C'
            'J' -> 'B'
            'T' -> 'A'
            else -> this
        }
    }

    private fun Char.wildCardHexify(): Char {
        return when (this) {
            'A' -> 'E'
            'K' -> 'D'
            'Q' -> 'C'
            'J' -> '1'
            'T' -> 'A'
            else -> this
        }
    }

    private fun String.hexify(): String {
        return map { c -> c.hexify() }.joinToString(separator = "")
    }

    private fun String.wildCardHexify(): String {
        return map { c -> c.wildCardHexify() }.joinToString(separator = "")
    }

    private fun String.wildCardHandTypePrefix(): String {
        val signature = removeAll("J").groupingBy { c -> c }.eachCount().values.sortedDescending().joinToString(separator = "")
        return when(signature) {
            HIGH_CARD -> "1"
            ONE_PAIR, "1111"-> "2"
            TWO_PAIR -> "3"
            THREE_OF_A_KIND, "211", "111" -> "4"
            FULL_HOUSE, "22" -> "5"
            FOUR_OF_A_KIND, "31", "21", "11" -> "6"
            FIVE_OF_A_KIND, "4", "3", "2", "1", "" -> "7"
            else -> ""
        }
    }
    private fun String.handTypePrefix(): String {
        val signature = groupingBy { c -> c }.eachCount().values.sortedDescending().joinToString(separator = "")
        return when (signature) {
            HIGH_CARD -> "1"
            ONE_PAIR -> "2"
            TWO_PAIR -> "3"
            THREE_OF_A_KIND -> "4"
            FULL_HOUSE -> "5"
            FOUR_OF_A_KIND -> "6"
            FIVE_OF_A_KIND -> "7"
            else -> ""
        }
    }

    companion object {
        fun parse(input: String): CamelCardsHand {
            val (hand, bid) = input.splitByWhitespace()
            return CamelCardsHand(hand, bid.toInt())
        }
    }
}