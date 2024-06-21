import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import com.gabrielthecode.kontakt.domain.usecases.GetUserContacts
import com.gabrielthecode.kontakt.data.database.user.UserContactEntity
import com.gabrielthecode.kontakt.presentation.contact.UserContactEvent
import com.gabrielthecode.kontakt.presentation.contact.UserContactsViewModel
import com.gabrielthecode.kontakt.presentation.contact.mapper.UserContactEntityToUIModelMapper
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
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
	private lateinit var userContactsViewModel: UserContactsViewModel
	private val testDispatcher = TestCoroutineDispatcher()

	@Before
	fun setUp() {
		Dispatchers.setMain(testDispatcher)
		userContactsViewModel =
			UserContactsViewModel(getUserContactsUseCase, userContactEntityToUIModelMapper)
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
		testDispatcher.cleanupTestCoroutines()
	}

	@Test
	fun `test user contact state flow updates correctly`() = runTest {
		// Arrange
		val userContactEntity = mock(UserContactEntity::class.java)
		val userUIModel = mock(UserContactUIModel::class.java)
		val pagingData = PagingData.from(listOf(userContactEntity))

		`when`(getUserContactsUseCase.getUserContacts()).thenReturn(flowOf(pagingData))
		`when`(userContactEntityToUIModelMapper.map(userContactEntity)).thenReturn(userUIModel)
		// Act
		userContactsViewModel =
			UserContactsViewModel(getUserContactsUseCase, userContactEntityToUIModelMapper)
		// Assert
		userContactsViewModel.userContactStateFlow.value.map {
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
		val uiModel = mock(UserContactUIModel::class.java)
		// Act
		userContactsViewModel.onUserContactClick(uiModel)
		// Assert
		assertTrue(
			userContactsViewModel.event.value is UserContactEvent.OnContactClickEvent
		)
	}
}
