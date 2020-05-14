package ss.app1.uiUtil

import android.content.Context
import android.content.res.Configuration
import androidx.compose.Composable
import androidx.lifecycle.LifecycleOwner
import androidx.ui.core.ClipboardManagerAmbient
import androidx.ui.core.ConfigurationAmbient
import androidx.ui.core.ContextAmbient
import androidx.ui.core.LifecycleOwnerAmbient
import androidx.ui.core.clipboard.ClipboardManager
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Typography
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette
import androidx.ui.text.font.FontFamily


//val daveColorsLight = c
val daveColorsDark = darkColorPalette()


//data class Foo()
object Theme {


    @Composable
    val colors
        get() = MaterialTheme.colors

    @Composable
    val typography
        get() = MaterialTheme.typography

    @Composable
    operator fun component1() = colors

    @Composable
    operator fun component2() = typography


}

@Composable
fun StetsonTheme(children: @Composable() () -> Unit) {
    MaterialTheme(
        colors = lightColorPalette(primary = Color.LightGray, secondary = Color.Green),
        typography = Typography(defaultFontFamily = FontFamily.Monospace)
    ) {
        children()
    }
}

object Sys {

    @Composable
    val ctx: Context
        get() = ContextAmbient.current

    @Composable
    val cfg: Configuration
        get() = ConfigurationAmbient.current

    @Composable
    val own: LifecycleOwner
        get() = LifecycleOwnerAmbient.current

    @Composable
    val clp: ClipboardManager
        get() = ClipboardManagerAmbient.current

}
