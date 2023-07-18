package mx.datafox.restaurant

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.datafox.restaurant.ui.theme.RestaurantTheme

@Composable
fun DrawingScreen() {
    Canvas(modifier = Modifier.fillMaxSize(),
        onDraw = {
            drawCircle(
                Color.Green,
                center = Offset(
                    20.dp.toPx(),
                    100.dp.toPx()
                ),
                radius = 60.dp.toPx()
            )
        }
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        scale(scaleX = 10f, scaleY = 15f) {
            drawCircle(Color.Yellow, radius = 20.dp.toPx())
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = 100f, top = 300f) {
            drawCircle(Color.Blue, radius = 200.dp.toPx())
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(degrees = 45f) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(
                    x = size.width / 3f,
                    y = size.height / 3f),
                size = size / 3f
            )
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val quadrantSize = size / 2f
        inset(400f, 30f) {
            drawRect(color = Color.Cyan,
            size = quadrantSize)
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        withTransform( {
            translate(left = 50f, top = 50f)
            rotate(degrees = 45f)
        })
         {
            drawCircle(Color.Black, radius = 200.dp.toPx())
        }
    }
}

@Composable
fun LineGraphScreen() {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                    // Mantiene la proporción de la pantalla
                .aspectRatio(3 / 2f)
                .fillMaxSize()
        ) {
            // Rectángulo
            val barWidthPx = 1.dp.toPx()
            drawRect(Color.Blue, style = Stroke(barWidthPx))

            // Líneas verticales
            val verticalLines = 4
            val verticalSize = size.width / ( verticalLines + 1)
            repeat(verticalLines) { i ->
                val startX = verticalSize * (i + 1)
                drawLine(
                    Color.Blue,
                    start = Offset(startX, 0f),
                    end = Offset(startX, size.height),
                    strokeWidth = barWidthPx
                )
            }

            // Líneas horizontales
            val horizontalLines = 3
            val sectionSize = size.height / ( horizontalLines + 1)
            repeat(horizontalLines) { i ->
                val startY = sectionSize * (i + 1)
                drawLine(
                    Color.Blue,
                    start = Offset(0f, startY),
                    end = Offset(size.width, startY),
                    strokeWidth = barWidthPx
                )
            }

            // Draw the path
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DibujarPreview() {
    RestaurantTheme {
        LineGraphScreen()
    }
}