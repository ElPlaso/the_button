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
        val actualLength = CardData().generateRandomBoard().size
        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun get_length_of_random_pocket() {
        val expectedLength = 2
        val actualLength = CardData().generateRandomPocket(arrayOf()).size
        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun get_best_hand_straight_flush() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TEN),
            Card(Suit.CLUBS, Rank.JACK),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.CLUBS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT_FLUSH
        val actualBestHand = CardData().calculateBestHand(board, arrayOf())
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_straight_flush_low_ace() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TWO),
            Card(Suit.CLUBS, Rank.THREE),
            Card(Suit.CLUBS, Rank.FOUR),
            Card(Suit.CLUBS, Rank.FIVE),
            Card(Suit.CLUBS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT_FLUSH
        val actualBestHand = CardData().calculateBestHand(board, arrayOf())
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_quads() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.SPADES, Rank.ACE),
            Card(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.QUADS
        val actualBestHand = CardData().calculateBestHand(board, arrayOf())
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_full_house() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.HEARTS, Rank.KING),
            Card(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.FULL_HOUSE
        val actualBestHand = CardData().calculateBestHand(board, arrayOf())
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_flush() {
        val board = arrayOf(
            Card(Suit.SPADES, Rank.EIGHT),
            Card(Suit.SPADES, Rank.NINE),
            Card(Suit.SPADES, Rank.TEN),
            Card(Suit.SPADES, Rank.JACK),
            Card(Suit.SPADES, Rank.ACE)
        )

        val expectedBestHand = Hand.FLUSH
        val actualBestHand = CardData().calculateBestHand(board, arrayOf())
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_straight() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TEN),
            Card(Suit.CLUBS, Rank.JACK),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = CardData().calculateBestHand(board, arrayOf())
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_straight_low_ace() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TWO),
            Card(Suit.CLUBS, Rank.THREE),
            Card(Suit.CLUBS, Rank.FOUR),
            Card(Suit.CLUBS, Rank.FIVE),
            Card(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = CardData().calculateBestHand(board, arrayOf())
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_with_pocket_straight_flush() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TEN),
            Card(Suit.CLUBS, Rank.JACK),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.DIAMONDS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.ACE)
        )

        val pocket = arrayOf(
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.CLUBS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT_FLUSH
        val actualBestHand = CardData().calculateBestHand(board, pocket)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_with_pocket_straight_flush_low_ace() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TWO),
            Card(Suit.CLUBS, Rank.THREE),
            Card(Suit.CLUBS, Rank.FOUR),
            Card(Suit.DIAMONDS, Rank.SEVEN),
            Card(Suit.DIAMONDS, Rank.SEVEN)
        )

        val pocket = arrayOf(
            Card(Suit.CLUBS, Rank.FIVE),
            Card(Suit.CLUBS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT_FLUSH
        val actualBestHand = CardData().calculateBestHand(board, pocket)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_with_pocket_quads() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.SPADES, Rank.JACK),
            Card(Suit.SPADES, Rank.QUEEN)
        )

        val pocket = arrayOf(
            Card(Suit.SPADES, Rank.ACE),
            Card(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.QUADS
        val actualBestHand = CardData().calculateBestHand(board, pocket)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_with_pocket_full_house() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.HEARTS, Rank.JACK),
            Card(Suit.SPADES, Rank.QUEEN)
        )

        val pocket = arrayOf(
            Card(Suit.HEARTS, Rank.KING),
            Card(Suit.SPADES, Rank.KING)
        )

        val expectedBestHand = Hand.FULL_HOUSE
        val actualBestHand = CardData().calculateBestHand(board, pocket)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_with_pocket_flush() {
        val board = arrayOf(
            Card(Suit.SPADES, Rank.EIGHT),
            Card(Suit.SPADES, Rank.NINE),
            Card(Suit.SPADES, Rank.TEN),
            Card(Suit.HEARTS, Rank.TWO),
            Card(Suit.HEARTS, Rank.SEVEN),
        )

        val pocket = arrayOf(
            Card(Suit.SPADES, Rank.JACK),
            Card(Suit.SPADES, Rank.ACE)
        )

        val expectedBestHand = Hand.FLUSH
        val actualBestHand = CardData().calculateBestHand(board, pocket)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_with_pocket_straight() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TEN),
            Card(Suit.CLUBS, Rank.JACK),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.CLUBS, Rank.TWO),
            Card(Suit.DIAMONDS, Rank.SEVEN)
        )

        val pocket = arrayOf(
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = CardData().calculateBestHand(board, pocket)
        assertEquals(expectedBestHand, actualBestHand)
    }

    @Test
    fun get_best_hand_with_pocket_straight_low_ace() {
        val board = arrayOf(
            Card(Suit.CLUBS, Rank.TWO),
            Card(Suit.CLUBS, Rank.THREE),
            Card(Suit.CLUBS, Rank.FOUR),
            Card(Suit.CLUBS, Rank.SEVEN),
            Card(Suit.DIAMONDS, Rank.SEVEN)
        )

        val pocket = arrayOf(
            Card(Suit.CLUBS, Rank.FIVE),
            Card(Suit.DIAMONDS, Rank.ACE)
        )

        val expectedBestHand = Hand.STRAIGHT
        val actualBestHand = CardData().calculateBestHand(board, pocket)
        assertEquals(expectedBestHand, actualBestHand)
    }
}