package com.cevdetkilickeser.shimmereffectcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cevdetkilickeser.shimmereffectcompose.ui.theme.ShimmerEffectComposeTheme
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.placeholder
import com.eygraber.compose.placeholder.shimmer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShimmerEffectComposeTheme {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()) {
                    items(dataList) { data ->
                        ShimmerItem(
                            data.imageUrl,
                            data.description
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerItem(
    imageUrl: String,
    text: String,
) {
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .size(128.dp)
                .shimmerPlaceholder(isLoading),
            onSuccess = {
                scope.launch {
                    delay(2000) //Simulate loading delay
                    isLoading = false
                }
            },
            onError = {
                scope.launch {
                    delay(2000)
                    isLoading = false
                }
            }
        )
        Text(
            text = text,
            modifier = Modifier
                .shimmerPlaceholder(isLoading)
        )
    }
}

fun Modifier.shimmerPlaceholder(
    isVisible: Boolean,
    cornerRadius: Dp = 8.dp,
    shape: RoundedCornerShape = RoundedCornerShape(cornerRadius),
    baseColor: Color = Color.LightGray,
    highlightColor: Color = Color.White
): Modifier {
    return this.placeholder(
        visible = isVisible,
        color = baseColor,
        shape = shape,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = highlightColor,
        )
    )
}

data class ShimmerData(
    val description: String,
    val imageUrl: String
)

const val imageUrl1 =
    "https://media.istockphoto.com/id/480968800/photo/karag%C3%B6l.jpg?s=612x612&w=0&k=20&c=vUUJWjSHeD7UaKybX9_Mlf4XNlCPEnPCngaeSSXwBMA="
const val imageUrl2 =
    "https://fotolifeakademi.com/uploads/2020/04/manzara-fotografi-cekmek-724x394.webp"
const val imageUrl3 =
    "https://media.istockphoto.com/id/517188688/tr/foto%C4%9Fraf/mountain-landscape.jpg?s=612x612&w=0&k=20&c=KpANCuD2dFxKw6Qy6XfMXpTHiZcsprOf0LRm2t4K9kM="
const val imageUrl4 =
    "https://t3.ftcdn.net/jpg/00/80/21/66/240_F_80216605_VihKydNNGGD0jWnGo3APzXs6wH3mHprc.jpg"
const val imageUrl5 =
    "https://as1.ftcdn.net/v2/jpg/02/82/28/12/1000_F_282281297_QGZh9ZrTXZ4Tu60Z6hVBW3KQSaeKjo10.jpg"
const val imageUrl6 =
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShUYXis-3FrEhfKqHYrAUDTz5GWw8h2OJSrg&s"
const val imageUrl7 =
    "https://www.shutterstock.com/image-photo/banff-national-park-lake-minnewanka-260nw-2527379207.jpg"

val dataList = listOf(
    ShimmerData("Content 1 is displaying on this line.", imageUrl1),
    ShimmerData("Content 2 is displaying on this line.", imageUrl2),
    ShimmerData("Content 3 is displaying on this line.", imageUrl3),
    ShimmerData("Content 4 is displaying on this line.", imageUrl4),
    ShimmerData("Content 5 is displaying on this line.", imageUrl5),
    ShimmerData("Content 6 is displaying on this line.", imageUrl6),
    ShimmerData("Content 7 is displaying on this line.", imageUrl7),
    ShimmerData("Content 8 is displaying on this line.", imageUrl3),
    ShimmerData("Content 9 is displaying on this line.", imageUrl4),
    ShimmerData("Content 10 is displaying on this line.", imageUrl5),
    ShimmerData("Content 11 is displaying on this line.", imageUrl6),
    ShimmerData("Content 12 is displaying on this line.", imageUrl7),

    )