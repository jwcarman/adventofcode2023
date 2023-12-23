package adventofcode.day07

import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CamelCardsHandTest {
    @Test
    fun valueOfFiveOfAKindShouldBeRight() {
        CamelCardsHand("AAAAA").value shouldBe "7EEEEE".toLong(16)
    }

    @Test
    fun fiveOfAKindBeatsFourOfAKind() {
        CamelCardsHand("AAAAA").value shouldBeGreaterThan CamelCardsHand("AAQAA").value
    }

    @Test
    fun fourOfAKindBeatsFullHouse() {
        CamelCardsHand("AQAAA").value shouldBeGreaterThan CamelCardsHand("QKQKQ").value
    }

    @Test
    fun fullHouseBeatsThreeOfAKind() {
        CamelCardsHand("QKQKQ").value shouldBeGreaterThan CamelCardsHand("QQQKT").value
    }

    @Test
    fun threeOfAKindBeatsTwoPair() {
        CamelCardsHand("QQQKT").value shouldBeGreaterThan CamelCardsHand("QQTJJ").value
    }

    @Test
    fun twoPairBeatsOnePair() {
        CamelCardsHand("QQTJJ").value shouldBeGreaterThan CamelCardsHand("QQ234").value
    }

    @Test
    fun onePairBeatHighCard() {
        CamelCardsHand("QQ234").value shouldBeGreaterThan CamelCardsHand("AKQJT").value
    }

    @Test
    fun sameHandShouldBeEqual() {
        CamelCardsHand("QQ234").value shouldBeEqual CamelCardsHand("QQ234").value
    }

    @Test
    fun sameHandTypeUsesOrderingRules() {
        CamelCardsHand("77888").value shouldBeGreaterThan CamelCardsHand("77788").value
    }
}