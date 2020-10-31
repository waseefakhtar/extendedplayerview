import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.DimenRes
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.waseefakhtar.extendedplayerview.ExtendedPlayerView

fun PlayerView.mute(mute: Boolean) {
    if (player is SimpleExoPlayer) {
        val simpleExoPlayer = player as SimpleExoPlayer
        when (mute) {
            true -> simpleExoPlayer.volume = 0f
            false -> simpleExoPlayer.volume = 1f
        }
    }
}

fun PlayerView.hideControllerVisibility() {
    setControllerVisibilityListener { visibility ->
        if (visibility == 0) {
            hideController()
        }
    }
}

fun ExtendedPlayerView.showControllerVisibility() {
    setControllerVisibilityListener { visibility ->
        if (visibility == 1) {
            showController()
        }
    }
}

fun PlayerView.roundCornerRadius(radius: Float) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(
                    0,
                    0,
                    view.width,
                    view.height,
                    radius
                )
            }
        }
        clipToOutline = true
    }
}

fun ExtendedPlayerView.roundCornerRadius(@DimenRes radius: Int) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(
                    0,
                    0,
                    view.width,
                    view.height,
                    view.resources.getDimension(radius)
                )
            }
        }
        clipToOutline = true
    }
}