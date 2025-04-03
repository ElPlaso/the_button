package com.example.button.ui

import com.example.button.model.Card
import com.example.button.model.Hand

data class GameUiState(
    val currentBoard: Array<Card> = arrayOf(),
    val currentPocket: Array<Card> = arrayOf(),
    val currentHands: Array<Hand> = arrayOf(),
    val currentBestHand: Hand? = null,
    val score: Int = 0,
    val isGameOver: Boolean = false,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameUiState

        if (score != other.score) return false
        if (isGameOver != other.isGameOver) return false
        if (!currentBoard.contentEquals(other.currentBoard)) return false
        if (!currentPocket.contentEquals(other.currentPocket)) return false
        if (!currentHands.contentEquals(other.currentHands)) return false
        if (currentBestHand != other.currentBestHand) return false

        return true
    }

    override fun hashCode(): Int {
        var result = score
        result = 31 * result + isGameOver.hashCode()
        result = 31 * result + currentBoard.contentHashCode()
        result = 31 * result + currentPocket.contentHashCode()
        result = 31 * result + currentHands.contentHashCode()
        result = 31 * result + (currentBestHand?.hashCode() ?: 0)
        return result
    }
}