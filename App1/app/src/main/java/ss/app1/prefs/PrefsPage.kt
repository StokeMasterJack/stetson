package ss.app1.prefs

import android.content.SharedPreferences
import androidx.compose.Composable
import androidx.compose.state
import androidx.preference.PreferenceManager
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.material.Switch
import androidx.ui.unit.dp
import ss.app1.AppScaffold
import ss.app1.TabKey
import ss.app1.uiUtil.Sys
import ss.app1.uiUtil.Theme

//Retro
//Room

@Composable
fun PrefPage(page: TabKey, setPage: (TabKey) -> Unit) {
    val (c, t) = Theme
    AppScaffold(page = page, setPage = setPage) {
        Box(
            gravity = ContentGravity.Center,
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color.Gray
        ) {
            PrefController()
        }
    }
}


fun SharedPreferences.tx(f: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.f()
    editor.apply()
}

fun SharedPreferences.putBool(name: String, value: Boolean) = tx {
    putBoolean(name, value)
}


fun SharedPreferences.putStr(name: String, value: String) = tx {
    putString(name, value)
}

object PrefKeys {
    const val blonds = "blonds"    //type: bool/str.  defVal, label:String
    const val userName = "userName"
}

@Composable
fun PrefController() {

    val ctx = Sys.ctx

    val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)

    val (blonds, setBlond) = state { prefs.getBoolean(PrefKeys.blonds, false) }
    val (userName, setUserName) = state { prefs.getString(PrefKeys.userName, "") ?: "JohnDoe" }

    val onSaveBlonds: (Boolean) -> Unit = {
        prefs.putBool(PrefKeys.blonds, it)
        setBlond(it)
    }

    val onSaveUserName: (String) -> Unit = {
        prefs.putStr(PrefKeys.userName, it)
        setUserName(it)
    }

    PrefsView(blonds, onSaveBlonds, userName, onSaveUserName)

}

@Composable
fun PrefsView(
    blonds: Boolean,
    onSaveBlonds: (Boolean) -> Unit,
    userName: String,
    onSaveUserName: (String) -> Unit
) {

    val (tfv, setTfv) = state { TextFieldValue(userName) }

    Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = Alignment.CenterVertically
        ) {
            Text("Blonds")
            Switch(checked = blonds, onCheckedChange = onSaveBlonds)
        }
        Spacer(modifier = Modifier.heightIn(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = Alignment.CenterVertically
        ) {
            Text("UserName")
            Spacer(modifier = Modifier.width(10.dp))
            Surface(color = Color.White) {
                TextField(
                    modifier = Modifier.padding(10.dp).preferredWidth(200.dp),
                    value = tfv,
                    onValueChange = {
                        setTfv(it)
                        onSaveUserName(it.text)
                    })
            }

        }
    }

}