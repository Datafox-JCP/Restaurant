package mx.datafox.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import mx.datafox.restaurant.ui.theme.RestaurantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantTheme {
                RestaurantsApp()
            }
        }
    }
}

@Composable
fun RestaurantsApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "restaurants"
    ) {
        composable("restaurants") {
            RestaurantsScreen { id ->
                navController.navigate("restaurants/$id")
            }
        }
        composable("restaurants/{restaurant_id}",
            arguments = listOf(navArgument("restaurant_id") {
                type = NavType.IntType
            }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "www.restaurantapp.details.mx/{restaurant_id}"
            })) {
            RestaurantDetailsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RestaurantTheme {
        RestaurantsScreen()
    }
}