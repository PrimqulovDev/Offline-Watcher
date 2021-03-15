package app.test.offlinewatcher.usecase.save_video

import app.test.offlinewatcher.repo.db.video.VideoEntity

interface SaveVideoUseCase {
    suspend operator fun invoke(video: VideoEntity): Long
}