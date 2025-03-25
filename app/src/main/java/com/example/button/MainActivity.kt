package com.example.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

enum class Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

enum class Hand {
    STRAIGHT_FLUSH, QUADS, FULL_HOUSE, FLUSH, STRAIGHT, TRIPS, TWO_PAIR, PAIR, HIGH_CARD
}

@VisibleForTesting
internal fun getCardImage(suit: Suit, rank: Rank): Int {
    return when (suit) {
        Suit.CLUBS -> getClubsImage(rank)
        Suit.DIAMONDS -> getDiamondsImage(rank)
        Suit.HEARTS -> getHeartsImage(rank)
        Suit.SPADES -> getSpadesImage(rank)
    }
}

internal fun getClubsImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO-> R.drawable.two_of_clubs
        Rank.THREE-> R.drawable.three_of_clubs
        Rank.FOUR-> R.drawable.four_of_clubs
        Rank.FIVE-> R.drawable.five_of_clubs
        Rank.SIX-> R.drawable.six_of_clubs
        Rank.SEVEN-> R.drawable.seven_of_clubs
        Rank.EIGHT-> R.drawable.eight_of_clubs
        Rank.NINE-> R.drawable.nine_of_clubs
        Rank.TEN-> R.drawable.ten_of_clubs
        Rank.JACK-> R.drawable.jack_of_clubs
        Rank.QUEEN-> R.drawable.queen_of_clubs
        Rank.KING-> R.drawable.king_of_clubs
        Rank.ACE-> R.drawable.ace_of_clubs
    }
}

internal fun getDiamondsImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO-> R.drawable.two_of_diamonds
        Rank.THREE-> R.drawable.three_of_diamonds
        Rank.FOUR-> R.drawable.four_of_diamonds
        Rank.FIVE-> R.drawable.five_of_diamonds
        Rank.SIX-> R.drawable.six_of_diamonds
        Rank.SEVEN-> R.drawable.seven_of_diamonds
        Rank.EIGHT-> R.drawable.eight_of_diamonds
        Rank.NINE-> R.drawable.nine_of_diamonds
        Rank.TEN-> R.drawable.ten_of_diamonds
        Rank.JACK-> R.drawable.jack_of_diamonds
        Rank.QUEEN-> R.drawable.queen_of_diamonds
        Rank.KING-> R.drawable.king_of_diamonds
        Rank.ACE-> R.drawable.ace_of_diamonds
    }
}

internal fun getHeartsImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO-> R.drawable.two_of_hearts
        Rank.THREE-> R.drawable.three_of_hearts
        Rank.FOUR-> R.drawable.four_of_hearts
        Rank.FIVE-> R.drawable.five_of_hearts
        Rank.SIX-> R.drawable.six_of_hearts
        Rank.SEVEN-> R.drawable.seven_of_hearts
        Rank.EIGHT-> R.drawable.eight_of_hearts
        Rank.NINE-> R.drawable.nine_of_hearts
        Rank.TEN-> R.drawable.ten_of_hearts
        Rank.JACK-> R.drawable.jack_of_hearts
        Rank.QUEEN-> R.drawable.queen_of_hearts
        Rank.KING-> R.drawable.king_of_hearts
        Rank.ACE-> R.drawable.ace_of_hearts
    }
}

internal fun getSpadesImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO-> R.drawable.two_of_spades
        Rank.THREE-> R.drawable.three_of_spades
        Rank.FOUR-> R.drawable.four_of_spades
        Rank.FIVE-> R.drawable.five_of_spades
        Rank.SIX-> R.drawable.six_of_spades
        Rank.SEVEN-> R.drawable.seven_of_spades
        Rank.EIGHT-> R.drawable.eight_of_spades
        Rank.NINE-> R.drawable.nine_of_spades
        Rank.TEN-> R.drawable.ten_of_spades
        Rank.JACK-> R.drawable.jack_of_spades
        Rank.QUEEN-> R.drawable.queen_of_spades
        Rank.KING-> R.drawable.king_of_spades
        Rank.ACE-> R.drawable.ace_of_spades
    }
}

@Composable
fun CardImage(suit: Suit, rank: Rank, modifier: Modifier = Modifier) {
    val cardImage = getCardImage(suit, rank)

    Image(
        painter = painterResource(cardImage),
        contentDescription = rank.toString().lowercase() + "of" + suit,
        contentScale = ContentScale.Fit,
        modifier = modifier,
    )

}

private fun generateRandomCard(previousCards: Array<Pair<Suit, Rank>>): Pair<Suit, Rank> {
    val suits = enumValues<Suit>()
    val ranks = enumValues<Rank>()

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
internal fun generateRandomHand(): Array<Pair<Suit, Rank>> {
    val hand: MutableList<Pair<Suit, Rank>> = mutableListOf()

    repeat((0..4).count()) {
        val randomCard = generateRandomCard(hand.toTypedArray())
        hand += randomCard
    }

    return hand.toTypedArray()
}

@Composable
fun Hand(hand: Array<Pair<Suit, Rank>>, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
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

val rankValues: Map<Rank, Int> = mapOf(
    Pair(Rank.TWO, 2),
    Pair(Rank.THREE, 3),
    Pair(Rank.FOUR, 4),
    Pair(Rank.FIVE, 5),
    Pair(Rank.SIX, 6),
    Pair(Rank.SEVEN, 7),
    Pair(Rank.EIGHT, 8),
    Pair(Rank.NINE, 9),
    Pair(Rank.TEN, 10),
    Pair(Rank.JACK, 11),
    Pair(Rank.QUEEN, 12),
    Pair(Rank.KING, 13),
    Pair(Rank.ACE, 14),
)

private val cardComparator = Comparator<Pair<Suit, Rank>> { a, b ->
    val aValue = rankValues[a.second] ?: throw Error("Invalid rank")
    val bValue = rankValues[b.second] ?: throw Error("Invalid rank")

    when {
        (aValue == bValue) -> 0
        (aValue < bValue) -> -1
        else -> 1
    }
}

@VisibleForTesting
internal fun getBestHand(hand: Array<Pair<Suit, Rank>>): Hand {
    // Check for rank occurrences
    val rankOccurrences: MutableMap<Rank, Int> = mutableMapOf()
    hand.forEach {
        val newOccurrence = rankOccurrences[it.second]?.plus(1) ?: 1
        rankOccurrences[it.second] = newOccurrence
    }

    // Check for flush
    var seenSuit: Suit? = null
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
        return Hand.STRAIGHT_FLUSH
    }

    if (rankOccurrences.values.contains(4)) {
        return Hand.QUADS
    }

    if (rankOccurrences.values.contains(3) && rankOccurrences.values.contains(2)) {
        return Hand.FULL_HOUSE
    }

    if (isFlush) {
        return Hand.FLUSH
    }

    if (isStraight) {
        return Hand.STRAIGHT
    }

    if (rankOccurrences.values.contains(3)) {
        return Hand.TRIPS
    }

    if (rankOccurrences.values.contains(2)) {
        if (rankOccurrences.values.filter { it == 2 }.size == 2) {
            return Hand.TWO_PAIR
        }

        return Hand.PAIR
    }

    return Hand.HIGH_CARD
}

@Composable
fun HandSelector(modifier: Modifier = Modifier, hand: Hand, onClick: () -> Unit) {
    val label = when (hand) {
        Hand.STRAIGHT_FLUSH -> stringResource(R.string.straight_flush)
        Hand.QUADS -> stringResource(R.string.quads)
        Hand.FULL_HOUSE -> stringResource(R.string.full_house)
        Hand.FLUSH -> stringResource(R.string.flush)
        Hand.STRAIGHT -> stringResource(R.string.straight)
        Hand.TRIPS -> stringResource(R.string.trips)
        Hand.TWO_PAIR -> stringResource(R.string.two_pair)
        Hand.PAIR -> stringResource(R.string.pair)
        Hand.HIGH_CARD -> stringResource(R.string.high_card)
    }

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(label)
    }
}

private fun getDifferentHand(currentHands: Array<Hand>): Hand {
    val playableHands = enumValues<Hand>().filter { !currentHands.contains(it) }

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

    var score by remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 15.dp)
            .padding(top = 50.dp)
            .padding(bottom = 150.dp),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
            ) {
                Text(
                    stringResource(R.string.title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                TextButton(onClick = {
                    score = 0
                    hand = generateRandomHand()
                }) {
                    Text(stringResource(R.string.restart))
                }
            }
            Text(stringResource(R.string.score, score))
        }
        Hand(
            hand, modifier = Modifier
                .weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            handKinds.forEach {
                HandSelector(onClick = {
                    if (it == bestHand) {
                        score++

                        if (score == 23456) {
                            score = 0
                        }
                    } else if (score != 0) {
                        score--
                    }
                    hand = generateRandomHand()
                }, hand = it)
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