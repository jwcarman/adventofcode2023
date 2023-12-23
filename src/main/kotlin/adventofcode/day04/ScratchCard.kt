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

class ScratchCard(val cardNumber: Int, val winningNumbers: Set<Int>, val numbers: Set<Int>) {

    fun score(): Int {
        val matchCount = matchCount()
        if (matchCount == 0) {
            return 0
        }
        return 1.shl(matchCount - 1)
    }

    fun matchCount() = winningNumbers.intersect(numbers).size

    companion object {
        fun parse(input: String): ScratchCard {
            val cardNumber = input.substringBefore(':').removePrefix("Card ").trim().toInt()
            val splits = input.substringAfter(':').split('|').map { it.trim() }
            val regex = "\\s+".toRegex()
            val winningNumbers = splits[0].split(regex).map { it.toInt() }.toSet()
            val numbers = splits[1].split(regex).map { it.toInt() }.toSet()
            return ScratchCard(cardNumber, winningNumbers, numbers)
        }
    }


}