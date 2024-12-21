package org.example.smart.cart.thapar.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import org.jetbrains.compose.resources.painterResource
import smart_cart.composeapp.generated.resources.Res
import smart_cart.composeapp.generated.resources.backgroundupdate
import smart_cart.composeapp.generated.resources.home
import smart_cart.composeapp.generated.resources.veggies




@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("") },
                    backgroundColor = Color(0xFF2382AA),
                    contentColor = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
            }
        },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(Res.drawable.home),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                SearchBar(modifier = Modifier.padding(top = 52.dp, bottom = 32.dp, start = 12.dp, end = 48.dp))
                CartNotif(modifier = Modifier.padding(bottom = 32.dp))
                UpdatesSection(modifier = Modifier)
                CategoriesSection(modifier = Modifier.padding(bottom = 32.dp))
                GrocerySection(modifier = Modifier.padding(bottom = 32.dp))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
            Spacer(modifier=Modifier.width(8.dp))
            BasicTextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.Gray, fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    if (textState.text.isEmpty()) {
                        Text("Search for fruits, vegetables, groce...", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Composable
fun CartNotif(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Icon(, contentDescription = "Location", tint = Color.Gray)
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(text = "Patiala, Punjab", color = Color.Gray, fontSize = 16.sp)
    }
}

@Composable
fun UpdatesSection(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth().height(200.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(Res.drawable.backgroundupdate),
                contentDescription = "Updates",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Ensures the image fills the Box
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Updates",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "All Vegetables & Fruits",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /* TODO */ }) {
                    Text(text = "See Detail")
                }
            }
        }
    }
}



@Composable
fun CategoriesSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Categories", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            CategoryItem(name = "Vegetables", icon = painterResource(Res.drawable.veggies))
            CategoryItem(name = "Fruits", icon = painterResource(Res.drawable.veggies))
            CategoryItem(name = "Meat & Eggs", icon = painterResource(Res.drawable.veggies))
            CategoryItem(name = "Drinks", icon = painterResource(Res.drawable.veggies))
            CategoryItem(name = "Bakery", icon = painterResource(Res.drawable.veggies))
        }
    }
}

@Composable
fun CategoryItem(name: String, icon: Painter) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            contentDescription = name,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, color = Color.Black, fontSize = 14.sp)
    }
}

@Composable
fun GrocerySection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Grocery and Kitchen", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* TODO */ }) {
                Text(text = "See more", color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            GroceryItem(image = painterResource(Res.drawable.veggies))
            GroceryItem(image = painterResource(Res.drawable.veggies))
            GroceryItem(image = painterResource(Res.drawable.veggies))
        }
    }
}

@Composable
fun GroceryItem(image: Painter) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier.size(100.dp)
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { /* TODO */ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = false,
            onClick = { /* TODO */ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart") },
            label = { Text("Cart") },
            selected = false,
            onClick = { /* TODO */ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Account") },
            label = { Text("Account") },
            selected = false,
            onClick = { /* TODO */ }
        )
    }
}
