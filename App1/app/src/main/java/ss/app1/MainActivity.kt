package ss.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.setContent
import ss.app1.bj.BlackjackPage
import ss.app1.http.HttpPage
import ss.app1.prefs.PrefPage
import ss.app1.uiUtil.StetsonTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appName: String = resources.getString(R.string.app_name)

        setContent {
            App()
        }
    }
}

enum class TabKey(val title: String, val iconId: Int) {
    Blackjack("A fun game", R.drawable.ic_home),
    Http("Fun with HTTP", R.drawable.ic_home),
    Pref("I am resourceful", R.drawable.ic_home)
}

@Composable
fun App() {

    val (page, setPage) = state<TabKey> { TabKey.Blackjack }

    val user = User("Fred")
    ProvideUser(user = user) {
        StetsonTheme {
            when (page) {
                TabKey.Blackjack -> BlackjackPage(page, setPage)
                TabKey.Http -> HttpPage(page, setPage)
                TabKey.Pref -> PrefPage(page, setPage)
            }
        }
    }
}


