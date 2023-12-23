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

package adventofcode.day05

import adventofcode.util.intersection
import adventofcode.util.translate

data class Mapping(val inputRange: LongRange, val offset: Long) {

    val outputRange = inputRange.translate(offset)
    init {
        require(inputRange.first <= inputRange.last) { "Input range must not be empty!" }
    }

    fun map(srcValue: Long): Long {
        require(srcValue in inputRange) { "Source value not in range $inputRange" }
        return offset + srcValue
    }

    fun feedsInto(successor: Mapping): Boolean =
        successor.inputRange.first <= outputRange.last && successor.inputRange.last >= outputRange.first

    fun feedsFrom(predecessor: Mapping): Boolean =
        predecessor.inputRange.last + predecessor.offset >= inputRange.first && predecessor.inputRange.first + predecessor.offset <= inputRange.last

    operator fun plus(successor: Mapping): List<Mapping> {
        require(feedsInto(successor)) { "Cannot add successor $successor to $this" }
        val intersection = successor.inputRange.intersection(outputRange)
        val results = mutableListOf(Mapping(intersection.translate(-offset), offset + successor.offset))
        if(outputRange.first < intersection.first) {
            results.add(Mapping((outputRange.first..<intersection.first).translate(-offset), offset))
        }
        if(outputRange.last > intersection.last) {
            results.add(Mapping((intersection.last + 1..outputRange.last).translate(-offset), offset))
        }
        if(successor.inputRange.first < intersection.first) {
            results.add(Mapping((successor.inputRange.first..<intersection.first), successor.offset))
        }
        if(successor.inputRange.last > intersection.last) {
            results.add(Mapping((intersection.last + 1..successor.inputRange.last), successor.offset))
        }
        return results
    }
    companion object {
        fun parse(input: String): Mapping {
            val splits = input.split("\\s+".toRegex())
            val destStart = splits[0].toLong()
            val srcStart = splits[1].toLong()
            val rangeLength = splits[2].toLong()
            val srcRange = srcStart..<srcStart + rangeLength
            val offset = destStart - srcStart
            return Mapping(srcRange, offset)
        }
    }
}
