package app.test.offlinewatcher.ui.screens.nav.page_download

import app.test.offlinewatcher.base.BaseViewModel
import app.test.offlinewatcher.repo.db.video.VideoEntity
import app.test.offlinewatcher.usecase.save_video.SaveVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    val save: SaveVideoUseCase
) : BaseViewModel() {
    fun saveVideo(video: VideoEntity) = launch { save(video) }
}