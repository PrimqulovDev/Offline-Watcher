package app.test.offlinewatcher.di

import app.test.offlinewatcher.usecase.save_video.SaveVideoUseCase
import app.test.offlinewatcher.usecase.save_video.SaveVideoUseCaseImpl
import app.test.offlinewatcher.usecase.video.GetAllVideosUseCase
import app.test.offlinewatcher.usecase.video.GetAllVideosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {

    @Binds
    fun bindSaveVideoUseCase(binder: SaveVideoUseCaseImpl): SaveVideoUseCase

    @Binds
    fun bindGetAllVideosUseCase(binder: GetAllVideosUseCaseImpl): GetAllVideosUseCase

}