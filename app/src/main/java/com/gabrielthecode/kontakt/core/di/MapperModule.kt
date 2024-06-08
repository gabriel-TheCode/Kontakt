package com.gabrielthecode.kontakt.core.di

import com.gabrielthecode.kontakt.core.domain.UserDomainModel
import com.gabrielthecode.kontakt.datasource.network.mapper.EntityMapper
import com.gabrielthecode.kontakt.datasource.network.mapper.UserResponseMapper
import com.gabrielthecode.kontakt.datasource.network.mapper.error.DomainExceptionMapper
import com.gabrielthecode.kontakt.datasource.network.model.UserResponse
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

	@Provides
	fun provideDomainExceptionMapper(): DomainExceptionMapper {
		return DomainExceptionMapper()
	}
}
