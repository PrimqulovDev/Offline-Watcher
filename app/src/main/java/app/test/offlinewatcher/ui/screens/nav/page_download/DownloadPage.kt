package app.test.offlinewatcher.ui.screens.nav.page_download

import android.content.Intent
import android.util.Patterns
import android.webkit.URLUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import app.test.offlinewatcher.R
import app.test.offlinewatcher.base.BaseFragment
import app.test.offlinewatcher.repo.db.video.VideoEntity
import app.test.offlinewatcher.repo.model.*
import app.test.offlinewatcher.services.DownloadService
import app.test.offlinewatcher.ui.BottomNavScreenDirections
import app.test.offlinewatcher.utils.Const.ACTION_START_DOWNLOADING
import app.test.offlinewatcher.utils.Const.ACTION_STOP_DOWNLOADING
import app.test.offlinewatcher.utils.Const.VIDEO_DATA
import app.test.offlinewatcher.utils.extensions.activityNavController
import app.test.offlinewatcher.utils.extensions.gone
import app.test.offlinewatcher.utils.extensions.stringText
import app.test.offlinewatcher.utils.extensions.visible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.page_download.*
import java.util.*

@AndroidEntryPoint
class DownloadPage : BaseFragment(R.layout.page_download) {

    override val viewModel: DownloadViewModel by viewModels()
    override val navController: NavController by activityNavController(R.id.navHostFragment)

    override fun initialize() {
        showDetails()
        btCheck.setOnClickListener {
            videoUrl = inputUrl.stringText
            videoTitle = URLUtil.guessFileName(videoUrl, null, null)
            if (videoTitle.isEmpty()) videoTitle = "no title video"

            hideKeyboard()
            if (checkUrl(videoUrl)) {
                isLoading = true
                viewModel.isUrlReachable(videoUrl).observe(viewLifecycleOwner) { reachable ->
                    isLoading = false
                    if (reachable) {
                        showDetails()
                        sendCommandToService(
                            ACTION_START_DOWNLOADING,
                            VideoData(videoUrl, videoTitle, null)
                        )
                    } else {
                        message("URL is not reachable")
                    }
                }
            }
        }

        DownloadService.apply {
            downloadingStatus.observe(viewLifecycleOwner) { status ->
                if (status != null) {
                    when (status) {
                        is Downloading -> {
                            llVideo.visible()
                            btCheck.gone()
                            inputUrl.gone()
                            tvPercentage.text = ("${status.percentage}%")
                            pbDownloading.progress = status.percentage
                        }

                        is Canceled -> {
                            llVideo.gone()
                            btCheck.visible()
                            inputUrl.visible()
                            inputUrl.setText("")
                            message(status.message)
                        }

                        is Success -> {

                        }

                        Paused -> {

                        }
                    }
                }
            }
        }

        btCancel.setOnClickListener {
            sendCommandToService(ACTION_STOP_DOWNLOADING)
        }

        btPause.setOnClickListener {

        }

        btResume.setOnClickListener {

        }

        btPlay.setOnClickListener {
            val dir = BottomNavScreenDirections.globalOpenMedia(videoUrl)
            viewModel.navigateTo(dir)
        }

    }


    private fun showDetails() {
        Glide.with(requireView())
            .load(videoUrl)
            .error(R.color.colorGreyB3)
            .placeholder(R.color.colorGreyB3)
            .into(ivThumbnail)

        tvTitle.text = videoTitle
        viewModel.getVideoDuration(videoUrl).observe(viewLifecycleOwner) {
            tvDuration.text = getVideoDurationString(it)
        }
    }

    override fun onDestroyView() {
        DownloadService.downloadingStatus.value = null
        super.onDestroyView()
    }

    private fun getVideoDurationString(duration: Long): String {
        var seconds = duration / 1000
        val hours = seconds / 3600
        seconds %= 3600
        val minutes = seconds / 60
        seconds %= 60
        val hoursStr = if (hours > 9) hours.toString() else "0$hours"
        val minutesStr = if (minutes > 9) minutes.toString() else "0$minutes"
        val secondsStr = if (seconds > 9) seconds.toString() else "0$seconds"

        return if (hours != 0L) {
            "$hoursStr:$minutesStr:$secondsStr"
        } else
            "$minutesStr:$secondsStr"
    }

    private fun checkUrl(url: String): Boolean {
        if (!Patterns.WEB_URL.matcher(url).matches()) {
            message("URL is not valid")
            return false
        }

        if (!url.toLowerCase(Locale.getDefault()).endsWith(".mp4")) {
            message("there is no mp4 file in this url")
            return false
        }
        return true
    }

    private fun sendCommandToService(command: String, videoData: VideoData? = null) {
        Intent(requireContext(), DownloadService::class.java).also {
            it.action = command
            it.putExtra(VIDEO_DATA, videoData)
            requireContext().startService(it)
        }
    }

    private var videoTitle = ""
    private var videoUrl = ""

}