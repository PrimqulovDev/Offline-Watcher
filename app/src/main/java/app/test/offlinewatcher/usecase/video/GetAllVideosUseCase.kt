package app.test.offlinewatcher.usecase.video

import app.test.offlinewatcher.repo.db.video.VideoEntity
import kotlinx.coroutines.flow.Flow

interface GetAllVideosUseCase {
    operator fun invoke(): Flow<List<VideoEntity>>
}