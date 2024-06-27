package com.gabrielthecode.kontakt.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import com.gabrielthecode.kontakt.data.local.entities.UserContactEntity
import com.gabrielthecode.kontakt.domain.usecases.GetUserContacts
import com.gabrielthecode.kontakt.presentation.contact.UserContactEvent
import com.gabrielthecode.kontakt.presentation.contact.UserContactsViewModel
import com.gabrielthecode.kontakt.presentation.contact.mapper.UserContactEntityToUIModelMapper
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserContactsViewModelTest {
	@get:Rule
	val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
	private val getUserContactsUseCase = mock<GetUserContacts>()
	private val userContactEntityToUIModelMapper = mock<UserContactEntityToUIModelMapper>()
	private lateinit var viewModel: UserContactsViewModel
	private val testDispatcher = UnconfinedTestDispatcher()
	private val userContactEntity = mock(UserContactEntity::class.java)
	private val userUIModel = mock(UserContactUIModel::class.java)

	@Before
	fun setUp() {
		Dispatchers.setMain(testDispatcher)

		`when`(getUserContactsUseCase()).thenReturn(flowOf(PagingData.from(listOf(userContactEntity))))
		Mockito.lenient().`when`(userContactEntityToUIModelMapper.map(userContactEntity))
			.thenReturn(userUIModel) // Lenient to avoid conversion exceptions (#registered and #birthdate in this case)
		viewModel = UserContactsViewModel(getUserContactsUseCase, userContactEntityToUIModelMapper)
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
	}

	@Test
	fun `test user contact state flow updates correctly`() = runTest {
		// Assert
		viewModel.userContactStateFlow.value.map {
			assertEquals(it.uuid, userContactEntity.uuid)
			assertEquals(it.firstname, userContactEntity.firstname)
			assertEquals(it.lastname, userContactEntity.lastname)
			assertEquals(it.phone, userContactEntity.phone)
			assertEquals(it.smallPicture, userContactEntity.smallPicture)
		}
	}

	@Test
	fun `test onUserContactClick triggers event`() {
		// Arrange
		val uiModel = userUIModel
		// Act
		viewModel.onUserContactClick(uiModel)
		// Assert
		assertTrue(viewModel.event.value is UserContactEvent.OnContactClickEvent)
		val event = viewModel.event.value as UserContactEvent.OnContactClickEvent
		assertEquals(uiModel, event.uiModel)
	}

	@Test
	fun `test clearEvent resets event`() = runTest {
		viewModel.clearEvent()

		assertTrue(viewModel.event.value is UserContactEvent.None)
	}
}
