package com.gabrielthecode.kontakt.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.map
import com.gabrielthecode.kontakt.data.local.datasource.LocalDataSourceImpl
import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity
import com.gabrielthecode.kontakt.data.local.mapper.UserEntityToDomainMapper
import com.gabrielthecode.kontakt.data.mediator.UserContactRemoteMediator
import com.gabrielthecode.kontakt.data.remote.datasource.RemoteDataSourceImpl
import com.gabrielthecode.kontakt.data.repositories.UserContactsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@OptIn(ExperimentalPagingApi::class)
@RunWith(RobolectricTestRunner::class)
class UserContactsRepositoryTest {
	private lateinit var repository: UserContactsRepository
	private lateinit var remoteDataSource: RemoteDataSourceImpl
	private lateinit var localDataSource: LocalDataSourceImpl
	private lateinit var mapper: UserEntityToDomainMapper

	@Before
	fun setUp() {
		remoteDataSource = mock(RemoteDataSourceImpl::class.java)
		localDataSource = mock(LocalDataSourceImpl::class.java)
		mapper = mock(UserEntityToDomainMapper::class.java)

		repository = UserContactsRepository(remoteDataSource, localDataSource, mapper)
	}

	@Test
	fun `test getUserContactsFromCache returns expected data`() = runTest {
		// Create a PagingSource mock
		val pagingSource = mock(PagingSource::class.java) as PagingSource<Int, UserContactEntity>
		`when`(localDataSource.getUserContacts()).thenReturn(pagingSource)
		// Verify that the Pager configuration is correct
		val pager = repository.getUserContactsFromCache()
		// Collect the PagingData flow
		val pagingData = pager.first()
		// Verify interactions with local data source
		verify(localDataSource).getUserContacts()
		// Optionally, you can verify the pagingSourceFactory and remoteMediator are set up correctly
		val config = PagingConfig(pageSize = 20)
		val expectedPager = Pager(
			config = config,
			pagingSourceFactory = { pagingSource },
			remoteMediator = UserContactRemoteMediator(remoteDataSource, localDataSource, mapper)
		)
		// Assert that the pager configurations are the same
		expectedPager.flow.first().map { expected ->
			pagingData.map {
				assertEquals(expected.uuid, it.uuid)
				assertEquals(expected.firstname, it.firstname)
				assertEquals(expected.lastname, it.lastname)
				assertEquals(expected.phone, it.phone)
				assertEquals(expected.smallPicture, it.smallPicture)
			}
		}
	}
}
