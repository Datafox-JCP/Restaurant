package mx.datafox.restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.datafox.restaurant.ui.theme.RestaurantTheme

@Composable
fun RestaurantsScreen(
    onItemClick: (id: Int) -> Unit = {}
) {

    val viewModel: RestaurantsViewModel = viewModel()

    LazyColumn(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 8.dp)
    ) {
        items(
            viewModel.state.value
        ) { restaurant ->
            RestaurantItem(
                restaurant,
                onFavoriteClick = { id -> viewModel.toggleFavorite(id) },
                onItemClick = { id -> onItemClick(id) })
        }
    }
}

@Composable
fun RestaurantItem(
    item: Restaurant,
    onFavoriteClick: (id: Int) -> Unit,
    onItemClick: (id: Int) -> Unit
) {

    val icon = if(item.isFavorite)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.id) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp)
        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            RestaurantIcon(
                Icons.Filled.Place,
                Modifier.weight(0.15f))
            RestaurantDetails(
                item.title,
                item.description,
                Modifier.weight(0.7f)
            )
            RestaurantIcon(
                icon,
                Modifier.weight(0.15f)
            ) {
                onFavoriteClick(item.id)
            }
        }
    }
}

@Composable
fun RestaurantIcon(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {

    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() }
    )
}

@Composable
fun RestaurantDetails(
    title: String,
    description: String,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
    ) {

    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1
        )
        Text(
            text = description,
            modifier = Modifier.alpha(0.5f),
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantsPreview() {
    RestaurantTheme {
        //RestaurantsScreen()
    }
}
