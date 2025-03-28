package com.example.button.ui

import androidx.lifecycle.ViewModel
import com.example.button.data.CardData
import com.example.button.model.Card
import com.example.button.model.Hand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var currentBoard: Array<Card> = arrayOf()
    private lateinit var currentBestHand: Hand

    private fun getRandomBoard(): Array<Card> {
        currentBoard = CardData().generateRandomBoard()
        return currentBoard
    }

    private fun getBestHand(): Hand {
        return currentBestHand
    }

    private fun getHands(): Array<Hand> {
        currentBestHand = CardData().calculateBestHand(currentBoard)
        val secondHand = CardData().getDifferentHand(arrayOf(currentBestHand))
        val thirdHand = CardData().getDifferentHand(arrayOf(currentBestHand, secondHand))

        val hands = arrayOf(currentBestHand, secondHand, thirdHand)
        hands.shuffle()

        return hands
    }

    fun checkSelectedHand(hand: Hand) {
        var score = _uiState.value.score
        if (hand == currentBestHand) {
            score++
        } else if (score != 0) {
            score--
        }

        updateGameState(score)
    }

    private fun updateGameState(updatedScore: Int) {
        if (updatedScore == 14) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    isGameOver = true,
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    currentBoard = getRandomBoard(),
                    currentHands = getHands(),
                    currentBestHand = getBestHand(),
                    score = updatedScore,
                )
            }
        }

    }


    fun resetGame() {
        _uiState.update { currentState ->
            currentState.copy(
                currentBoard = getRandomBoard(),
                currentHands = getHands(),
                currentBestHand = getBestHand(),
                score = 0,
                isGameOver = false,
            )
        }
    }

    init {
        resetGame()
    }
}