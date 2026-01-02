package com.morarafrank.dailybrief.ui.screens.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.morarafrank.dailybrief.R
import com.morarafrank.dailybrief.domain.model.Article
import com.morarafrank.dailybrief.ui.theme.Typography

@Composable
fun LatestNewsUi(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClickArticle: (String) -> Unit,
) {

    var searchClicked by remember { mutableStateOf(false) }
    var searchText by remember{mutableStateOf("")}
    LazyColumn(
        modifier = modifier.fillMaxSize()
            .padding(8.dp)
    ) {

        item {

            AnimatedVisibility(visible = searchClicked) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = {
                        Text(
                            text = "Search Todos",
                            style = Typography.bodyMedium
                        )
                    },
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = searchText.isNotEmpty()
                        ) {
                            IconButton(
                                onClick = {
                                    searchText = ""
                                },
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_close),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            }
            androidx.compose.material3.Text(
                text = "Latest News",
                modifier = Modifier.padding(8.dp),
                style = Typography.bodyLarge
            )
        }
        items(articles) { article ->
            NewsItemCard(
                newsArticle = article,
                onClick = {
                    onClickArticle(it)
                }
            )
        }
    }
}