package zed.rainxch.decemberminichallenges_.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Bold
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Light
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Medium
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_Regular
import decemberminichallenges_.composeapp.generated.resources.HostGrotesk_SemiBold
import decemberminichallenges_.composeapp.generated.resources.Montserrat_Black
import decemberminichallenges_.composeapp.generated.resources.Montserrat_Bold
import decemberminichallenges_.composeapp.generated.resources.Montserrat_Medium
import decemberminichallenges_.composeapp.generated.resources.Montserrat_Regular
import decemberminichallenges_.composeapp.generated.resources.Montserrat_SemiBold
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
@Composable
fun montserratFontFamily(): FontFamily {
    return FontFamily(
        listOf(
            Font(Res.font.Montserrat_Regular, FontWeight.Normal),
            Font(Res.font.Montserrat_Medium, FontWeight.Medium),
            Font(Res.font.Montserrat_SemiBold, FontWeight.SemiBold),
            Font(Res.font.Montserrat_Bold, FontWeight.Bold),
            Font(Res.font.Montserrat_Black, FontWeight.Black),
        )
    )
}