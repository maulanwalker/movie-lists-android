package com.example.movielists

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.movielists.databinding.FragmentYoutubePlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Suppress("DEPRECATION")
class YoutubePlayerFragment : Fragment() {
    private var _binding : FragmentYoutubePlayerBinding? = null
    private val binding get() = _binding!!
    private var isFullscreen = false
    private lateinit var youtubeID : String
    private lateinit var youtubePlayer : YouTubePlayer
    private lateinit var videoPlayer : YouTubePlayerView
    private lateinit var fullscreenViewContainer : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            youtubeID = it.getString("id_youtube").toString()
        }
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYoutubePlayerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        videoPlayer = binding.videoPlayer
        fullscreenViewContainer = binding.fullScreenViewContainer

        lifecycle.addObserver(videoPlayer)
        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1).fullscreen(1).build()

        videoPlayer.enableAutomaticInitialization = false
        videoPlayer.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullscreen = true

                videoPlayer.visibility = View.GONE
                fullscreenViewContainer.visibility = View.VISIBLE
                fullscreenViewContainer.addView(fullscreenView)

                activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onExitFullscreen() {
                isFullscreen = false

                videoPlayer.visibility = View.VISIBLE
                fullscreenViewContainer.visibility = View.GONE
                fullscreenViewContainer.removeAllViews()
                activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        })

        videoPlayer.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // loading the selected video into the YouTube Player
                this@YoutubePlayerFragment.youtubePlayer = youTubePlayer
                youTubePlayer.loadVideo(youtubeID, 0F)
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                super.onStateChange(youTubePlayer, state)
            }
        }, iFramePlayerOptions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}