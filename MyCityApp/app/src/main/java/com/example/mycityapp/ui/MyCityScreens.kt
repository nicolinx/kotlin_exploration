package com.example.mycityapp.ui

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.R
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Item
import com.example.mycityapp.utils.MyCityContentType

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
) {
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val contentType: MyCityContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium
            -> {
            contentType = MyCityContentType.ListOnly
        }

        WindowWidthSizeClass.Expanded -> {
            contentType = MyCityContentType.ListAndDetail
        }

        else -> contentType = MyCityContentType.ListOnly
    }

    BackHandler(enabled = !(contentType == MyCityContentType.ListAndDetail || (uiState.selectedCategory == null))) {
        if (uiState.selectedItem != null) {
            viewModel.unselectItem()
        } else if (uiState.selectedCategory != null) {
            viewModel.unselectCategory()
        }
    }

    Scaffold(
        topBar = {
            MyCityTopAppBar(
                titleResId = if (uiState.selectedItem != null) uiState.selectedItem!!.titleResId
                else if (uiState.selectedCategory != null) uiState.selectedCategory!!.titleResId
                else R.string.app_name,
                isShowBack = !(contentType == MyCityContentType.ListAndDetail || (uiState.selectedCategory == null)),
                onBackButtonClick = {
                    if (uiState.selectedItem != null) {
                        viewModel.unselectItem()
                    } else if (uiState.selectedCategory != null) {
                        viewModel.unselectCategory()
                    }
                }
            )
        }
    ) { innerPadding ->
        if (contentType == MyCityContentType.ListOnly) {
            if (uiState.selectedCategory == null)
                CategoriesList(
                    categories = uiState.listCategories,
                    onClick = {
                        viewModel.selectCategory(it)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            else if (uiState.selectedItem == null)
                ItemsList(
                    items = uiState.listItems,
                    onClick = {
                        viewModel.selectItem(it)
                    },
                    onBackButtonClick = { viewModel.unselectCategory() },
                    modifier = Modifier.padding(innerPadding)
                )
            else
                ItemDetail(
                    item = uiState.selectedItem!!,
                    onBackButtonClick = {
                        viewModel.unselectItem()
                    },
                    modifier = Modifier.padding(innerPadding)
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityTopAppBar(
    @StringRes titleResId: Int,
    isShowBack: Boolean,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(stringResource(titleResId))
        },
        navigationIcon = {
            if (isShowBack) {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun CategoriesList(
    categories: List<Category>,
    onClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium))
    ) {
        items(categories, key = { category -> category.id }) { category ->
            CategoryCard(
                category = category,
                onItemClick = { onClick(category) }
            )
        }
    }
}

@Composable
fun ItemsList(
    items: List<Item>,
    onClick: (Item) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler() {
        onBackButtonClick()
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium))
    ) {
        items(items, key = { item -> item.id }) { item ->
            ItemCard(
                item = item,
                onItemClick = { onClick(item) }
            )
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(category) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            Box(modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))) {
                Image(
                    painter = painterResource(category.imageResId),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    stringResource(category.titleResId),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    stringResource(category.descriptionResId),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Composable
fun ItemCard(
    item: Item,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(item) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            Box(modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))) {
                Image(
                    painter = painterResource(item.imageResId),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    stringResource(item.titleResId),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    stringResource(item.descriptionResId),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ItemDetail(
    item: Item,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler() {
        onBackButtonClick()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp)
    ) {
        Image(
            painter = painterResource(item.imageResId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        Text(
            stringResource(item.titleResId),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            stringResource(item.descriptionResId),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}