package com.thiennguyen.estatelisting.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thiennguyen.estatelisting.R
import com.thiennguyen.estatelisting.models.EstateUiModel
import com.thiennguyen.estatelisting.ui.screens.carditem.EstateCardItem
import com.thiennguyen.estatelisting.ui.theme.AppTheme.dimensions
import com.thiennguyen.estatelisting.ui.theme.EstateListingTheme

@Composable
fun EstateListingScreen(
    viewModel: EstateListingViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getEstates()
    }

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val estates: List<EstateUiModel> by viewModel.estates.collectAsStateWithLifecycle()

    EstateListScreenContent(
        estates = estates,
        isLoading = isLoading,
        onItemLikeClicked = {
            viewModel.updateBookmarkedItem(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EstateListScreenContent(
    estates: List<EstateUiModel>,
    isLoading: Boolean,
    onItemLikeClicked: (EstateUiModel) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {},
                title = {
                    Text(
                        stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.background(Color.Transparent)
            )
        }
    ) { padding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            items(estates.size) { index ->
                EstateCardItem(
                    estate = estates[index],
                    onLikeClicked = {
                        onItemLikeClicked(estates[index])
                    },
                    modifier = Modifier
                        .padding(
                            horizontal = dimensions.spacingMedium,
                            vertical = dimensions.spacingSmall,
                        )
                )
            }

            if (isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserListScreenPreview() {
    EstateListingTheme {
        EstateListScreenContent(
            estates = listOf(
                EstateUiModel(
                    id = "id1",
                    imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/6b53db714891bfe2321cc3a6d4af76e1.jpg",
                    title = "title1",
                    price = "price1",
                    address = "address1",
                    isBookmarked = false,
                ),
                EstateUiModel(
                    id = "id2",
                    imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/328c41c0c0805299f5c28d680fbac4d9.jpg",
                    title = "title2",
                    price = "price2",
                    address = "address2",
                    isBookmarked = true,
                ),
                EstateUiModel(
                    id = "id3",
                    imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/2333f298be7cc3609daaaf2e39e91bf9.jpg",
                    title = "title3",
                    price = "price3",
                    address = "address3",
                    isBookmarked = false,
                ),
                EstateUiModel(
                    id = "id4",
                    imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/8944c80cb8afb8d5d579ca4faf7dbbb4.jpg",
                    title = "title4",
                    price = "price4",
                    address = "address4",
                    isBookmarked = true,
                ),
                EstateUiModel(
                    id = "id5",
                    imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/bbb5b2fb8a1cf58ce690e0cfca23d266.jpg",
                    title = "title5",
                    price = "price5",
                    address = "address5",
                    isBookmarked = false,
                ),
            ),
            isLoading = false,
            onItemLikeClicked = {}
        )
    }
}
