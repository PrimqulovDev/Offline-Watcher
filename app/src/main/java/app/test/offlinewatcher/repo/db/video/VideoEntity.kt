package app.test.offlinewatcher.repo.db.video

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "videos_table")
data class VideoEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0,

    @ColumnInfo(name = "remote_url")
    val remoteUrl: String = "",

    @ColumnInfo(name = "local_url")
    val localUrl: String = "",

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "duration")
    val duration: Long = 0,
) {
    @Ignore
    var decodedUrl: String = ""
}