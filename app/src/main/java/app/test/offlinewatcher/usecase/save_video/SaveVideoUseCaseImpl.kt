package app.test.offlinewatcher.usecase.save_video

import app.test.offlinewatcher.repo.Repository
import app.test.offlinewatcher.repo.db.video.VideoEntity
import javax.inject.Inject

class SaveVideoUseCaseImpl @Inject constructor(
    private val repository: Repository
) : SaveVideoUseCase {
    override suspend fun invoke(video: VideoEntity): Long = repository.saveVideo(video)
}