package com.gabrielthecode.kontakt.presentation.contact

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsActivity
import com.gabrielthecode.kontakt.presentation.contactdetails.UserContactDetailsActivity.Companion.USER_CONTACT_UI_MODEL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserContactsActivity : ComponentActivity() {

	private val viewModel by viewModels<UserContactsViewModel>()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			val userPagingData = viewModel.userContactStateFlow.collectAsLazyPagingItems()

			UserContactScreen(
				userPagingData = userPagingData,
				onContactClick = { userContact ->
					viewModel.onUserContactClick(userContact)
				})
		}.also {
			lifecycleScope.launch {
				viewModel.event.collectLatest { event ->
					when (event) {
						is UserContactEvent.OnContactClickEvent -> launchContactDetails(event.uiModel)
						else -> Unit
					}
				}
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		viewModel.clearEvent()
	}

	private fun launchContactDetails(userContactUIModel: UserContactUIModel) {
		val intent = Intent(this, UserContactDetailsActivity::class.java)
		intent.putExtra(USER_CONTACT_UI_MODEL, userContactUIModel)
		startActivity(intent)
	}
}
