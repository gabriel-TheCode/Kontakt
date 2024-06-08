package com.gabrielthecode.kontakt.presentation.contactdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsActivity.Companion.USER_CONTACT_UI_MODEL
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsEvent.OnCallActionClickEvent
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsEvent.OnSmsActionClickEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserContactDetailsActivity : ComponentActivity() {
	private val viewModel by viewModels<UserContactDetailsViewModel>()
	private val userContact: UserContactUIModel? by lazy {
		intent.extras!!.getParcelable(USER_CONTACT_UI_MODEL, UserContactUIModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			UserContactDetailsScreen(
				state = viewModel.state.value,
				onAppBarIconClick = { onBackPressedDispatcher.onBackPressed() },
				onCallClick = { phone ->
					viewModel.onCallActionClick(phone)
				},
				onSmsClick = { phone ->
					viewModel.onSmsActionClick(phone)
				}
			)
		}.also {
			viewModel.event.observe(this) { event ->
				when (event) {
					is OnCallActionClickEvent -> userContact?.let { user -> launchCallIntent(user.phone) }
					is OnSmsActionClickEvent -> userContact?.let { user -> launchMessageIntent(user.phone) }
				}
			}
		}

		viewModel.loadContactDetails(userContact)
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


