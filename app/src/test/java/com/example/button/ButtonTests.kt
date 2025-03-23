package com.example.button

import org.junit.Assert.assertEquals
import org.junit.Test

class ButtonTests {

    @Test
    fun get_ace_of_spades_image() {
        val suit = "spades"
        val rank = "A"

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
    fun get_best_hand_quads() {
        val hand = arrayOf(
            Pair("clubs", "A"),
            Pair("diamonds", "A"),
            Pair("hearts", "A"),
            Pair("spades", "A"),
            Pair("spades", "K")
        )

        val expectedBestHand = "Quads"
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_full_house() {
        val hand = arrayOf(
            Pair("clubs", "A"),
            Pair("diamonds", "A"),
            Pair("hearts", "A"),
            Pair("hearts", "K"),
            Pair("spades", "K")
        )

        val expectedBestHand = "Full House"
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_flush() {
        val hand = arrayOf(
            Pair("spades", "8"),
            Pair("spades", "9"),
            Pair("spades", "10"),
            Pair("spades", "J"),
            Pair("spades", "A")
        )

        val expectedBestHand = "Flush"
        val actualBestHand = getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }
}