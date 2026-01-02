package com.morarafrank.dailybrief.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.morarafrank.dailybrief.ui.NewsViewModel
import com.morarafrank.dailybrief.ui.theme.Typography
import com.morarafrank.dailybrief.utils.UiUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullNewsScreen(
    modifier: Modifier = Modifier,
    articleUrl: String,
    navigateBack: () -> Unit,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val newsArticle by viewModel.selectedNewsArticle.collectAsState()
    val context = androidx.compose.ui.platform.LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Full News Article"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigation Icon"
                        )
                    }
                }
            )
        },
        content = {

            Column(
                modifier = modifier.fillMaxSize().padding(it)
                    .padding(16.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(newsArticle?.urlToImage)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxWidth(0.9f)
                        .size(150.dp)
                        .padding(2.dp),
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(com.morarafrank.dailybrief.R.drawable.placeholder_image),
                    error = painterResource(com.morarafrank.dailybrief.R.drawable.broken_image)
                )



                Text(
                    text = newsArticle?.title ?: "No Title",
                    style = Typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )

                Text(
                    text = newsArticle?.description ?: "No Title",
                    style = Typography.bodySmall.copy(
                        fontWeight = FontWeight.Light,
                        fontSize = 11.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )



                Text(
                    text = UiUtils.formatLastUpdated(newsArticle?.publishedAt),
                    style = Typography.bodySmall.copy(
                        fontSize = 10.sp
                    )
                )
            }
        }
    )
}