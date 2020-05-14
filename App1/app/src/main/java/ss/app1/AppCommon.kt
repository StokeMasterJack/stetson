package ss.app1

import android.util.Log
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import ss.app1.uiUtil.Theme
import ss.app1.util.CF


@Composable
fun AppScaffold(page: TabKey, setPage: (TabKey) -> Unit, body: CF) {
    val (c, t) = Theme

    val scaffoldState: ScaffoldState = remember { ScaffoldState() }
//    val scaffoldState: ScaffoldState = remember(id) { Calc(x,y) }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Column {
                Box(
                    gravity = ContentGravity.CenterEnd,
                    backgroundColor = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Closed }) {
                        Icon(vectorResource(id = R.drawable.ic_close))
                    }
                }
                Divider(color = c.secondary)
                TabKey.values().forEach {
                    ListItem(
                        text = { Text(it.name) },
                        icon = { Icon(vectorResource(it.iconId)) },
                        secondaryText = { Text(it.title) },
                        onClick = { setPage(it) }
                    )
                    Divider(color = c.secondary)
                }
            }
        },

        topAppBar = {
            TopAppBar(
                title = { Text(text = page.title, style = t.subtitle1) },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                        Icon(vectorResource(id = R.drawable.ic_menu))
                    }
                }
            )
        },

        bottomAppBar = {
            BottomNavigation {
                TabKey.values().forEach {
                    BottomNavigationItem(
                        icon = { Icon(vectorResource(id = it.iconId)) },
                        selected = page == it,
                        onSelected = { setPage(it) },
                        text = { Text(it.name) }
                    )
                }
            }
        },
        bodyContent = {
            body()
        }
    )
}

object L {

    const val TAG = "App1"

    fun w(msg: String, e: Throwable) {
        Log.w(TAG, msg, e)
    }

    fun w(msg: String) {
        Log.w(TAG, msg)
    }

}