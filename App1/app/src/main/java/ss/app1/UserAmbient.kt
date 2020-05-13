package ss.app1

import androidx.compose.Composable
import androidx.compose.Providers
import androidx.compose.ambientOf

data class User(val userName: String)

val UserAmbient = ambientOf<User> { error("No user yet") }
//val UserAmbient = ambientOf<User?> { null }

@Composable
fun ProvideUser(user: User, children: @Composable() () -> Unit) {
    Providers(UserAmbient provides user) {
        children()
    }
}

//@Composable
//fun App1() {
//    val user = User("fred")
//    Providers(UserAmbient provides user) {
//        GameVu()
//    }
//}



