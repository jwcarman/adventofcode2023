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
import adventofcode.util.removeAll
import adventofcode.util.translate

data class AlmanacSection(val mappings: List<Mapping>) {


    fun map(srcValue: Long): Long = mappings.find { it.inputRange.contains(srcValue) }?.map(srcValue) ?: srcValue

    operator fun plus(successor: AlmanacSection): AlmanacSection {
        val newMappings = mutableListOf<Mapping>()
        newMappings.addAll(createIntersectionMappings(successor))
        newMappings.addAll(createUnmappedPredecessorMappings(successor))
        newMappings.addAll(createUnmappedSuccessorMappings(successor))
        return AlmanacSection(newMappings.sortedBy { it.inputRange.first })
    }

    private fun createUnmappedPredecessorMappings(successor: AlmanacSection): List<Mapping> {
        return mappings.flatMap { predMapping ->
            predMapping.outputRange
                .removeAll(successor.mappings.map { it.inputRange })
                .map { Mapping(it.translate(-predMapping.offset), predMapping.offset) }
        }
    }

    private fun createUnmappedSuccessorMappings(successor: AlmanacSection): List<Mapping> {
        return successor.mappings.flatMap { succMapping ->
            succMapping.inputRange
                .removeAll(mappings.map { it.outputRange })
                .map { Mapping(it, succMapping.offset) }
        }
    }

    private fun createIntersectionMappings(successor: AlmanacSection): List<Mapping> {
        return mappings.map { predMapping ->
            successor.mappings
                .filter { predMapping.feedsInto(it) }
                .map {
                    Mapping(
                        predMapping.outputRange.intersection(it.inputRange).translate(-predMapping.offset),
                        predMapping.offset + it.offset
                    )
                }
        }.flatten()
    }

    companion object {
        fun parse(input: String): AlmanacSection {
            val mappings = input.substringAfter(':').trim().lines()
                .map { it.trim() }
                .map { Mapping.parse(it) }
                .sortedBy { it.inputRange.first }
            return AlmanacSection(mappings)
        }

        fun parseList(input: String): List<AlmanacSection> {
            return input.trim().split("\n\n").map { parse(it) }
        }
    }

}
