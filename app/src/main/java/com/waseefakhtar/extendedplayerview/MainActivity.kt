package com.waseefakhtar.extendedplayerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {

    private lateinit var playerView: ExtendedPlayerView
    private var player: SimpleExoPlayer? = null

    private val mp4Url = "https://html5demos.com/assets/dizzy.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        playerView = findViewById(R.id.player_view)
        playerView.setOnClickListener {
            when (player?.isPlaying) {
                true -> player?.playWhenReady = false
                false -> player?.playWhenReady = true
            }
        }

        // Setting properties programmatically.
        playerView.roundCornerRadius(84f)
        playerView.mute = true
        playerView.hideControllerVisibility()
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

        player?.playWhenReady = true
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