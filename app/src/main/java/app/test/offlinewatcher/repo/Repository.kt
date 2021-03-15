package app.test.offlinewatcher.repo

import app.test.offlinewatcher.repo.db.video.VideoEntity
import app.test.offlinewatcher.repo.db.video.VideosDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val videosDao: VideosDao
) {


    fun getAllVideos(): Flow<List<VideoEntity>> = videosDao.getAll()

    suspend fun saveVideo(video: VideoEntity): Long =
        withContext(IO) { videosDao.insert(video) }

    suspend fun saveVideo(videos: List<VideoEntity>): List<Long> =
        withContext(IO) { videosDao.insert(videos) }


}