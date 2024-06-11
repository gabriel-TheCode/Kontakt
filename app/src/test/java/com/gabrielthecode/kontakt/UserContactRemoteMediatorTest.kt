package com.gabrielthecode.kontakt

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.gabrielthecode.kontakt.core.domain.Info
import com.gabrielthecode.kontakt.core.domain.UserContactDomainModel
import com.gabrielthecode.kontakt.core.domain.UserDomainModel
import com.gabrielthecode.kontakt.core.local.LocalDataSourceImpl
import com.gabrielthecode.kontakt.core.remote.RemoteDataSourceImpl
import com.gabrielthecode.kontakt.datasource.database.mapper.UserEntityToDomainMapper
import com.gabrielthecode.kontakt.datasource.database.user.UserContactEntity
import com.gabrielthecode.kontakt.datasource.mediator.UserContactRemoteMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.anyList
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
@OptIn(ExperimentalPagingApi::class)
class UserContactRemoteMediatorTest {
	private lateinit var remoteDataSource: RemoteDataSourceImpl
	private lateinit var localDataSource: LocalDataSourceImpl
	private lateinit var userContactMapper: UserEntityToDomainMapper

	@Before
	fun setUp() {
		remoteDataSource = mock(RemoteDataSourceImpl::class.java)
		localDataSource = mock(LocalDataSourceImpl::class.java)
		userContactMapper = mock(UserEntityToDomainMapper::class.java)
	}

	@Test
	fun testLoadRefreshSuccess() = runTest {
		val mediator =
			UserContactRemoteMediator(remoteDataSource, localDataSource, userContactMapper)
		val pagingState = PagingState<Int, UserContactEntity>(
			pages = listOf(),
			anchorPosition = null,
			config = PagingConfig(pageSize = 20),
			leadingPlaceholderCount = 0
		)

		`when`(remoteDataSource.getUserContacts(page = 1)).thenReturn(fakeUserDomainModel)
		val result = mediator.load(LoadType.REFRESH, pagingState)

		assert(result is RemoteMediator.MediatorResult.Success && !result.endOfPaginationReached)

		verify(localDataSource).deleteAllUserContacts()
		verify(localDataSource).deleteAllRemoteKeys()
		verify(localDataSource).saveKeys(anyList())
		verify(localDataSource).saveUserContacts(anyList())
	}

	@Test
	fun testLoadAppendSuccess() = runTest {
		val mediator =
			UserContactRemoteMediator(remoteDataSource, localDataSource, userContactMapper)
		val pagingState = PagingState<Int, UserContactEntity>(
			pages = listOf(),
			anchorPosition = null,
			config = PagingConfig(pageSize = 20),
			leadingPlaceholderCount = 0
		)

		`when`(remoteDataSource.getUserContacts(page = 2)).thenReturn(fakeUserDomainModel)
		val result = mediator.load(LoadType.APPEND, pagingState)

		assert(result is RemoteMediator.MediatorResult.Success && !result.endOfPaginationReached)

		verify(localDataSource, never()).deleteAllUserContacts()
		verify(localDataSource, never()).deleteAllRemoteKeys()
	}

	@Test
	fun testLoadError() = runTest {
		val mediator =
			UserContactRemoteMediator(remoteDataSource, localDataSource, userContactMapper)
		val pagingState = PagingState<Int, UserContactEntity>(
			pages = listOf(),
			anchorPosition = null,
			config = PagingConfig(pageSize = 20),
			leadingPlaceholderCount = 0
		)

		`when`(remoteDataSource.getUserContacts(page = 1)).thenThrow(RuntimeException("Network error"))
		val result = mediator.load(LoadType.REFRESH, pagingState)

		assert(result is RemoteMediator.MediatorResult.Error)
	}

	private val fakeInfo = Info(
		page = 1,
		results = 20,
		seed = "test_seed",
		version = "1.0"
	)
	private val fakeUserContacts = listOf(
		UserContactDomainModel(
			uuid = "1",
			firstname = "John",
			lastname = "Doe",
			birthdate = "1990-01-01",
			email = "john.doe@example.com",
			smallPicture = "http://example.com/small.jpg",
			normalPicture = "http://example.com/normal.jpg",
			largePicture = "http://example.com/large.jpg",
			phone = "123-456-7890",
			gender = "male",
			street = "123 Main St",
			city = "Hometown",
			state = "CA",
			country = "USA",
			registered = "2020-01-01"
		),
		UserContactDomainModel(
			uuid = "2",
			firstname = "Jane",
			lastname = "Smith",
			birthdate = "1985-05-05",
			email = "jane.smith@example.com",
			smallPicture = "http://example.com/small.jpg",
			normalPicture = "http://example.com/normal.jpg",
			largePicture = "http://example.com/large.jpg",
			phone = "098-765-4321",
			gender = "female",
			street = "456 Elm St",
			city = "Another Town",
			state = "NY",
			country = "USA",
			registered = "2019-05-05"
		)
	)
	private val fakeUserDomainModel = UserDomainModel(
		info = fakeInfo,
		results = fakeUserContacts
	)
}
