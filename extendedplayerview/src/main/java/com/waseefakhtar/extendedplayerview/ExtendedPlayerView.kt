package com.waseefakhtar.extendedplayerview

import android.content.Context
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.DimenRes
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class ExtendedPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr) {

    private var playerControllerVisibility = true
    var controllerVisibility
        get() = playerControllerVisibility
        set(value) {
            playerControllerVisibility = value
            if (!value) hideControllerVisibility()
        }

    private var playerCornerRadius = 0f

    private var mutePlayer = false
    var mute
        get() = mutePlayer
        set(value) {
            mutePlayer = value
            mute(value)
        }

    init {
        attrs?.let {
            val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ExtendedPlayerView, 0, 0)
            try {
                playerControllerVisibility = a.getBoolean(R.styleable.ExtendedPlayerView_controllerVisibility, true)
                mutePlayer = a.getBoolean(R.styleable.ExtendedPlayerView_mutePlayer, false)
                playerCornerRadius = a.getDimension(R.styleable.ExtendedPlayerView_playerCornerRadius, 0f)
            } finally {
                a.recycle()
            }
        }

        roundCornerRadius(playerCornerRadius)
        if (!playerControllerVisibility) {
            hideControllerVisibility()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mute(mutePlayer)
    }

    private fun mute(mute: Boolean) {
        if (player is SimpleExoPlayer) {
            val simpleExoPlayer = player as SimpleExoPlayer
            when (mute) {
                true -> simpleExoPlayer.volume = 0f
                false -> simpleExoPlayer.volume = 1f
            }
        }
    }

    private fun hideControllerVisibility() {
        setControllerVisibilityListener { visibility ->
            if (visibility == 0) {
                hideController()
            }
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

fun ExtendedPlayerView.roundCornerRadius(radius: Float) {
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