package com.thiennguyen.estatelisting.ui.screens.carditem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.thiennguyen.estatelisting.R
import com.thiennguyen.estatelisting.models.EstateUiModel
import com.thiennguyen.estatelisting.ui.theme.AppTheme.colors
import com.thiennguyen.estatelisting.ui.theme.AppTheme.dimensions
import com.thiennguyen.estatelisting.ui.theme.AppTheme.typography
import com.thiennguyen.estatelisting.ui.theme.EstateListingTheme

@Composable
fun EstateCardItem(
    estate: EstateUiModel,
    onLikeClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(dimensions.shadowMedium, RoundedCornerShape(dimensions.cornerMedium))
            .clip(RoundedCornerShape(dimensions.cornerMedium))
            .background(colors.themeColors.surface)
            .padding(bottom = dimensions.spacingSmall),
        verticalArrangement = Arrangement.spacedBy(dimensions.spacingSmall)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = estate.imageUrl,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                fallback = painterResource(R.drawable.ic_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(ratio = 1.5f)
            )
            Text(
                text = estate.price,
                style = typography.b2,
                modifier = Modifier
                    .padding(dimensions.spacingSmall)
                    .clip(RoundedCornerShape(dimensions.cornerSmall))
                    .background(colors.themeColors.secondary)
                    .padding(dimensions.spacingXSmall)
                    .align(Alignment.BottomStart)
            )
            Image(
                painter = if (estate.isBookmarked) {
                    painterResource(R.drawable.ic_check_filled)
                } else {
                    painterResource(R.drawable.ic_check_outlined)
                },
                colorFilter = ColorFilter.tint(
                    color = colors.themeColors.secondary
                ),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(dimensions.spacingSmall)
                    .clickable {
                        onLikeClicked()
                    }
            )
        }
        Text(
            text = estate.title,
            style = typography.t2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = dimensions.spacingSmall)
        )
        Text(
            text = estate.address,
            style = typography.b2,
            color = colors.themeColors.tertiary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = dimensions.spacingSmall)
        )
    }
}

@Preview
@Composable
private fun UserCardItemPreview() {
    EstateListingTheme {
        EstateCardItem(
            estate = EstateUiModel(
                id = "id1",
                imageUrl = "https://media2.homegate.ch/listings/heia/104123262/image/6b53db714891bfe2321cc3a6d4af76e1.jpg",
                title = "title1",
                price = "price1",
                address = "address1",
                isBookmarked = false,
            ),
            onLikeClicked = {}
        )
    }
}
