package com.morarafrank.dailybrief.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morarafrank.dailybrief.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToHeadlinesScreen: () -> Unit,
    navigateToLatestNewsScreen: () -> Unit,
    navigateToSingleArticle: (String) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DailyBrief",
                    )
                },
                modifier = modifier,
                actions = {
                    IconButton(
                        onClick = { /* TODO: Implement search action */ }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "Search"
                        )
                    }
                }
            )
        },
        content = {
            LazyColumn(
                modifier = modifier.fillMaxSize()
                    .padding(8.dp)
            ) {
                item {
                    LazyRow(
                        modifier= modifier.fillMaxWidth().padding(2.dp)
                    ) {
                        items(4){}
                    }
                }
                items(listOf(1,2,3,4,5)) {
                    // NewsItemCard(newsItem = it)
                }
            }
        }
    )
}