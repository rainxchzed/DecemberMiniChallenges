package zed.rainxch.decemberminichallenges_.santa_piano.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import decemberminichallenges_.composeapp.generated.resources.Res
import decemberminichallenges_.composeapp.generated.resources.ic_prize
import decemberminichallenges_.composeapp.generated.resources.ic_snow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import zed.rainxch.decemberminichallenges_.core.presentation.theme.SantaPianoColors
import zed.rainxch.decemberminichallenges_.core.presentation.theme.hostGroteskFontFamily
import zed.rainxch.decemberminichallenges_.santa_piano.domain.model.PianoKey

@Composable
fun SantaPianoRoot(
    viewModel: SantaPianoViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SantaPianoScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SantaPianoScreen(
    state: SantaPianoState,
    onAction: (SantaPianoAction) -> Unit,
) {
    val density = LocalDensity.current
    Scaffold(
        containerColor = Color(0xffDFE0DF)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Decorations(density)

            Piano(
                state = state,
                onAction = onAction
            )
        }
    }
}

@Composable
private fun BoxScope.Piano(
    state: SantaPianoState,
    onAction: (SantaPianoAction) -> Unit
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Santa's Piano",
            fontFamily = hostGroteskFontFamily(),
            fontWeight = FontWeight.SemiBold,
            color = SantaPianoColors.title,
            fontSize = 34.sp
        )

        Box {
            Row(
                modifier = Modifier
                    .height(224.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0xff1A1A1A))
                    .padding(horizontal = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                state.pianoKeys.forEach { pianoKey ->
                    PianoKey(
                        pianoKey = pianoKey,
                        isPressed = state.pressedKeys.contains(pianoKey),
                        onClick = { onAction(SantaPianoAction.OnPianoKeyClick(pianoKey)) }
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(48.dp),
                modifier = Modifier.padding(start = 68.dp)
            ) {
                repeat(6) {
                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .height(120.dp)
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 16.dp,
                                    bottomEnd = 16.dp,
                                )
                            )
                            .dropShadow(
                                CircleShape,
                                shadow = Shadow(
                                    radius = 16.dp,
                                    color = Color.Black.copy(alpha = .25f),
                                    offset = DpOffset(x = 0.dp, y = 16.dp)
                                )
                            )
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xff121212),
                                        Color(0xff232323),
                                    )
                                )
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun PianoKey(
    pianoKey: PianoKey,
    isPressed: Boolean,
    onClick: () -> Unit
) {
    val topColor by animateColorAsState(
        targetValue = if (isPressed) {
            Color(0xD9D0FFFD)
        } else {
            Color.White.copy(alpha = .85f)
        },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "topColorAnimation"
    )

    val bottomColor by animateColorAsState(
        targetValue = if (isPressed) {
            Color(0xFF88B5F4)
        } else {
            Color.White
        },
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "bottomColorAnimation"
    )

    Box(
        modifier = Modifier
            .width(74.dp)
            .height(214.dp)
            .clip(RoundedCornerShape(size = 16.dp))
            .dropShadow(
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp,
                ),
                shadow = Shadow(
                    radius = 40.dp,
                    color = Color.Black.copy(alpha = .3f),
                    offset = DpOffset(x = 0.dp, y = 10.dp)
                )
            )
            .background(
                Brush.linearGradient(
                    listOf(topColor, bottomColor)
                )
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    )
}

@Composable
private fun BoxScope.Decorations(density: Density) {
    BoxWithConstraints(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .align(Alignment.TopStart)
    ) {
        val boxWidth = with(density) { constraints.maxWidth.toDp() }
        val boxHeight = with(density) { constraints.maxHeight.toDp() }

        Icon(
            painter = painterResource(Res.drawable.ic_prize),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .offset(x = boxWidth / 3),
            tint = Color.White.copy(alpha = .6f)
        )

        Icon(
            painter = painterResource(Res.drawable.ic_snow),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .offset(
                    x = boxWidth - 64.dp,
                    y = boxHeight / 3
                ),
            tint = Color.White.copy(alpha = .6f)
        )

        Icon(
            painter = painterResource(Res.drawable.ic_prize),
            contentDescription = null,
            modifier = Modifier
                .size(42.dp)
                .offset(
                    y = boxHeight - 42.dp
                ),
            tint = Color.White.copy(alpha = .6f)
        )
    }


    BoxWithConstraints(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .align(Alignment.TopEnd)
    ) {
        val boxWidth = with(density) { constraints.maxWidth.toDp() }
        val boxHeight = with(density) { constraints.maxHeight.toDp() }

        Icon(
            painter = painterResource(Res.drawable.ic_snow),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = Color.White.copy(alpha = .6f)
        )

        Icon(
            painter = painterResource(Res.drawable.ic_prize),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .offset(
                    x = boxWidth - 64.dp,
                    y = boxHeight / 5
                ),
            tint = Color.White.copy(alpha = .6f)
        )

        Icon(
            painter = painterResource(Res.drawable.ic_snow),
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .offset(
                    x = boxWidth - 88.dp,
                    y = boxHeight - 88.dp
                ),
            tint = Color.White.copy(alpha = .6f)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SantaPianoScreen(
        state = SantaPianoState(),
        onAction = {}
    )
}