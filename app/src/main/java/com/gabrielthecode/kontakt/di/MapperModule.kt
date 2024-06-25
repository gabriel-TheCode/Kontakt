package com.gabrielthecode.kontakt.di

import com.gabrielthecode.kontakt.domain.model.UserDomainModel
import com.gabrielthecode.kontakt.data.remote.mapper.EntityMapper
import com.gabrielthecode.kontakt.data.remote.mapper.UserResponseMapper
import com.gabrielthecode.kontakt.data.remote.model.UserResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
	@Singleton
	@Provides
	fun provideSearchResponseMapper(): EntityMapper<UserResponse, UserDomainModel> {
		return UserResponseMapper()
	}
}
