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

package adventofcode.util.geom.plane

import kotlin.math.sign

data class Line2D(val begin: Point2D, val end: Point2D) {
    fun points() = sequence {
        var p = begin
        var dx = (end.x - begin.x).sign
        var dy = (end.y - begin.y).sign
        while (p != end) {
            yield(p)
            p = p.translate(dx, dy)
        }
        yield(p)
    }
}