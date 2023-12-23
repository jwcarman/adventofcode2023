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

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MappingTest {
    @Test
    fun shouldFeedIntoWhenExactlyMatchingSuccessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(100L..110L, 1000L)
        predecessor.feedsInto(successor) shouldBe true
    }

    @Test
    fun shouldFeedIntoWhenOverlapsWithinSuccessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(102L..108L, 1000L)
        predecessor.feedsInto(successor) shouldBe true
    }

    @Test
    fun shouldFeedIntoWhenOverlapsAtFrontOfSuccessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(105..115L, 1000L)
        predecessor.feedsInto(successor) shouldBe true
    }

    @Test
    fun shouldFeedIntoWhenOverlapsAtBackOfSuccessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(95..105L, 1000L)
        predecessor.feedsInto(successor) shouldBe true
    }

    @Test
    fun shouldFeedIntoWhenOverlapsAtBothEndsOfSuccessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(95..115L, 1000L)
        predecessor.feedsInto(successor) shouldBe true
    }

    @Test
    fun shouldNotFeedIntoWhenNotOverlappingSuccessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(115..125L, 1000L)
        predecessor.feedsInto(successor) shouldBe false
    }
    
    @Test
    fun shouldFeedFromWhenExactlyMatchingPredecessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(100L..110L, 1000L)
        successor.feedsFrom(predecessor) shouldBe true
    }

    @Test
    fun shouldFeedFromWhenOverlapsWithinPredecessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(102L..108L, 1000L)
        successor.feedsFrom(predecessor) shouldBe true
    }

    @Test
    fun shouldFeedFromWhenOverlapsAtFrontWithPredecessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(105..115L, 1000L)
        successor.feedsFrom(predecessor) shouldBe true
    }

    @Test
    fun shouldFeedFromWhenOverlapsAtBackWithPredecessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(95..105L, 1000L)
        successor.feedsFrom(predecessor) shouldBe true
    }

    @Test
    fun shouldFeedFromWhenOverlapsAtBothEndsWithPredecessor() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(95..115L, 1000L)
        successor.feedsFrom(predecessor) shouldBe true
    }

    @Test
    fun addingSuccessorWithSameRangeShouldYieldSameRangeWithSumOfOffsets() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(100L..110L, 1000L)
        val sum = predecessor + successor
        sum shouldBe listOf(Mapping(0L..10L, 1100L))
    }

    @Test
    fun addingSuccessorWithPredecessorHeadShouldYieldTwoResults() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(105L..110L, 1000L)

        val sum = predecessor + successor
        sum shouldContainExactlyInAnyOrder listOf(
            Mapping(0L..4L, 100L),
            Mapping(5L..10L, 1100L)
        )
    }

    @Test
    fun addingSuccessorWithSuccessorHeadShouldYieldTwoResults() {
        val predecessor = Mapping(5L..10L, 100L)
        val successor = Mapping(100L..110L, 1000L)
        val sum = predecessor + successor
        sum shouldContainExactlyInAnyOrder listOf(
            Mapping(5L..10L, 1100L),
            Mapping(100L..104L, 1000L)
        )
    }

    @Test
    fun addingSuccessorWithPredecessorTailShouldYieldTwoResults() {
        val predecessor = Mapping(0L..10L, 100L)
        val successor = Mapping(100L..105L, 1000L)
        val sum = predecessor + successor
        sum shouldContainExactlyInAnyOrder listOf(
            Mapping(0L..5L, 1100L),
            Mapping(6L..10L, 100L)
        )
    }

    @Test
    fun addingSuccessorWithSuccessorTailShouldYieldTwoResults() {
        val predecessor = Mapping(0L..5L, 100L)
        val successor = Mapping(100L..110L, 1000L)
        val sum = predecessor + successor
        sum shouldContainExactlyInAnyOrder listOf(
            Mapping(0L..5L, 1100L),
            Mapping(106L..110L, 1000L)
        )
    }
}