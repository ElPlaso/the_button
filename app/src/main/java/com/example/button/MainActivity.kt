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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.button.data.CardData
import com.example.button.data.Game
import com.example.button.model.Card
import com.example.button.model.Hand
import com.example.button.model.Rank
import com.example.button.model.Suit
import com.example.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonApp()
                }
            }
        }
    }
}

@VisibleForTesting
internal fun getCardImage(card: Card): Int {
    return when (card.suit) {
        Suit.CLUBS -> getClubsImage(card.rank)
        Suit.DIAMONDS -> getDiamondsImage(card.rank)
        Suit.HEARTS -> getHeartsImage(card.rank)
        Suit.SPADES -> getSpadesImage(card.rank)
    }
}

internal fun getClubsImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO -> R.drawable.two_of_clubs
        Rank.THREE -> R.drawable.three_of_clubs
        Rank.FOUR -> R.drawable.four_of_clubs
        Rank.FIVE -> R.drawable.five_of_clubs
        Rank.SIX -> R.drawable.six_of_clubs
        Rank.SEVEN -> R.drawable.seven_of_clubs
        Rank.EIGHT -> R.drawable.eight_of_clubs
        Rank.NINE -> R.drawable.nine_of_clubs
        Rank.TEN -> R.drawable.ten_of_clubs
        Rank.JACK -> R.drawable.jack_of_clubs
        Rank.QUEEN -> R.drawable.queen_of_clubs
        Rank.KING -> R.drawable.king_of_clubs
        Rank.ACE -> R.drawable.ace_of_clubs
    }
}

internal fun getDiamondsImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO -> R.drawable.two_of_diamonds
        Rank.THREE -> R.drawable.three_of_diamonds
        Rank.FOUR -> R.drawable.four_of_diamonds
        Rank.FIVE -> R.drawable.five_of_diamonds
        Rank.SIX -> R.drawable.six_of_diamonds
        Rank.SEVEN -> R.drawable.seven_of_diamonds
        Rank.EIGHT -> R.drawable.eight_of_diamonds
        Rank.NINE -> R.drawable.nine_of_diamonds
        Rank.TEN -> R.drawable.ten_of_diamonds
        Rank.JACK -> R.drawable.jack_of_diamonds
        Rank.QUEEN -> R.drawable.queen_of_diamonds
        Rank.KING -> R.drawable.king_of_diamonds
        Rank.ACE -> R.drawable.ace_of_diamonds
    }
}

internal fun getHeartsImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO -> R.drawable.two_of_hearts
        Rank.THREE -> R.drawable.three_of_hearts
        Rank.FOUR -> R.drawable.four_of_hearts
        Rank.FIVE -> R.drawable.five_of_hearts
        Rank.SIX -> R.drawable.six_of_hearts
        Rank.SEVEN -> R.drawable.seven_of_hearts
        Rank.EIGHT -> R.drawable.eight_of_hearts
        Rank.NINE -> R.drawable.nine_of_hearts
        Rank.TEN -> R.drawable.ten_of_hearts
        Rank.JACK -> R.drawable.jack_of_hearts
        Rank.QUEEN -> R.drawable.queen_of_hearts
        Rank.KING -> R.drawable.king_of_hearts
        Rank.ACE -> R.drawable.ace_of_hearts
    }
}

internal fun getSpadesImage(rank: Rank): Int {
    return when (rank) {
        Rank.TWO -> R.drawable.two_of_spades
        Rank.THREE -> R.drawable.three_of_spades
        Rank.FOUR -> R.drawable.four_of_spades
        Rank.FIVE -> R.drawable.five_of_spades
        Rank.SIX -> R.drawable.six_of_spades
        Rank.SEVEN -> R.drawable.seven_of_spades
        Rank.EIGHT -> R.drawable.eight_of_spades
        Rank.NINE -> R.drawable.nine_of_spades
        Rank.TEN -> R.drawable.ten_of_spades
        Rank.JACK -> R.drawable.jack_of_spades
        Rank.QUEEN -> R.drawable.queen_of_spades
        Rank.KING -> R.drawable.king_of_spades
        Rank.ACE -> R.drawable.ace_of_spades
    }
}

@Composable
fun CardImage(card: Card, modifier: Modifier = Modifier) {
    val cardImage = getCardImage(card)

    Image(
        painter = painterResource(cardImage),
        contentDescription = card.rank.toString().lowercase() + "of" + card.suit,
        contentScale = ContentScale.Fit,
        modifier = modifier,
    )

}

@Composable
fun Hand(hand: Array<Card>, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        hand.forEach {
            val card = it
            CardImage(
                card, modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
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

@Composable
fun Game(modifier: Modifier = Modifier) {
    val game = Game
    var hand by rememberSaveable { mutableStateOf(CardData().generateRandomHand()) }

    val bestHand = CardData().getBestHand(hand)
    val secondHand = CardData().getDifferentHand(arrayOf(bestHand))
    val thirdHand = CardData().getDifferentHand(arrayOf(bestHand, secondHand))

    val handKinds = arrayOf(bestHand, secondHand, thirdHand)
    handKinds.shuffle()

    var score by rememberSaveable { mutableIntStateOf(game.getScore()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 15.dp)
            .padding(top = 15.dp)
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
                    game.restart()
                    score = game.getScore()
                    hand = CardData().generateRandomHand()
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
                    game.checkSelectedHand(it, bestHand)
                    score = game.getScore()
                    hand = CardData().generateRandomHand()
                }, hand = it)
            }
        }
    }
}

@Composable
fun ButtonApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    )
    {
        Game()
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    ButtonTheme {
        Game()
    }
}