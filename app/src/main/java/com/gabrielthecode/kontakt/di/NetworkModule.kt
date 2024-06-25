package com.gabrielthecode.kontakt.di

import com.gabrielthecode.kontakt.data.remote.datasource.RemoteDataSourceImpl
import com.gabrielthecode.kontakt.data.remote.api.RandomUserApi
import com.gabrielthecode.kontakt.data.remote.mapper.UserResponseMapper
import com.gabrielthecode.kontakt.BuildConfig
import com.gabrielthecode.kontakt.data.remote.RandomUserApiRemoteService
import com.gabrielthecode.kontakt.data.remote.RandomUserApiRemoteServiceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
	private const val BASE_URL = "https://randomuser.me/api/1.3/"
	private const val SEED_QUERY = "seed"
	private const val REQUEST_TIMEOUT = 60

	@Singleton
	@Provides
	fun provideGsonBuilder(): Gson {
		return GsonBuilder().create()
	}

	@Singleton
	@Provides
	fun provideRetrofit(gson: Gson): Retrofit.Builder {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.client(getOkHttpService())
	}

	@Singleton
	@Provides
	fun provideRandomUserService(retrofit: Retrofit.Builder): RandomUserApi {
		return retrofit
			.build()
			.create(RandomUserApi::class.java)
	}

	@Singleton
	@Provides
	fun provideRemoteService(
		api: RandomUserApi
	): RandomUserApiRemoteService {
		return RandomUserApiRemoteServiceImpl(api)
	}

	@Singleton
	@Provides
	fun provideRemoteDataSource(
		remoteService: RandomUserApiRemoteService,
		userResponseMapper: UserResponseMapper
	): RemoteDataSourceImpl {
		return RemoteDataSourceImpl(
			remoteService,
			userResponseMapper
		)
	}

	private fun getOkHttpService(): OkHttpClient {
		val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
			.connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
			.readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
			.writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
		if (BuildConfig.DEBUG) {
			val interceptor = HttpLoggingInterceptor()
			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
			httpClient.addInterceptor(interceptor)
		}

		httpClient.addInterceptor(BasicAuthInterceptor())

		return httpClient.build()
	}

	class BasicAuthInterceptor : Interceptor {
		@Throws(IOException::class)
		override fun intercept(chain: Chain): Response {
			val request = chain.request()
			val newUrl =
				request.url.newBuilder().addQueryParameter(SEED_QUERY, BuildConfig.API_SEED)
					.build()
			val newRequest = request.newBuilder().url(newUrl).build()
			return chain.proceed(newRequest)
		}
	}
}
