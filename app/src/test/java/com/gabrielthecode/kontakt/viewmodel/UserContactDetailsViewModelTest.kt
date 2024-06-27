package com.gabrielthecode.kontakt.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsEvent
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserContactDetailsViewModelTest {
	@get:Rule
	val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
	private lateinit var viewModel: UserContactDetailsViewModel
	private val testDispatcher = UnconfinedTestDispatcher()

	@Before
	fun setUp() {
		Dispatchers.setMain(testDispatcher)
		viewModel = UserContactDetailsViewModel()
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
	}

	@Test
	fun `test onSmsActionClick triggers event`() {
		// Arrange
		val phone = "05343877622"
		// Act
		viewModel.onSmsActionClick(phone)
		// Assert
		assertTrue(
			viewModel.event.value is UserContactDetailsEvent.OnSmsActionClickEvent
		)
		val event = viewModel.event.value as UserContactDetailsEvent.OnSmsActionClickEvent
		assertEquals(phone, event.phone)
	}

	@Test
	fun `test onCallActionClick triggers event`() {
		// Arrange
		val phone = "05343877622"
		// Act
		viewModel.onCallActionClick(phone)
		// Assert
		assertTrue(
			viewModel.event.value is UserContactDetailsEvent.OnCallActionClickEvent
		)
		val event = viewModel.event.value as UserContactDetailsEvent.OnCallActionClickEvent
		assertEquals(phone, event.phone)
	}

	@Test
	fun `test clearEvent resets event`() = runTest {
		viewModel.clearEvent()

		assertTrue(viewModel.event.value is UserContactDetailsEvent.None)
	}
}
