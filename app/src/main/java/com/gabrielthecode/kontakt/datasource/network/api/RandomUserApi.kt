package com.gabrielthecode.kontakt.datasource.network.api

import com.gabrielthecode.kontakt.datasource.network.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
	@GET(".")
	suspend fun getUserContacts(
		@Query("results") results: Int = 20,
		@Query("page") page: Int?
	): UserResponse
}