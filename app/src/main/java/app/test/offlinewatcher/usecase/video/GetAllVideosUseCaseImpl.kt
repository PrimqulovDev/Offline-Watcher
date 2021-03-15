package app.test.offlinewatcher.usecase.video

import app.test.offlinewatcher.repo.Repository
import app.test.offlinewatcher.repo.db.video.VideoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllVideosUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetAllVideosUseCase {
    override fun invoke(): Flow<List<VideoEntity>> = repository.getAllVideos()
}