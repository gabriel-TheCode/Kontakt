package com.gabrielthecode.kontakt.presentation.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.gabrielthecode.kontakt.R
import com.gabrielthecode.kontakt.presentation.contact.uimodel.UserContactUIModel
import com.gabrielthecode.kontakt.presentation.ui.components.ErrorBottomSection
import com.gabrielthecode.kontakt.presentation.ui.components.ErrorPage
import com.gabrielthecode.kontakt.presentation.ui.components.HeaderSection
import com.gabrielthecode.kontakt.presentation.ui.components.InfiniteScrollLoader
import com.gabrielthecode.kontakt.presentation.ui.components.PageLoader
import com.gabrielthecode.kontakt.presentation.ui.components.UserContactItem
import com.gabrielthecode.kontakt.presentation.ui.theme.KontaktTheme

@Composable
internal fun UserContactScreen(
	userPagingData: LazyPagingItems<UserContactUIModel>,
	onContactClick: (UserContactUIModel) -> Unit
) {
	UserContactsScreenLoadedState(
		onContactClick = onContactClick,
		pagingItems = userPagingData
	)
}

@Composable
private fun UserContactsScreenLoadedState(
	pagingItems: LazyPagingItems<UserContactUIModel>,
	onContactClick: (UserContactUIModel) -> Unit
) {
	KontaktTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
		) {
			Column(Modifier.padding(top = 8.dp)) {
				HeaderSection(
					title = stringResource(id = R.string.app_name),
					description = stringResource(id = R.string.home_description)
				)

				LazyColumn {
					pagingItems.itemKey { item -> item.uuid }
					items(pagingItems.itemCount) { index ->
						pagingItems.get(index = index)?.let { user ->
							UserContactItem(
								user,
								modifier = Modifier
							) { onContactClick(it) }
						}
					}

					pagingItems.apply {
						when {
							loadState.refresh is LoadState.Loading -> {
								item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
							}
							loadState.append is LoadState.Loading -> {
								item { InfiniteScrollLoader(modifier = Modifier) }
							}
							loadState.refresh is LoadState.Error -> {
								if (pagingItems.itemCount == 0) {
									item {
										ErrorPage(
											modifier = Modifier.fillParentMaxSize(),
											title = stringResource(id = R.string.network_error),
											message = stringResource(id = R.string.check_internet),
											lottieRes = R.raw.wifi_error_anim,
											onClickRetry = { retry() })
									}
								}
							}
							loadState.append is LoadState.Error -> {
								item {
									ErrorBottomSection(
										modifier = Modifier,
										message = stringResource(R.string.unable_to_fetch),
										onClickRetry = { retry() })
								}
							}
						}
					}
				}
			}
		}
	}
}