plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.kapt)
	alias(libs.plugins.hiltAndroid)
	alias(libs.plugins.kotlinParcelize)
}

android {
	namespace = "com.gabrielthecode.kontakt"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.gabrielthecode.kontakt"
		minSdk = 34
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		buildConfigField("String", "API_SEED", "\"lydia\"")
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		compose = true
		buildConfig = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.14"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.org.jetbrains.kotlinx.coroutines.core)
	implementation(libs.org.jetbrains.kotlinx.coroutines.android)
	implementation(libs.com.google.dagger.hilt.android)
	kapt(libs.com.google.dagger.hilt.compiler)
	implementation(libs.androidx.lifecycle.process)
	implementation(libs.androidx.datastore.preferences)
	implementation(libs.com.google.code.gson)
	implementation(libs.com.squareup.retrofit2.retrofit)
	implementation(libs.com.squareup.retrofit2.converter.gson)
	implementation(libs.com.jakewharton.retrofit.retrofit2.kotlin.coroutines.adapter)
	implementation(libs.com.squareup.retrofit2.converter.scalars)
	implementation(libs.com.squareup.okhttp3.okhttp)
	implementation(libs.com.squareup.okhttp3.okhttp.urlconnection)
	implementation(libs.com.squareup.okhttp3.logging.interceptor)
	implementation(libs.androidx.room.ktx)
	implementation(libs.androidx.room.runtime)
	kapt(libs.androidx.room.compiler)
	implementation(libs.androidx.room.paging)
	implementation(libs.androidx.paging.compose)
	implementation(libs.androidx.paging.runtime.ktx)
	implementation(libs.androidx.room.paging)
	implementation(libs.airbnb.lottie.compose)
	implementation(libs.com.github.bumptech.glide)
	implementation(libs.com.github.bumptech.glide.annotations)
	implementation(libs.com.github.bumptech.glide.okhttp3.integration)
	implementation(libs.io.coil.compose)
	implementation(libs.io.mockk)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}