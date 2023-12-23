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

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AlmanacSectionTest {

    @Test
    fun addingSectionsShouldYieldSameResultsWhenMapping() {
        val section1 = AlmanacSection(
            listOf(
                Mapping(5L..15L, 100)
            )
        )
        val section2 = AlmanacSection(
            listOf(
                Mapping(0L..110L, 1000)
            )
        )
        section2.map(section1.map(200L)) shouldBe 200L
        section2.map(section1.map(5L)) shouldBe 1105L
        section2.map(section1.map(10L)) shouldBe 1110L
        section2.map(section1.map(15L)) shouldBe 115L
        section2.map(section1.map(0L)) shouldBe 1000L
    }

    @Test
    fun addingSuccessorWithNoRangesThatFeedFromSuccessorShouldOnlyApplyPredecessorOffset() {
        val predecessor = AlmanacSection(
            listOf(
                Mapping(5L..10L, 100L)
            )
        )

        val successor = AlmanacSection(listOf())

        val sum = predecessor + successor
        sum.map(7L) shouldBe 107L
    }

    @Test
    fun addingSuccessorWithNoPredecessorRangesThatFeedIntoSuccessorShouldApplySuccessorOffset() {
        val predecessor = AlmanacSection(
            listOf()
        )

        val successor = AlmanacSection(
            listOf(
                Mapping(5L..10L, 100L)
            )
        )
        val sum = predecessor + successor
        sum.map(7L) shouldBe 107L
    }

    @Test
    fun parseShouldExtractData() {
        val section = AlmanacSection.parse(
            """seed-to-soil map:
            50 98 2
            52 50 48"""
        )

        section.mappings.size shouldBe 2
        section.mappings[0].inputRange shouldBe 50L..97L
        section.mappings[0].offset shouldBe 2
        section.mappings[1].inputRange shouldBe 98L..99L
        section.mappings[1].offset shouldBe -48
    }

    @Test
    fun mapShouldReturnSourceValueWhenNoMappingFunctionApplies() {
        val section = AlmanacSection.parse(
            """seed-to-soil map:
            50 98 2
            52 50 48"""
        )
        section.map(49) shouldBe 49
    }

    @Test
    fun mapShouldReturnDestinationValueWhenMappingFunctionApplies() {
        val section = AlmanacSection.parse(
            """seed-to-soil map:
            50 98 2
            52 50 48"""
        )
        section.map(98) shouldBe 50
        section.map(99) shouldBe 51
        section.map(50) shouldBe 52
        section.map(51) shouldBe 53
    }
}