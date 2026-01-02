package com.morarafrank.dailybrief.ui.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.morarafrank.dailybrief.domain.model.Article
import com.morarafrank.dailybrief.ui.theme.Typography
import com.morarafrank.dailybrief.utils.UiUtils

@Composable
fun HeadlinesItemCard(
    modifier: Modifier = Modifier,
    newsArticle: Article,
    onClick: (articleId: String) -> Unit
) {

    val context = LocalContext.current
    Card(
        modifier = modifier
            .size(250.dp)
            .clickable {
                onClick(newsArticle.url ?: "")
            }
            .padding(4.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement
                .spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(newsArticle.urlToImage)
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
                text = newsArticle.title ?: "No Title",
                style = Typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                softWrap = true
            )

            Text(
                text = newsArticle.description ?: "No Title",
                style = Typography.bodySmall.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 11.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                softWrap = true
            )



            Text(
                text = UiUtils.formatLastUpdated(newsArticle.publishedAt),
                style = Typography.bodySmall.copy(
                    fontSize = 10.sp
                )
            )
        }


    }
}

//@Preview
@Composable
private fun PrevHeadlineCard() {
    HeadlinesItemCard(
        newsArticle = Article(
            title = "Sample Headline Title",
            description = "This is a sample description for the headline item card. It provides a brief overview of the news article.",
            author = "John Doe",
            publishedAt = "2024-06-01T12:00:00Z",
            source = com.morarafrank.dailybrief.domain.model.Source(
                id = "sample-source",
                name = "Sample Source"
            ),
            url = "https://www.example.com/sample-article",
            urlToImage = null
        ),
        onClick = {}
    )
}