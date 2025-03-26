package com.example.button.data

import com.example.button.model.Hand

object Game {
    private var score: Int = 0

    fun getScore(): Int {
        return score
    }

    fun checkSelectedHand(hand: Hand, bestHand: Hand) {
        if (hand == bestHand) {
            score++

            if (score == 23456) {
                score = 0
            }
        } else if (score != 0) {
            score--
        }
    }

    fun restart() {
        score = 0
    }
}