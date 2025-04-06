package com.example.button.data

import com.example.button.model.Card
import com.example.button.model.Hand
import com.example.button.model.Rank
import com.example.button.model.Suit

class CardData {
    private fun generateRandomCard(previousCards: Array<Card>): Card {
        val suits = enumValues<Suit>()
        val ranks = enumValues<Rank>()

        val suitsLength = suits.size
        val ranksLength = ranks.size

        while (true) {
            val randomSuit = suits[((0..<suitsLength).random())]
            val randomRank = ranks[((0..<ranksLength).random())]

            val card = Card(randomSuit, randomRank)

            if (!previousCards.contains(card)) {
                return card
            }
        }
    }

    fun generateRandomBoard(): Array<Card> {
        val hand: MutableList<Card> = mutableListOf()

        repeat((0..4).count()) {
            val randomCard = generateRandomCard(hand.toTypedArray())
            hand += randomCard
        }

        return hand.toTypedArray()
    }

    fun generateRandomPocket(previousCards: Array<Card>): Array<Card> {
        val pocket: MutableList<Card> = mutableListOf()

        repeat((0..1).count()) {
            val randomCard = generateRandomCard(pocket.toTypedArray() + previousCards)
            pocket += randomCard
        }

        return pocket.toTypedArray()
    }

    private val cardComparator = Comparator<Card> { a, b ->
        val aValue = a.rank.ordinal
        val bValue = b.rank.ordinal

        when {
            (aValue == bValue) -> 0
            (aValue < bValue) -> -1
            else -> 1
        }
    }

    fun calculateBestHand(board: Array<Card>, pocket: Array<Card>): Hand {
        val hand = pocket + board

        // Check for rank occurrences
        val rankOccurrences: MutableMap<Rank, Int> = mutableMapOf()
        hand.forEach {
            val newOccurrence = rankOccurrences[it.rank]?.plus(1) ?: 1
            rankOccurrences[it.rank] = newOccurrence
        }

        // Check for flush
        var seenSuit: Suit? = null
        var nonFlushCount = 0
        var seenFlushCards = 0
        var isFlush = false
        run flushCheck@{
            hand.forEach {
                if (it.suit != seenSuit && seenSuit != null) {
                    nonFlushCount++

                    if (nonFlushCount == 3) {
                        return@flushCheck
                    }
                } else {
                    seenFlushCards++

                    if (seenFlushCards == 5) {
                        isFlush = true
                        return@flushCheck
                    }
                }

                seenSuit = it.suit
            }
        }

        // Check for straight
        val sortedHand = hand.clone()
        sortedHand.sortWith(cardComparator)
        var previousRank: Rank? = null
        var previousStraightRank: Rank? = null
        var nonStraightCount = 0
        var seenStraightCards = 1
        var isStraight = false
        run straightCheck@{
            sortedHand.forEach {
                if (previousRank != null) {
                    val currentValue = it.rank.ordinal

                    // Includes edge case for low Ace
                    if (currentValue - previousRank!!.ordinal != 1 && !(it.rank === Rank.ACE && seenStraightCards == 4 && previousStraightRank == Rank.FIVE)) {
                        nonStraightCount++

                        if (nonStraightCount == 3) {
                            return@straightCheck
                        }

                        if (!(seenStraightCards == 4 && previousStraightRank == Rank.FIVE)) {
                            seenStraightCards = 1
                        }
                    } else {
                        seenStraightCards++

                        if (seenStraightCards == 5) {
                            isStraight = true
                            return@straightCheck
                        }

                        previousStraightRank = it.rank
                    }
                }
                previousRank = it.rank
            }
        }

        // TODO: This is not always true
        if (isFlush && isStraight) {
            return Hand.STRAIGHT_FLUSH
        }

        if (rankOccurrences.values.contains(4)) {
            return Hand.QUADS
        }

        if (rankOccurrences.values.contains(3) && rankOccurrences.values.contains(2)) {
            return Hand.FULL_HOUSE
        }

        if (isFlush) {
            return Hand.FLUSH
        }

        if (isStraight) {
            return Hand.STRAIGHT
        }

        if (rankOccurrences.values.contains(3)) {
            return Hand.TRIPS
        }

        if (rankOccurrences.values.contains(2)) {
            if (rankOccurrences.values.filter { it == 2 }.size == 2) {
                return Hand.TWO_PAIR
            }

            return Hand.PAIR
        }

        return Hand.HIGH_CARD
    }

    fun getDifferentHand(currentHands: Array<Hand>): Hand {
        val playableHands = enumValues<Hand>().filter { !currentHands.contains(it) }

        return playableHands[playableHands.indices.random()]
    }
}