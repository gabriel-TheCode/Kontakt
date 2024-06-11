package com.gabrielthecode.kontakt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsEvent
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
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
	private lateinit var userContactDetailsViewModel: UserContactDetailsViewModel
	private val testDispatcher = TestCoroutineDispatcher()

	@Before
	fun setUp() {
		Dispatchers.setMain(testDispatcher)
		userContactDetailsViewModel = UserContactDetailsViewModel()
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
		testDispatcher.cleanupTestCoroutines()
	}

	@Test
	fun `test onSmsActionClick triggers event`() {
		// Arrange
		val phone = "05343877622"
		// Act
		userContactDetailsViewModel.onSmsActionClick(phone)
		// Assert
		assertTrue(
			userContactDetailsViewModel.event.value is UserContactDetailsEvent.OnSmsActionClickEvent
		)
	}

	@Test
	fun `test onSmsCallClick triggers event`() {
		// Arrange
		val phone = "05343877622"
		// Act
		userContactDetailsViewModel.onCallActionClick(phone)
		// Assert
		assertTrue(
			userContactDetailsViewModel.event.value is UserContactDetailsEvent.OnCallActionClickEvent
		)
	}
}
