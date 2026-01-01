package zed.rainxch.decemberminichallenges_.snow_globe_shake.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import decemberminichallenges_.composeapp.generated.resources.Res
import decemberminichallenges_.composeapp.generated.resources.winter_magic_globe
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.decemberminichallenges_.core.presentation.theme.montserratFontFamily

@Composable
fun SnowGlobeShakeRoot(
    viewModel: SnowGlobeShakeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SnowGlobeShakeScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SnowGlobeShakeScreen(
    state: SnowGlobeShakeState,
    onAction: (SnowGlobeShakeAction) -> Unit,
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
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SnowGlobeShakeScreen(
        state = SnowGlobeShakeState(),
        onAction = {}
    )
}