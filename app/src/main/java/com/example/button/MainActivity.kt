package com.example.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

private fun generateRandomCard(previousCards: Array<Pair<String, String>>): Pair<String, String> {
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
internal fun generateRandomHand(): Array<Pair<String, String>> {
    val hand: MutableList<Pair<String, String>> = mutableListOf()

    repeat((0..4).count()) {
        val randomCard = generateRandomCard(hand.toTypedArray())
        hand += randomCard
    }

    return hand.toTypedArray()
}

@Composable
fun Hand(hand: Array<Pair<String, String>>, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues = PaddingValues(horizontal = 25.dp))
    ) {
        hand.forEach {
            val (suit, rank) = it
            CardImage(
                suit, rank, modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

val handRanking: Array<String> = arrayOf(
    "Straight Flush",
    "Quads",
    "Full House",
    "Flush",
    "Straight",
    "Trips",
    "Two Pair",
    "Pair",
    "High Card"
)

val rankValues: Map<String, Int> = mapOf(
    Pair("2", 2),
    Pair("3", 3),
    Pair("4", 4),
    Pair("5", 5),
    Pair("6", 6),
    Pair("7", 7),
    Pair("8", 8),
    Pair("9", 9),
    Pair("10", 10),
    Pair("J", 11),
    Pair("Q", 12),
    Pair("K", 13),
    Pair("A", 14),
)

private val cardComparator = Comparator<Pair<String, String>> { a, b ->
    val aValue = rankValues[a.second] ?: throw Error("Invalid rank")
    val bValue = rankValues[b.second] ?: throw Error("Invalid rank")

    when {
        (aValue == bValue) -> 0
        (aValue < bValue) -> -1
        else -> 1
    }
}

@VisibleForTesting
internal fun getBestHand(hand: Array<Pair<String, String>>): String {
    // Check for rank occurrences
    val rankOccurrences: MutableMap<String, Int> = mutableMapOf()
    hand.forEach {
        val newOccurrence = rankOccurrences[it.second]?.plus(1) ?: 1
        rankOccurrences[it.second] = newOccurrence
    }

    // Check for flush
    var seenSuit: String? = null
    var isFlush = true
    run flushCheck@{
        hand.forEach {
            if (it.first != seenSuit && seenSuit != null) {
                isFlush = false
                return@flushCheck
            }
            seenSuit = it.first
        }
    }

    // Check for straight
    val sortedHand = hand.clone()
    sortedHand.sortWith(cardComparator)
    var previousValue: Int? = null
    var isStraight = true
    run straightCheck@{
        sortedHand.forEach {
            if (previousValue != null) {
                val currentValue = rankValues[it.second] ?: throw Error("Invalid rank")

                // Includes edge case for low Ace
                if (currentValue - previousValue!! != 1 && !(currentValue == 14 && previousValue == 5)) {
                    isStraight = false
                    return@straightCheck
                }
            }
            previousValue = rankValues[it.second]
        }
    }

    if (isFlush && isStraight) {
        return handRanking[0]
    }

    if (rankOccurrences.values.contains(4)) {
        return handRanking[1]
    }

    if (rankOccurrences.values.contains(3) && rankOccurrences.values.contains(2)) {
        return handRanking[2]
    }

    if (isFlush) {
        return handRanking[3]
    }

    if (isStraight) {
        return handRanking[4]
    }

    if (rankOccurrences.values.contains(3)) {
        return handRanking[5]
    }

    if (rankOccurrences.values.contains(2)) {
        if (rankOccurrences.values.filter { it == 2 }.size == 2) {
            return handRanking[6]
        }

        return handRanking[7]
    }

    return handRanking[8]
}

@Composable
fun HandSelector(modifier: Modifier = Modifier, hand: String, onClick: () -> Unit) {
    val label = when (hand) {
        "Straight Flush" -> stringResource(R.string.straight_flush)
        "Quads" -> stringResource(R.string.quads)
        "Full House" -> stringResource(R.string.full_house)
        "Flush" -> stringResource(R.string.flush)
        "Straight" -> stringResource(R.string.straight)
        "Trips" -> stringResource(R.string.trips)
        "Two Pair" -> stringResource(R.string.two_pair)
        "Pair" -> stringResource(R.string.pair)
        "High Card" -> stringResource(R.string.high_card)
        else -> {
            throw Error("Invalid hand")
        }
    }

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(label)
    }
}

private fun getDifferentHand(currentHands: Array<String>): String {
    val playableHands = handRanking.filter { !currentHands.contains(it) }

    return playableHands[playableHands.indices.random()]
}

@Composable
fun Game(modifier: Modifier = Modifier) {
    var hand by remember { mutableStateOf(generateRandomHand()) }

    val bestHand = getBestHand(hand)
    val secondHand = getDifferentHand(arrayOf(bestHand))
    val thirdHand = getDifferentHand(arrayOf(bestHand, secondHand))

    val handKinds = arrayOf(bestHand, secondHand, thirdHand)
    handKinds.shuffle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Hand(hand, modifier = Modifier.weight(1f).padding(top = 75.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(
                paddingValues = PaddingValues(
                    horizontal = 25.dp
                )
            ).padding(
                bottom = 150.dp
            )
        ) {
            handKinds.forEach {
                HandSelector(onClick = { hand = generateRandomHand() }, hand = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    ButtonTheme {
        Game()
    }
}