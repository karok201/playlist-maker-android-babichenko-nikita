package com.example.playlistmaker

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaylistMakerTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("Recycle")
@Preview
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val arrowIcon = painterResource(R.drawable.ic_arrow_forward)

    val tabs = context.resources.getStringArray(R.array.main_screen_tabs)
    val icons = context.resources.obtainTypedArray(R.array.main_screen_icons)

    val items = mutableListOf<MainScreenItem>()
    for (i in tabs.indices) {
        items.add(MainScreenItem(tabs[i], icons.getResourceId(i, 0)))
    }
    icons.recycle()

    Column(
        modifier = Modifier
            .background(Color(0xFF3772E7))
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .padding()
        ) {
            Row (
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 52.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 10.dp, bottom = 12.dp, end = 60.dp),
                    text = stringResource(R.string.app_name),
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            ) {
                items.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 18.dp)
                            .clickable {
                                showToast(context, item.name)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(item.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.name,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            painter = arrowIcon,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(width = 8.dp, height = 14.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun showToast(context: android.content.Context, buttonName: String) {
    Toast.makeText(
        context,
        "Нажата кнопка \"$buttonName\"",
        Toast.LENGTH_SHORT
    ).show()
}
