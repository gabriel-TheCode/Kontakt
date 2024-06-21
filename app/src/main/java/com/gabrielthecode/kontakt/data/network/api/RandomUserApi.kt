package com.gabrielthecode.kontakt.data.network.api

import com.gabrielthecode.kontakt.data.network.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
	@GET(".")
	suspend fun getUserContacts(
		@Query("results") results: Int = 20,
		@Query("page") page: Int?
	): UserResponse
}