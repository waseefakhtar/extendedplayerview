package com.waseefakhtar.extendedplayerview.sample

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.util.Util
import com.waseefakhtar.extendedplayerview.ExtendedPlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var playerView: ExtendedPlayerView
    private lateinit var playIconView: FrameLayout
    private var player: SimpleExoPlayer? = null

    private val mp4Url = "https://assets.mixkit.co/videos/preview/mixkit-set-of-plateaus-seen-from-the-heights-in-a-sunset-26070-large.mp4"
    private val mp4Url2 = "https://assets.mixkit.co/videos/preview/mixkit-virgin-lonely-beach-with-a-palapa-1958-large.mp4"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        playerView = findViewById(R.id.player_view)
        playerView.setOnClickListener {
            when (player?.isPlaying) {
                true -> {
                    player?.playWhenReady = false
                    fadeIn()
                }
                false -> {
                    player?.playWhenReady = true
                    fadeOut()
                }
            }
        }

        // Setting properties programmatically.
        playerView.cornerRadius = 84f
        playerView.mute = true
        playerView.controllerVisibility = false
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

        playIconView = findViewById(R.id.play_icon_view)
    }

    private fun fadeIn() {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        playIconView.startAnimation(fadeIn)
        playIconView.visibility = View.VISIBLE
    }

    private fun fadeOut() {
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        playIconView.startAnimation(fadeOut)
        playIconView.visibility = View.INVISIBLE
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player

        val mediaItem: MediaItem = MediaItem.fromUri(mp4Url)
        player?.setMediaItem(mediaItem)

        player?.prepare()
        player?.volume
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let {
            it.release()
            player = null
        }
    }
}