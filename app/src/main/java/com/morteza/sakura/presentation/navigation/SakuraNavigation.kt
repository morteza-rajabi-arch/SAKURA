
package com.morteza.sakura.presentation.navigation
import com.morteza.sakura.presentation.tasks.TasksScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.morteza.sakura.presentation.home.HomeScreen

private enum class SakuraTab(
    val title: String,
    val symbol: String
) {
    HOME("خانه", "⌂"),
    TASKS("کارها", "✓"),
    STUDY("مطالعه", "◷"),
    GOALS("هدف‌ها", "◎"),
    MORE("بیشتر", "•••")
}

@Composable
fun SakuraNavigation() {
    var selectedTab by rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (SakuraTab.entries[selectedTab]) {
                SakuraTab.HOME -> HomeScreen()

                SakuraTab.TASKS -> TasksScreen()

                SakuraTab.STUDY -> PlaceholderScreen(
                    title = "مطالعه",
                    description = "جلسات مطالعه و زمان واقعی مطالعه در این بخش قرار می‌گیرند."
                )

                SakuraTab.GOALS -> PlaceholderScreen(
                    title = "هدف‌ها",
                    description = "هدف‌ها و میزان پیشرفتت در این بخش نمایش داده می‌شوند."
                )

                SakuraTab.MORE -> PlaceholderScreen(
                    title = "بیشتر",
                    description = "تقویم، گزارش‌ها و تنظیمات در این بخش قرار می‌گیرند."
                )
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SakuraTab.entries.forEachIndexed { index, tab ->
                    NavigationItem(
                        tab = tab,
                        selected = selectedTab == index,
                        modifier = Modifier.weight(1f),
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationItem(
    tab: SakuraTab,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(vertical = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = tab.symbol,
                fontSize = 23.sp,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = tab.title,
                style = MaterialTheme.typography.labelSmall,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                fontWeight = if (selected) {
                    FontWeight.SemiBold
                } else {
                    FontWeight.Normal
                },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
