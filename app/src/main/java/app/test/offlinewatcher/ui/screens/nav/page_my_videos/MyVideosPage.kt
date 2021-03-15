package app.test.offlinewatcher.ui.screens.nav.page_my_videos

import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import app.test.offlinewatcher.R
import app.test.offlinewatcher.base.BaseFragment
import app.test.offlinewatcher.repo.db.video.VideoEntity
import app.test.offlinewatcher.ui.BottomNavScreenDirections
import app.test.offlinewatcher.utils.extensions.activityNavController
import app.test.offlinewatcher.utils.extensions.decodeAndCopyTo
import app.test.offlinewatcher.utils.extensions.getCurrentTimeStamp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.page_downloads.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class MyVideosPage : BaseFragment(R.layout.page_downloads) {

    override val viewModel: MyVideosViewModel by viewModels()
    override val navController: NavController by activityNavController(R.id.navHostFragment)

    private val videosAdapter: VideosAdapter by lazy { VideosAdapter() }

    override fun initialize() {
        rvVideos.adapter = videosAdapter

        videosAdapter.onItemClickListener = { videoUrl ->
            val dir = BottomNavScreenDirections.globalOpenMedia(videoUrl)
            viewModel.navigateTo(dir)
        }

        viewModel.videosLiveData.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                videosAdapter.submitList(it)
                decodeAllVideos(it)
            }
        }
    }

    private fun decodeAllVideos(videos: List<VideoEntity>) {
        GlobalScope.launch(Dispatchers.IO) {
            videos.forEach {
                val filename = "${getCurrentTimeStamp()}${it.title}.mp4"
                val out = File(requireContext().cacheDir, filename)
                val input = File(it.localUrl)
                input.decodeAndCopyTo(out)
                it.decodedUrl = out.absolutePath
            }

            launch(Dispatchers.Main) {
                videosAdapter.submitList(videos)
                videosAdapter.notifyDataSetChanged()
            }

        }
    }
}