package com.confradestech.aetna_cvs_code_challenge.commom.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.confradestech.aetna_cvs_code_challenge.R
import com.confradestech.aetna_cvs_code_challenge.commom.previewAssets.flickrItemPreviewAsset
import com.confradestech.aetna_cvs_code_challenge.commom.theme.AetnaCvsCodeChallengeTheme
import com.confradestech.aetna_cvs_code_challenge.commom.utils.PreviewProfileScreens
import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FlickrImageCardComponent(
    flickrImage: FlickrItem,
    onItemTapped: (FlickrItem) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = { onItemTapped(flickrImage) }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImage(
                    model = flickrImage.flickrItemMedia.mediaLink,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentDescription = stringResource(R.string.flickr_media_item_description),
                    contentScale = if (flickrImage.flickrItemMedia.mediaLink.isEmpty()) {
                        ContentScale.Fit
                    } else {
                        ContentScale.Crop
                    },
                    alignment = Alignment.CenterStart
                ) { requestBuilder ->
                    try {
                        requestBuilder
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .fitCenter()
                            .load(flickrImage.flickrItemMedia.mediaLink)
                            .error(R.drawable.baseline_image_not_supported_24)
                    } catch (error: Throwable) {
                        requestBuilder
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .fitCenter()
                            .load(flickrImage.flickrItemMedia.mediaLink)
                            .error(R.drawable.baseline_image_not_supported_24)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                BodyTextComponent(
                    text = flickrImage.title,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@PreviewProfileScreens
@Composable
fun FlickrImageCardComponentPreview() {
    AetnaCvsCodeChallengeTheme {
        FlickrImageCardComponent(
            flickrImage = flickrItemPreviewAsset,
            onItemTapped = { /*No-op for previews */ }
        )
    }
}