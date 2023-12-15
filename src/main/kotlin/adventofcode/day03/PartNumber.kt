package adventofcode.day03

import adventofcode.util.geom.plane.Point2D

data class PartNumber(val value: Int = 0, val points: Set<Point2D> = emptySet()) {
    fun isEmpty() = points.isEmpty()
    fun addDigitAt(point: Point2D, digit: Char): PartNumber =
        PartNumber(value * 10 + digit.code - '0'.code, points + point)

    fun isAdjacentTo(point: Point2D) = points.any { it.x - point.x in -1..1 && it.y - point.y in -1..1 }

}