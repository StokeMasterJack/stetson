package ss.app1.bj

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ss.app1.AppScaffold
import ss.app1.TabKey
import ss.app1.UserAmbient
import ss.app1.model.Game
import ss.app1.model.Hand
import ss.app1.uiUtil.StetsonTheme
import ss.app1.uiUtil.Theme

enum class BjAction { Hit, Deal, Stay }

val BjDispatchAmbient = ambientOf<BjDispatch> { error("Not defined") }

fun reducer(game: Game, action: BjAction): Game {
    val g = game.copy()

    when (action) {
        BjAction.Hit -> g.hit()
        BjAction.Stay -> g.stay()
        BjAction.Deal -> g.deal()
    }

    return g
}

typealias BjDispatch = (BjAction) -> Unit


@Composable
fun BlackjackPage(page: TabKey, setPage: (TabKey) -> Unit) {
    val (c, t) = Theme
    AppScaffold(page = page, setPage = setPage) {
        GameController()
    }
}



@Composable
fun GameController() {

    val (game, setGame) = state { Game() }

//    fun dispatch(action: BjAction) {
//        val newGame = reducer(game, action)
//        setGame(newGame)
//    }

    val dispatch: BjDispatch = { action ->
        val newGame = reducer(game, action)
        setGame(newGame)
    }

    Providers(BjDispatchAmbient provides dispatch) {
        GameVu(g = game)
    }

}

@Composable
fun ButtonBar(isGameOver: Boolean) {
    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Btn(BjAction.Deal, enabled = isGameOver)
        Btn(BjAction.Hit, enabled = !isGameOver)
        Btn(BjAction.Stay, enabled = !isGameOver)
    }
}

@Composable
fun Btn(a: BjAction, enabled: Boolean = true) {

    val (_, t) = Theme
    val tStyle = if (enabled) t.button else t.button.copy(color = t.button.color.copy(alpha = .5f))
    val d = BjDispatchAmbient.current
    Box(padding = 5.dp) {
        Button(enabled = enabled, onClick = { d(a) }) { Text(a.name, style = tStyle) }
    }

}

@Composable
fun GameVu(g: Game = Game()) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            gravity = ContentGravity.Center
        ) {
            ButtonBar(g.isGameOver)
        }
        HandsVu(g)
        GameMsg(msg = g.msg)
    }
}

@Composable
fun HandsVu(g: Game) {
    Box(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    paddingStart = 10.dp,
                    paddingTop = 10.dp,
                    paddingEnd = 5.dp,
                    paddingBottom = 10.dp
                ) {
                    HandVu(h = g.ph)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    paddingStart = 5.dp,
                    paddingTop = 10.dp,
                    paddingEnd = 10.dp,
                    paddingBottom = 10.dp
                ) {
                    HandVu(h = g.dh)
                }
            }
        }

    }
}


@Composable
fun HandVu(h: Hand) {
    val (c, t) = Theme
    Box(backgroundColor = c.secondary, padding = 10.dp, modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {
                Text(text = h.name, style = t.h5)
            }
            Box(paddingTop = 5.dp, paddingBottom = 5.dp) {
                Column(modifier = Modifier.preferredHeight(100.dp)) {
                    h.cards.forEach {
                        Row {
                            Text(it.name)
                        }
                    }
                }
            }
            Box {
                Text(
                    text = "${h.points} Points",
                    style = t.subtitle1.copy(fontWeight = FontWeight.Bold)
                )
            }

        }

    }
}

@Composable
fun GameMsg(msg: String) {
    val (_, t) = Theme
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            paddingTop = 5.dp,
            paddingBottom = 2.dp,
            gravity = ContentGravity.Center
        ) {

            Text(
                text = msg,
                style = t.h5
            )

        }
        Box(
            modifier = Modifier.fillMaxWidth(),
//            backgroundColor = c.primary,
            paddingBottom = 5.dp,
            paddingTop = 2.dp,
            gravity = ContentGravity.Center
        ) {
            Text(UserAmbient.current.userName)
        }

    }
}

@Preview
@Composable
fun DefaultPreview() {
    StetsonTheme {
        GameVu()
    }
}