package app.test.offlinewatcher.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.test.offlinewatcher.repo.db.video.VideoEntity
import app.test.offlinewatcher.repo.db.video.VideosDao

@Database(
    entities = [
        VideoEntity::class
    ], version = 1
)
abstract class MainDatabase : RoomDatabase() {
    abstract val videosDao: VideosDao
}