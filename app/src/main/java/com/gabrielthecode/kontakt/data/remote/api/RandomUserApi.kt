package com.gabrielthecode.kontakt.data.remote.api

import com.gabrielthecode.kontakt.data.remote.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
	@GET(".")
	suspend fun getUserContacts(
		@Query("results") results: Int = 20,
		@Query("page") page: Int?
	): UserResponse
}