package com.example.button


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Game()
                }
            }
        }
    }
}

@VisibleForTesting
internal fun getCardImage(suit: String, rank: String): Int {
    return when (suit) {
        "clubs" -> getClubsImage(rank)
        "diamonds" -> getDiamondsImage(rank)
        "hearts" -> getHeartsImage(rank)
        "spades" -> getSpadesImage(rank)
        else -> {
            throw Error("Invalid suit")
        }
    }
}

internal fun getClubsImage(rank: String): Int {
    return when (rank) {
        "2" -> R.drawable.two_of_clubs
        "3" -> R.drawable.three_of_clubs
        "4" -> R.drawable.four_of_clubs
        "5" -> R.drawable.five_of_clubs
        "6" -> R.drawable.six_of_clubs
        "7" -> R.drawable.seven_of_clubs
        "8" -> R.drawable.eight_of_clubs
        "9" -> R.drawable.nine_of_clubs
        "10" -> R.drawable.ten_of_clubs
        "J" -> R.drawable.jack_of_clubs
        "Q" -> R.drawable.queen_of_clubs
        "K" -> R.drawable.king_of_clubs
        "A" -> R.drawable.ace_of_clubs
        else -> {
            throw Error("Invalid rank")
        }
    }
}

internal fun getDiamondsImage(rank: String): Int {
    return when (rank) {
        "2" -> R.drawable.two_of_diamonds
        "3" -> R.drawable.three_of_diamonds
        "4" -> R.drawable.four_of_diamonds
        "5" -> R.drawable.five_of_diamonds
        "6" -> R.drawable.six_of_diamonds
        "7" -> R.drawable.seven_of_diamonds
        "8" -> R.drawable.eight_of_diamonds
        "9" -> R.drawable.nine_of_diamonds
        "10" -> R.drawable.ten_of_diamonds
        "J" -> R.drawable.jack_of_diamonds
        "Q" -> R.drawable.queen_of_diamonds
        "K" -> R.drawable.king_of_diamonds
        "A" -> R.drawable.ace_of_diamonds
        else -> {
            throw Error("Invalid rank")
        }
    }
}

internal fun getHeartsImage(rank: String): Int {
    return when (rank) {
        "2" -> R.drawable.two_of_hearts
        "3" -> R.drawable.three_of_hearts
        "4" -> R.drawable.four_of_hearts
        "5" -> R.drawable.five_of_hearts
        "6" -> R.drawable.six_of_hearts
        "7" -> R.drawable.seven_of_hearts
        "8" -> R.drawable.eight_of_hearts
        "9" -> R.drawable.nine_of_hearts
        "10" -> R.drawable.ten_of_hearts
        "J" -> R.drawable.jack_of_hearts
        "Q" -> R.drawable.queen_of_hearts
        "K" -> R.drawable.king_of_hearts
        "A" -> R.drawable.ace_of_hearts
        else -> {
            throw Error("Invalid rank")
        }
    }
}

internal fun getSpadesImage(rank: String): Int {
    return when (rank) {
        "2" -> R.drawable.two_of_spades
        "3" -> R.drawable.three_of_spades
        "4" -> R.drawable.four_of_spades
        "5" -> R.drawable.five_of_spades
        "6" -> R.drawable.six_of_spades
        "7" -> R.drawable.seven_of_spades
        "8" -> R.drawable.eight_of_spades
        "9" -> R.drawable.nine_of_spades
        "10" -> R.drawable.ten_of_spades
        "J" -> R.drawable.jack_of_spades
        "Q" -> R.drawable.queen_of_spades
        "K" -> R.drawable.king_of_spades
        "A" -> R.drawable.ace_of_spades
        else -> {
            throw Error("Invalid rank")
        }
    }
}

@Composable
fun CardImage(suit: String, rank: String, modifier: Modifier = Modifier) {
    val cardImage = getCardImage(suit, rank)

    Image(
        painter = painterResource(cardImage),
        contentDescription = rank + "of" + suit,
        contentScale = ContentScale.Fit,
        modifier = modifier,
    )

}

private fun generateRandomCard(previousCards: MutableList<Pair<String, String>>): Pair<String, String> {
    val suits = arrayOf("clubs", "diamonds", "hearts", "spades")
    val ranks = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")

    val suitsLength = suits.size
    val ranksLength = ranks.size

    while (true) {
        val randomSuit = suits[((0..<suitsLength).random())]
        val randomRank = ranks[((0..<ranksLength).random())]

        val card = Pair(randomSuit, randomRank)

        if (!previousCards.contains(card)) {
            return card
        }
    }
}

@VisibleForTesting
internal fun generateRandomHand(): MutableList<Pair<String, String>> {
    val hand: MutableList<Pair<String, String>> = mutableListOf()

    repeat((0..4).count()) {
        val randomCard = generateRandomCard(hand)
        hand += randomCard
    }

    return hand
}

@Composable
fun Game(modifier: Modifier = Modifier) {
    val randomHand = generateRandomHand()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues = PaddingValues(horizontal = 10.dp))
    ) {
        randomHand.forEach({
            val (suit, rank) = it
            CardImage(
                suit, rank, modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        })
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    ButtonTheme {
        Game()
    }
}