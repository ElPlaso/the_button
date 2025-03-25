package com.example.button

import org.junit.Assert.assertEquals
import org.junit.Test

class ButtonTests {

    @Test
    fun get_ace_of_spades_image() {
        val suit = Suit.SPADES
        val rank = Rank.ACE

        val expectedImage = R.drawable.ace_of_spades
        val actualImage = getCardImage(suit, rank)
        assertEquals(expectedImage, actualImage)
    }

    @Test
    fun get_length_of_random_hand() {
        val expectedLength = 5
        val actualLength = generateRandomHand().size
        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun get_best_hand_straight_flush() {
        val hand = arrayOf(
            Pair(Suit.CLUBS, Rank.TEN),
            Pair(Suit.CLUBS, Rank.JACK),
            Pair(Suit.CLUBS, Rank.QUEEN),
            Pair(Suit.CLUBS, Rank.KING),
            Pair(Suit.CLUBS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT_FLUSH
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_quads() {
        val hand = arrayOf(
            Pair(Suit.CLUBS, Rank.ACE),
            Pair(Suit.DIAMONDS, Rank.ACE),
            Pair(Suit.HEARTS, Rank.ACE),
            Pair(Suit.SPADES, Rank.ACE),
            Pair(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.QUADS
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_full_house() {
        val hand = arrayOf(
            Pair(Suit.CLUBS, Rank.ACE),
            Pair(Suit.DIAMONDS, Rank.ACE),
            Pair(Suit.HEARTS, Rank.ACE),
            Pair(Suit.HEARTS, Rank.KING),
            Pair(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.FULL_HOUSE
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_flush() {
        val hand = arrayOf(
            Pair(Suit.SPADES, Rank.EIGHT),
            Pair(Suit.SPADES, Rank.NINE),
            Pair(Suit.SPADES, Rank.TEN),
            Pair(Suit.SPADES, Rank.JACK),
            Pair(Suit.SPADES, Rank.ACE)
        )

        val expectedBestHand = Hand.FLUSH
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_straight() {
        val hand = arrayOf(
            Pair(Suit.CLUBS, Rank.TEN),
            Pair(Suit.CLUBS, Rank.JACK),
            Pair(Suit.CLUBS, Rank.QUEEN),
            Pair(Suit.CLUBS, Rank.KING),
            Pair(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_straight_low_ace() {
        val hand = arrayOf(
            Pair(Suit.CLUBS, Rank.TWO),
            Pair(Suit.CLUBS, Rank.THREE),
            Pair(Suit.CLUBS, Rank.FOUR),
            Pair(Suit.CLUBS, Rank.FIVE),
            Pair(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }
}