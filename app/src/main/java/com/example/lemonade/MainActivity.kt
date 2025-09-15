package com.example.lemonade

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import org.w3c.dom.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeApp(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ImagePage(
    onClick: () -> Unit,
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(200.dp, 200.dp)
            .clickable(){ onClick() },
        shape = RoundedCornerShape(50.dp),
        color = Color(0xFFE0F2E9)
    ) {
        Image(
            painterResource(imageRes),
            contentDescription = null
        )
    }

}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    var step by remember { mutableStateOf(1) }
    var lemonSqueez by remember { mutableStateOf(0) }
    var requiredSqueezes by remember { mutableStateOf((1..5).random()) }

    when (step) {

        1 -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ImagePage(
                    onClick = {
                        step = 2
                        requiredSqueezes = (2..4).random()
                        lemonSqueez = 0
                    },
                    imageRes = R.drawable.lemon_tree
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    stringResource(R.string.lemon_tree),
                    fontSize = 18.sp
                )

            }
        }

        2 -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ImagePage(
                    onClick = {
                        lemonSqueez+=1
                        if (lemonSqueez >= requiredSqueezes) {
                            step = 3
                            lemonSqueez = 0
                            requiredSqueezes = (2..4).random()
                        }
                    },
                    imageRes = R.drawable.lemon_squeeze
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    stringResource(R.string.lemon),
                    fontSize = 18.sp
                )

            }
        }

        3 -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ImagePage(
                    onClick = { step = 4},
                    imageRes = R.drawable.lemon_drink
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    stringResource(R.string.glass_of_lemonade),
                    fontSize = 18.sp
                )

            }
        }

        4 -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                ImagePage(
                    onClick = { step = 1},
                    imageRes = R.drawable.lemon_restart
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    stringResource(R.string.empty_glass),
                    fontSize = 18.sp
                )

            }
        }
    }
}

