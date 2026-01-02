package zed.rainxch.decemberminichallenges_.snow_globe_shake.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import decemberminichallenges_.composeapp.generated.resources.Res
import decemberminichallenges_.composeapp.generated.resources.snowflake
import decemberminichallenges_.composeapp.generated.resources.winter_magic_globe
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import zed.rainxch.decemberminichallenges_.core.presentation.theme.montserratFontFamily
import kotlin.random.Random

@Composable
fun SnowGlobeShakeRoot(
    viewModel: SnowGlobeShakeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SnowGlobeShakeScreen(
        state = state,
        onSnowfallComplete = viewModel::onSnowfallComplete
    )
}

@Composable
fun SnowGlobeShakeScreen(
    state: SnowGlobeShakeState,
    onSnowfallComplete: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xffE1ECFF),
                        Color(0xffE0E0FF),
                        Color(0xffDCBFF9),
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Winter Magic",
            fontFamily = montserratFontFamily(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 42.sp,
            color = Color(0xff3730A3),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        Box(
            modifier = Modifier.width(380.dp),
        ) {
            Image(
                painter = painterResource(Res.drawable.winter_magic_globe),
                contentDescription = null,
                modifier = Modifier
                    .width(380.dp)
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )

            if (state.isSnowing) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(318.dp)
                            .height(290.dp)
                            .clip(CircleShape)
                    ) {
                        key(state.animationKey) {
                            Snowflakes(
                                onAnimationComplete = onSnowfallComplete
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Snowflakes(
    modifier: Modifier = Modifier,
    onAnimationComplete: () -> Unit = {}
) {
    val snowflakes = remember {
        List(100) {
            val shape =
                if (Random.nextFloat() < 0.5f) SnowFlakeShape.Dot else SnowFlakeShape.Snowflake

            SnowFlake(
                x = Random.nextFloat(),
                y = 0f,
                shape = shape,
                size = when (shape) {
                    SnowFlakeShape.Dot -> DpSize(
                        width = Random.nextInt(1, 7).dp,
                        height = Random.nextInt(1, 7).dp
                    )

                    SnowFlakeShape.Snowflake -> DpSize(
                        width = 12.dp,
                        height = 12.dp
                    )
                }
            )
        }
    }

    var completedCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(completedCount) {
        if (completedCount >= snowflakes.size) {
            onAnimationComplete()
        }
    }

    Box(modifier = modifier) {
        snowflakes.forEach { snowflake ->
            AnimatedSnowflake(
                snowflake = snowflake,
                onComplete = {
                    completedCount++
                }
            )
        }
    }
}

@Composable
private fun AnimatedSnowflake(
    snowflake: SnowFlake,
    onComplete: () -> Unit
) {
    val delay = remember { Random.nextInt(0, 3001) }
    var hasCompleted by remember { mutableStateOf(false) }

    val yOffset = remember { Animatable(-.1f) }

    LaunchedEffect(Unit) {
        delay(delay.toLong())
        yOffset.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        )
        if (!hasCompleted) {
            hasCompleted = true
            onComplete()
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val maxHeight = constraints.maxHeight.toFloat()
        val maxWidth = constraints.maxWidth.toFloat()

        val xPosition = snowflake.x * maxWidth
        val yPosition = yOffset.value * maxHeight

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = xPosition.toInt(),
                        y = yPosition.toInt()
                    )
                }
        ) {
            when (snowflake.shape) {
                SnowFlakeShape.Dot -> {
                    Box(
                        modifier = Modifier
                            .size(snowflake.size.width)
                            .background(Color.White, CircleShape)
                    )
                }

                SnowFlakeShape.Snowflake -> {
                    Icon(
                        painter = painterResource(Res.drawable.snowflake),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(snowflake.size.width)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SnowGlobeShakeScreen(
        state = SnowGlobeShakeState(),
    )
}