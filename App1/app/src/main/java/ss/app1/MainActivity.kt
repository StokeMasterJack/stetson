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
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.BottomAppBar
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import ss.app1.bj.GameController
import ss.app1.uiUtil.StetsonTheme
import ss.app1.util.CF

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

enum class TabKey {
    P1, P2, P3
}

@Composable
fun App() {

    val (page, setPage) = state<TabKey> { TabKey.P1 }

    val navVar = @Composable() {
        BottomAppBar() {
            Button(onClick = { setPage(TabKey.P1) }) { Text("P1") }
            Button(onClick = { setPage(TabKey.P2) }) { Text("P2") }
            Button(onClick = { setPage(TabKey.P3) }) { Text("P3") }
        }
    }

    val user = User("Fred")
    ProvideUser(user = user) {
        StetsonTheme {
            when (page) {
                TabKey.P1 -> Page1(navVar)
                TabKey.P2 -> Page2(navVar)
                TabKey.P3 -> Page3(navVar)
            }
        }
    }
}


@Composable
fun Page1(navBar: CF) {
    Scaffold(
        bottomAppBar = {
            navBar()
        },
        bodyContent = {
            GameController()
        }
    )
}

@Composable
fun Page2(navBar: CF) {
    Scaffold(
        bottomAppBar = {
            navBar()
        },
        bodyContent = {
            Box(
                gravity = ContentGravity.Center,
                modifier = Modifier.fillMaxSize(),
                backgroundColor = Color.Yellow
            ) {
                Text("Page 2")
            }
        }
    )


}

@Composable
fun Page3(navBar: CF) {
    Scaffold(
        bottomAppBar = {
            navBar()
        },
        bodyContent = {
            Box(
                gravity = ContentGravity.Center,
                modifier = Modifier.fillMaxSize(),
                backgroundColor = Color.Gray
            ) {
                Text("Page 3")
            }
        }
    )

}