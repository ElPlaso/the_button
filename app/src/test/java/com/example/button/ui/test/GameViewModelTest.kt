package com.example.button.ui.test

import com.example.button.data.CardData
import com.example.button.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_CorrectHandGuessed_ScoreUpdated() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerHand = currentGameUiState.currentBestHand

        viewModel.checkSelectedHand(correctPlayerHand!!)
        currentGameUiState = viewModel.uiState.value
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ScoreRemainsZero() {
        var currentGameUiState = viewModel.uiState.value

        val bestHand = currentGameUiState.currentBestHand
        val incorrectPlayerHand = currentGameUiState.currentHands.first { it != bestHand }

        viewModel.checkSelectedHand(incorrectPlayerHand)
        currentGameUiState = viewModel.uiState.value
        assertEquals(SCORE_AFTER_FIRST_INCORRECT_ANSWER, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_IncorrectGuessAfterCorrectGuess_ScoreDecreases() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerHand = currentGameUiState.currentBestHand

        viewModel.checkSelectedHand(correctPlayerHand!!)

        val bestHand = currentGameUiState.currentBestHand
        val incorrectPlayerHand = currentGameUiState.currentHands.first { it != bestHand }

        viewModel.checkSelectedHand(incorrectPlayerHand)
        currentGameUiState = viewModel.uiState.value
        assertEquals(SCORE_AFTER_FIRST_INCORRECT_ANSWER, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_Initialization_FirstBoardLoaded() {
        val gameUiState = viewModel.uiState.value
        val bestHand =
            CardData.calculateBestHand(gameUiState.currentBoard, gameUiState.currentPocket)

        assertEquals(bestHand, gameUiState.currentBestHand)
        assertTrue(gameUiState.score == SCORE_AFTER_INITIALIZATION)
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_AllHandsGuessedCorrectly_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerHand = currentGameUiState.currentBestHand
        repeat(14) {
            expectedScore += 1
            viewModel.checkSelectedHand(correctPlayerHand!!)

            currentGameUiState = viewModel.uiState.value
            correctPlayerHand = currentGameUiState.currentBestHand

            assertEquals(expectedScore, currentGameUiState.score)
        }

        assertTrue(currentGameUiState.isGameOver)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = 1
        private const val SCORE_AFTER_FIRST_INCORRECT_ANSWER = 0
        private const val SCORE_AFTER_INITIALIZATION = 0
    }
}