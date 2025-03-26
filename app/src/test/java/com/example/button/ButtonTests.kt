package com.example.button

import com.example.button.data.CardData
import com.example.button.model.Card
import com.example.button.model.Hand
import com.example.button.model.Rank
import com.example.button.model.Suit
import org.junit.Assert.assertEquals
import org.junit.Test

class ButtonTests {

    @Test
    fun get_ace_of_spades_image() {
        val suit = Suit.SPADES
        val rank = Rank.ACE
        val card = Card(suit, rank)

        val expectedImage = R.drawable.ace_of_spades
        val actualImage = getCardImage(card)
        assertEquals(expectedImage, actualImage)
    }

    @Test
    fun get_length_of_random_hand() {
        val expectedLength = 5
        val actualLength = CardData().generateRandomHand().size
        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun get_best_hand_straight_flush() {
        val hand = arrayOf(
            Card(Suit.CLUBS, Rank.TEN),
            Card(Suit.CLUBS, Rank.JACK),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.CLUBS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT_FLUSH
        val actualBestHand = CardData().getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_quads() {
        val hand = arrayOf(
            Card(Suit.CLUBS, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.SPADES, Rank.ACE),
            Card(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.QUADS
        val actualBestHand = CardData().getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_full_house() {
        val hand = arrayOf(
            Card(Suit.CLUBS, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.HEARTS, Rank.KING),
            Card(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.FULL_HOUSE
        val actualBestHand = CardData().getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_flush() {
        val hand = arrayOf(
            Card(Suit.SPADES, Rank.EIGHT),
            Card(Suit.SPADES, Rank.NINE),
            Card(Suit.SPADES, Rank.TEN),
            Card(Suit.SPADES, Rank.JACK),
            Card(Suit.SPADES, Rank.ACE)
        )

        val expectedBestHand = Hand.FLUSH
        val actualBestHand = CardData().getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_straight() {
        val hand = arrayOf(
            Card(Suit.CLUBS, Rank.TEN),
            Card(Suit.CLUBS, Rank.JACK),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = CardData().getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_straight_low_ace() {
        val hand = arrayOf(
            Card(Suit.CLUBS, Rank.TWO),
            Card(Suit.CLUBS, Rank.THREE),
            Card(Suit.CLUBS, Rank.FOUR),
            Card(Suit.CLUBS, Rank.FIVE),
            Card(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = CardData().getBestHand(hand)
        assertEquals(expectedBestHand, actualBestHand)
    }
}