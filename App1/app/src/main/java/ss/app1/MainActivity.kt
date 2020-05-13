package ss.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
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
            GameVu()
        }
    }
}

@Composable
fun GameVu(g: Game = Game()) {

    val (c, t) = Theme

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Yellow
        ) {
            Text("Button Bar")
        }
        Box(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        paddingStart = 10.dp,
                        paddingTop = 10.dp,
                        paddingEnd = 5.dp,
                        paddingBottom = 10.dp){
                        HandVu(h = g.ph)
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        paddingStart = 5.dp,
                        paddingTop = 10.dp,
                        paddingEnd = 10.dp,
                        paddingBottom = 10.dp) {
                        HandVu(h = g.dh)
                    }
                }
            }

        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = c.primary,
            padding = 10.dp,
            gravity = ContentGravity.Center
        ) {
            Text(
                text = g.msg,
                style = t.h5
            )
        }
    }
}

@Composable
fun HandVu(h: Hand) {
    val (c, t) = Theme
    Box(backgroundColor = c.secondary,padding = 10.dp,modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {
                Text(text = h.name, style = t.h5)
            }
            Box {
                Column {
                    h.cards.forEach {
                        Row {
                            Text(it.name)
                        }
                    }
                }
            }
            Box {
                Text(text = "${h.points} Points")
            }
            Box {
                Text(UserAmbient.current.userName)
            }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    App()
}