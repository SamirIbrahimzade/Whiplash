package com.qarone.whiplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qarone.whiplash.domain.GetCurrentMonthDaysUseCase
import com.qarone.whiplash.domain.model.ProgressDay
import com.qarone.whiplash.ui.StartButton
import com.qarone.whiplash.ui.theme.WhiplashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhiplashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Timer(minutes = "25", seconds = "00")
                        StartButton()
                        MonthProgress(monthDays = GetCurrentMonthDaysUseCase()())
                    }
                }
            }
        }

    }
}

@Composable
fun Timer(modifier: Modifier = Modifier, minutes: String, seconds: String) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = "$minutes:$seconds",
        fontSize = 80.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold

    )
}

@Composable
fun MonthProgress(modifier: Modifier = Modifier, monthDays: List<ProgressDay>) {
    LazyVerticalGrid(
        modifier = modifier
            .width(200.dp)
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(7),
    ) {
        val firstEmptyBoxCount = monthDays[0].weekDay - 1
        items(firstEmptyBoxCount) {
            Box(contentAlignment = Alignment.Center) { Spacer(modifier.size(15.dp)) }
        }
        items(monthDays) { day ->
            Box(
                modifier = modifier.clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Spacer(
                    modifier
                        .size(16.dp)
                        .background(color = Color.DarkGray, shape = RoundedCornerShape(3.dp))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerPreview() {
    WhiplashTheme {
        Timer(minutes = "25", seconds = "00")
    }
}

@Preview(showBackground = true)
@Composable
fun MonthProgressPreview() {
    WhiplashTheme {
        MonthProgress(monthDays = GetCurrentMonthDaysUseCase()())
    }
}
