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

package adventofcode.day03

import adventofcode.util.geom.plane.Point2D
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PartNumberTest {
    @Test
    fun shouldHaveValueZeroUponConstruction() {
        val partNumber = PartNumber()
        partNumber.value shouldBe 0
    }

    @Test
    fun shouldBeEmptyUponConstruction() {
        val partNumber = PartNumber()
        partNumber.isEmpty() shouldBe true
    }

    @Test
    fun zeroPartNumberShouldReturnNewValueWhenAdded() {
        val partNumber = PartNumber()
        val newPartNumber = partNumber.addDigitAt(Point2D(1, 0), '7')
        newPartNumber.value shouldBe 7
        newPartNumber.points shouldBe setOf(Point2D(1, 0))
    }

    @Test
    fun nonZeroPartNumberShouldReturnNewValueWhenAdded() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        val newPartNumber = partNumber.addDigitAt(Point2D(1, 0), '7')
        newPartNumber.value shouldBe 47
        newPartNumber.points shouldBe setOf(Point2D(0,0), Point2D(1, 0))
    }

    @Test
    fun nonZeroPartNumberShouldNotBeEmpty() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isEmpty() shouldBe false
    }

    @Test
    fun shouldBeAdjacentToPointNorth() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(0, -1)) shouldBe true
    }

    @Test
    fun shouldBeAdjacentToPointSouth() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(0, 1)) shouldBe true
    }

    @Test
    fun shouldBeAdjacentToPointEast() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(1, 0)) shouldBe true
    }

    @Test
    fun shouldBeAdjacentToPointWest() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(-1, 0)) shouldBe true
    }

    @Test
    fun shouldBeAdjacentToPointNorthEast() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(1, -1)) shouldBe true
    }

    @Test
    fun shouldBeAdjacentToPointNorthWest() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(-1, -1)) shouldBe true
    }

    @Test
    fun shouldBeAdjacentToPointSouthEast() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(1, 1)) shouldBe true
    }

    @Test
    fun shouldBeAdjacentToPointSouthWest() {
        val partNumber = PartNumber().addDigitAt(Point2D(0,0), '4')
        partNumber.isAdjacentTo(Point2D(-1, 1)) shouldBe true
    }
}