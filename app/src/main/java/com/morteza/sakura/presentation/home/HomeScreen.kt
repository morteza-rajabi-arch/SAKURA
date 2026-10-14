
package com.morteza.sakura.presentation.home
import android.icu.text.SimpleDateFormat
import android.icu.util.ULocale
import java.util.Date
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.morteza.sakura.R

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.morteza.sakura.presentation.tasks.AddTaskDialog
import com.morteza.sakura.presentation.tasks.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.runtime.collectAsState

private val SakuraCardPink = Color(0xFFF7E8EC)
private val SakuraSoftPink = Color(0xFFE8B9C5)
private val SakuraIconBackground = Color(0xFFF5E7EA)

private fun getTodayDate(): String {
    val locale = ULocale("fa_IR@calendar=persian")
    val formatter = SimpleDateFormat("EEEE، d MMMM yyyy", locale)
    return formatter.format(Date())
}


@Composable
fun HomeScreen() {
    var showAddTaskDialog by remember { mutableStateOf(false) }
    val taskViewModel: TaskViewModel = viewModel()
    val tasks by taskViewModel.tasks.collectAsState()
    val inProgressTasks = tasks.count { it.status == "IN_PROGRESS" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(top = 12.dp, bottom = 24.dp)
            ) {
                HeaderSection()

                Spacer(modifier = Modifier.height(24.dp))

                FocusCard(
                    totalTasks = tasks.size,
                    completedTasks = tasks.count { it.status == "COMPLETED" }
                )

                Spacer(modifier = Modifier.height(26.dp))

                SectionTitle(
                    title = "نمای کلی امروز",
                    action = null
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OverviewCard(
                        title = "کل وظایف",
                        value = tasks.size.toString(),
                        symbol = "✓",
                        modifier = Modifier.weight(1f)
                    )

                    OverviewCard(
                        title = "در حال انجام",
                        value = inProgressTasks.toString(),
                        symbol = "◷",
                        modifier = Modifier.weight(1f)
                    )

                    OverviewCard(
                        title = "هدف فعال",
                        value = "۴",
                        symbol = "◎",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))

                SectionTitle(
                    title = "برنامه پیش رو",
                    action = "مشاهده همه"
                )

                Spacer(modifier = Modifier.height(12.dp))

                ScheduleCard()
            }

            Surface(
                onClick = { showAddTaskDialog = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(18.dp)
                    .size(56.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                shadowElevation = 5.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "+",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }

        if (showAddTaskDialog) {
            AddTaskDialog(
                onDismiss = {
                    showAddTaskDialog = false
                },
                onAdd = { title, description ->
                    taskViewModel.addTask(
                        title = title,
                        description = description
                    )
                    showAddTaskDialog = false
                }
            )
        }
    }
}

@Composable
private fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // در چیدمان RTL، اولین فرزند در سمت راست قرار می‌گیرد.
        Image(
            painter = painterResource(id = R.drawable.sakura_logo),
            contentDescription = "لوگوی ساکورا",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.size(14.dp))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = getTodayDate(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "صبح بخیر، نگار",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "امروز آرام و پیوسته پیش برو.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
private fun FocusCard(
    totalTasks: Int,
    completedTasks: Int
) {
    val progress = if (totalTasks > 0) {
        completedTasks.toFloat() / totalTasks
    } else {
        0f
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = SakuraCardPink
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "تمرکز امروز",
                style = MaterialTheme.typography.labelLarge,
                color = Color(0xFF8F4D62),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "برای یک روز خوب آماده‌ای؟",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF302A2C),
                        textAlign = TextAlign.Right
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "$completedTasks از $totalTasks کار انجام شده",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6F6065)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(7.dp)
                            .clip(CircleShape),
                        color = Color(0xFFB86F83),
                        trackColor = Color(0xFFE8CDD5)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "۲ ساعت و ۴۰ دقیقه مطالعه",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6F6065)
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))

                Box(
                    modifier = Modifier.size(76.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = CircleShape,
                        color = Color(0xFFEAD1D9)
                    ) {}

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        shape = CircleShape,
                        color = SakuraCardPink,
                        border = BorderStroke(
                            width = 5.dp,
                            color = SakuraSoftPink
                        )
                    ) {}

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${(progress * 100).toInt()}٪",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8F4D62)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(
    title: String,
    action: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = action ?: "",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun OverviewCard(
    title: String,
    value: String,
    symbol: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(34.dp),
                shape = CircleShape,
                color = SakuraIconBackground
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = symbol,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF8F4D62)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ScheduleCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "امروز",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "هنوز برنامه‌ای ثبت نشده است",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "پس از ثبت وظایف و جلسات مطالعه، برنامه‌ی شما اینجا نمایش داده می‌شود.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.size(12.dp))

                Surface(
                    modifier = Modifier.size(42.dp),
                    shape = RoundedCornerShape(14.dp),
                    color = SakuraIconBackground
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "◷",
                            fontSize = 23.sp,
                            color = Color(0xFF8F4D62)
                        )
                    }
                }
            }
        }
    }
}



@Composable
private fun NavigationItem(
    symbol: String,
    title: String,
    selected: Boolean = false
) {
    val itemColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = symbol,
            fontSize = 22.sp,
            color = itemColor,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = itemColor,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}
