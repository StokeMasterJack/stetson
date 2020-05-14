package ss.app1.http

import androidx.compose.Composable
import androidx.compose.onActive
import androidx.compose.state
import androidx.lifecycle.lifecycleScope
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.ListItem
import androidx.ui.res.vectorResource
import kotlinx.coroutines.launch
import ss.app1.AppScaffold
import ss.app1.L
import ss.app1.R
import ss.app1.TabKey
import ss.app1.uiUtil.Sys
import ss.app1.uiUtil.Theme

@Composable
fun HttpPage(page: TabKey, setPage: (TabKey) -> Unit) {
    val (c, t) = Theme
    AppScaffold(page = page, setPage = setPage) {
        Box(
            gravity = ContentGravity.TopCenter,
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color.Yellow
        ) {
            RetroController()
        }
    }
}


@Composable
fun RetroController() {
//    val users = listOf<User>(
//        User(1, "dford", "bla"),
//        User(2, "foo", "zoo")
//    )

    val ss = state<UiState<List<User>>> { UiState.NotStarted }
    val (uiState, setUiState) = ss


    val lifecycleOwner = Sys.own

    onActive {
        lifecycleOwner.lifecycleScope.launch {
            val s = Retro.fetchUsers()
            setUiState(s)
        }

        onDispose {
            //cleanUp
        }

    }

    RetroVu(uiState)

}

@Composable
fun RetroVu(uiState: UiState<List<User>>) {

    val name = Sys.ctx.resources.getString(R.string.app_name)

    when (uiState) {
        is UiState.NotStarted -> CircularProgressIndicator()
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Success -> {
//            UsersVu(users = uiState.data)
            UsersVuLazy(users = uiState.data)
        }
        is UiState.Error -> {
            L.w("Problem fetching users", uiState.exception)
            Text(text = "Error: ${uiState.exception}")
        }
    }
}

@Composable
fun UsersVu(users: List<User>) {
    VerticalScroller {
        Column() {
            users.forEach {
                ListItem(
                    text = { Text(it.login) },
                    icon = { Icon(vectorResource(R.drawable.ic_home)) },
                    secondaryText = { Text(it.url) }
                )
            }
        }
    }
}

@Composable
fun UsersVuLazy(users: List<User>) {
    AdapterList(data = users) {
        ListItem(
            text = { Text(it.login) },
            icon = { Icon(vectorResource(R.drawable.ic_home)) },
            secondaryText = { Text(it.url) }
        )
    }
}