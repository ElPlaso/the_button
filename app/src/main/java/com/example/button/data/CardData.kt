package com.example.button.data

import com.example.button.model.Card
import com.example.button.model.Hand
import com.example.button.model.Rank
import com.example.button.model.Suit

object CardData {
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
        // Check for suit occurrences
        val suitOccurrences: MutableMap<Suit, Int> = mutableMapOf()
        hand.forEach {
            val newOccurrence = suitOccurrences[it.suit]?.plus(1) ?: 1
            suitOccurrences[it.suit] = newOccurrence
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
                    if (currentValue - previousRank!!.ordinal != 1 && !(it.rank == Rank.ACE && seenStraightCards == 4 && previousStraightRank == Rank.FIVE)) {
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

        // TODO: Can optimise checking straight, flush, and straight flush
        // Check for straight flush
        var previousSFRank: Rank? = null
        var previousStraightSFRank: Rank? = null
        var nonSFCount = 0
        var seenSFCards = 1
        var seenSFSuit: Suit? = null
        var previousFlushSFSuit: Suit? = null
        var isStraightFlush = false
        run straightFlushCheck@{
            sortedHand.forEach {
                if (previousSFRank != null) {
                    val currentValue = it.rank.ordinal

                    // Includes edge case for low Ace
                    if ((currentValue - previousSFRank!!.ordinal != 1
                                && !(it.rank == Rank.ACE && seenSFCards == 4 && previousStraightSFRank == Rank.FIVE))
                        || (it.suit != previousFlushSFSuit && previousFlushSFSuit != null)
                    ) {
                        nonSFCount++
                        if (nonSFCount == 3) {
                            return@straightFlushCheck
                        }

                        if (!(seenSFCards == 4 && previousStraightSFRank == Rank.FIVE)) {
                            previousFlushSFSuit = seenSFSuit
                        }
                    } else {
                        seenSFCards++

                        if (seenSFCards == 5) {
                            isStraightFlush = true
                            return@straightFlushCheck
                        }

                        previousStraightSFRank = it.rank
                        previousFlushSFSuit = it.suit
                    }
                }

                previousSFRank = it.rank
                seenSFSuit = it.suit
            }
        }

        if (isStraightFlush) {
            return Hand.STRAIGHT_FLUSH
        }

        if (rankOccurrences.values.contains(4)) {
            return Hand.QUADS
        }

        if (rankOccurrences.values.contains(3) && rankOccurrences.values.contains(2)) {
            return Hand.FULL_HOUSE
        }

        if (suitOccurrences.values.contains(5)) {
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