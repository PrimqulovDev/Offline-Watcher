package app.test.offlinewatcher.ui.screens.nav.page_my_videos

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import app.test.offlinewatcher.base.BaseViewModel
import app.test.offlinewatcher.repo.db.video.VideoEntity
import app.test.offlinewatcher.usecase.video.GetAllVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyVideosViewModel @Inject constructor(
    private val getAllVideos: GetAllVideosUseCase
) : BaseViewModel() {

    val videosLiveData: LiveData<List<VideoEntity>> = getAllVideos().asLiveData()
}