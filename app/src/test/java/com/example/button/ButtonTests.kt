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
}