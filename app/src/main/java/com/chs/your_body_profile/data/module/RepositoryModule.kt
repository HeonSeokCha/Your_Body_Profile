package com.chs.your_body_profile.data.module

import com.chs.your_body_profile.data.repository.BodyRepositoryImpl
import com.chs.your_body_profile.data.repository.FoodRepositoryImpl
import com.chs.your_body_profile.domain.repository.BodyRepository
import com.chs.your_body_profile.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindBodyRepository(bodyRepositoryImpl: BodyRepositoryImpl): BodyRepository

    @Binds
    abstract fun bindFoodRepository(foodRepositoryImpl: FoodRepositoryImpl): FoodRepository
}