package zed.rainxch.decemberminichallenges_.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Bold
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Light
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Medium
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Regular
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_SemiBold
import decemberminichallenges_.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun hostGroteskFontFamily(): FontFamily {
    return FontFamily(
        listOf(
            Font(Res.font.HostGrotesk_Light, FontWeight.Light),
            Font(Res.font.HostGrotesk_Regular, FontWeight.Normal),
            Font(Res.font.HostGrotesk_Medium, FontWeight.Medium),
            Font(Res.font.HostGrotesk_SemiBold, FontWeight.SemiBold),
            Font(Res.font.HostGrotesk_Bold, FontWeight.Bold),
        )
    )
}