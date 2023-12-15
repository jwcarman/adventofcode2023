package adventofcode

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