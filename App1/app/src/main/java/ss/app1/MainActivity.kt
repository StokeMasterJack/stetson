package ss.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.Button
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ss.app1.model.Game
import ss.app1.model.Hand
import ss.app1.uiUtil.StetsonTheme
import ss.app1.uiUtil.Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

//@Composable
//fun App() {
//    val user = User("Fred")
//    ProvideUser(user = user) {
//        MaterialTheme(colors =  lightColorPalette(primary = Color.Cyan)) {
//            GameVu()
//        }
//    }
//}

@Composable
fun App() {
    val user = User("Fred")
    ProvideUser(user = user) {
        StetsonTheme {
            GameController()
        }
    }
}

enum class BjAction { Hit, Deal, Stay }

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
fun GameController() {

    val (game, setGame) = state { Game() }

    fun dispatch(action: BjAction) {
        val newGame = reducer(game, action)
        setGame(newGame)
    }


    GameVu(g = game, dispatch = ::dispatch)

}

@Composable
fun ButtonBar(dispatch: BjDispatch) {
    Row {
        Button(onClick = { dispatch(BjAction.Deal) }) { Text("Deal") }
        Button(onClick = { dispatch(BjAction.Hit) }) { Text("Hit") }
        Button(onClick = { dispatch(BjAction.Stay) }) { Text("Stay") }
    }
}

@Composable
fun GameVu(g: Game, dispatch: BjDispatch) {

    val (c, t) = Theme

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Yellow,
            gravity =  ContentGravity.Center
        ) {
            ButtonBar(dispatch = dispatch)
        }
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
        GameMsg(msg = g.msg)
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
                Column {
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
    val (c, t) = Theme
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = c.primary,
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
            backgroundColor = c.primary,
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
    App()
}