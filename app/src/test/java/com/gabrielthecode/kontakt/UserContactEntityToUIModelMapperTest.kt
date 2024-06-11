package com.gabrielthecode.kontakt

import com.gabrielthecode.kontakt.datasource.database.user.UserContactEntity
import com.gabrielthecode.kontakt.presentation.contact.mapper.UserContactEntityToUIModelMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UserContactEntityToUIModelMapperTest {
	private lateinit var mapper: UserContactEntityToUIModelMapper

	@Before
	fun setUp() {
		mapper = UserContactEntityToUIModelMapper()
	}

	@Test
	fun `map should map with correct values for UserContactEntity`() {
		val uiModel = mapper.map(getUserEntity())

		uiModel.apply {
			assertEquals(uuid, "uuid")
			assertEquals(firstname, "John")
			assertEquals(lastname, "Doe")
			assertEquals(birthdate, "28 mars 1992, 11:45")
			assertEquals(email, "johndoe@email.com")
			assertEquals(smallPicture, "smallPicture")
			assertEquals(normalPicture, "normalPicture")
			assertEquals(largePicture, "largePicture")
			assertEquals(phone, "067363773")
			assertEquals(gender, "gender")
			assertEquals(street, "street")
			assertEquals(city, "city")
			assertEquals(state, "state")
			assertEquals(country, "country")
			assertEquals(registered, "17 février 2019, 07:29")
			assertFalse(favorite)
		}
	}

	private fun getUserEntity() = UserContactEntity(
		"uuid",
		"John",
		"Doe",
		"1992-03-28T11:45:34.772Z",
		"johndoe@email.com",
		"smallPicture",
		"normalPicture",
		"largePicture",
		"067363773",
		"gender",
		"street",
		"city",
		"state",
		"country",
		"2019-02-17T07:29:54.396Z",
		false
	)
}