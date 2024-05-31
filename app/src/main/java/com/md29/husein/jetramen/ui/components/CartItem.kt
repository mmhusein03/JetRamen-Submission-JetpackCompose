package com.md29.husein.jetramen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.md29.husein.jetramen.R
import com.md29.husein.jetramen.ui.theme.JetRamenTheme

@Composable
fun CartItem(
    menuId: Long,
    image: Int,
    title: String,
    price: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (menuImage, titleText, priceMenu, counter) = createRefs()

        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(menuImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.constrainAs(titleText) {
                start.linkTo(menuImage.end, margin = 16.dp)
                top.linkTo(parent.top, margin = 8.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = stringResource(
                R.string.price,
                price
            ),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.constrainAs(priceMenu) {
                top.linkTo(titleText.bottom)
                start.linkTo(titleText.start)
            }
        )

        ProductCounter(
            orderId = menuId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(menuId, count + 1) },
            onProductDecreased = { onProductCountChanged(menuId, count - 1) },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(counter) {
                    top.linkTo(priceMenu.bottom, margin = 8.dp)
                    start.linkTo(menuImage.end)
                }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    JetRamenTheme {
        CartItem(
            4, R.drawable.kakeudon_ramen, "Kakeudon Ramen", 30000, 0,
            onProductCountChanged = { _, _ -> },
        )
    }
}