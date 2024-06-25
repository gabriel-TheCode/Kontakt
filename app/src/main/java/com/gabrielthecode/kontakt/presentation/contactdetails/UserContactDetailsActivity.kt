package com.gabrielthecode.kontakt.presentation.contactdetails

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsActivity.Companion.USER_CONTACT_UI_MODEL
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsEvent.OnCallActionClickEvent
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsEvent.OnSmsActionClickEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserContactDetailsActivity : ComponentActivity() {

	private val viewModel by viewModels<UserContactDetailsViewModel>()

	private val userContact: UserContactUIModel? by lazy {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			intent.extras!!.getParcelable(USER_CONTACT_UI_MODEL, UserContactUIModel::class.java)
		} else {
			@Suppress("DEPRECATION")
			intent.getParcelableExtra(USER_CONTACT_UI_MODEL)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val state = viewModel.state.collectAsStateWithLifecycle()

			UserContactDetailsScreen(
				state = state.value,
				onAppBarIconClick = { onBackPressedDispatcher.onBackPressed() },
				onCallClick = { phone ->
					viewModel.onCallActionClick(phone)
				},
				onSmsClick = { phone ->
					viewModel.onSmsActionClick(phone)
				}
			)
		}.also {
			lifecycleScope.launch {
				viewModel.event.collectLatest {
					when (it) {
						is OnCallActionClickEvent -> launchCallIntent(it.phone)
						is OnSmsActionClickEvent -> launchMessageIntent(it.phone)
						else -> Unit
					}
				}
			}
		}

		viewModel.loadContactDetails(userContact)
	}

	override fun onDestroy() {
		super.onDestroy()
		viewModel.clearEvent()
	}

	private fun launchCallIntent(phone: String) {
		val intent = Intent(Intent.ACTION_DIAL)
		intent.setData(Uri.parse("tel:$phone"))
		startActivity(intent)
	}

	private fun launchMessageIntent(phone: String) {
		val intent = Intent(Intent.ACTION_VIEW)
		intent.setData(Uri.parse("sms:$phone"));
		startActivity(intent)
	}

	object Companion {
		const val USER_CONTACT_UI_MODEL = "UserContactUIModel"
	}
}


