package app.test.offlinewatcher.repo.model

import java.io.File

/*
enum class Status(var message: String? = null) {
    DOWNLOADING,
    PAUSED,
    CANCELED
}*/

sealed class Status
data class Downloading(val percentage: Int) : Status()
data class Canceled(val message: String) : Status()
data class Success(val file: File) : Status()
object Paused : Status()