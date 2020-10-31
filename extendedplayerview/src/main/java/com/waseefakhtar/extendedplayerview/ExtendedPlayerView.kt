package com.waseefakhtar.extendedplayerview

import android.content.Context
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.DimenRes
import com.google.android.exoplayer2.ui.PlayerView

class ExtendedPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr) {

    private var controllerVisibility = true
    private var mutePlayer = false
    private var playerCornerRadius = 0f

    init {
        attrs?.let {
            val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ExtendedPlayerView, 0, 0)
            try {
                controllerVisibility = a.getBoolean(R.styleable.ExtendedPlayerView_controllerVisibility, true)
                mutePlayer = a.getBoolean(R.styleable.ExtendedPlayerView_mutePlayer, false)
                playerCornerRadius = a.getDimension(R.styleable.ExtendedPlayerView_playerCornerRadius, 0f)
            } finally {
                a.recycle()
            }
        }
    }
}

fun ExtendedPlayerView.hideControllerVisibility() {
    setControllerVisibilityListener { visibility ->
        if (visibility == 0) {
            hideController()
        }
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