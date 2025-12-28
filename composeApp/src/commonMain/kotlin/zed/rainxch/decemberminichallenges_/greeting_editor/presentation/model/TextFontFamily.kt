package zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model

import decemberminichallenges_.composeapp.generated.resources.Montserrat_Medium
import decemberminichallenges_.composeapp.generated.resources.PTSerif_Regular
import decemberminichallenges_.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.FontResource

enum class TextFontFamily(
    val fontResource: FontResource
) {
    Montserrat(fontResource = Res.font.Montserrat_Medium),
    PTSerif(fontResource = Res.font.PTSerif_Regular);

    fun label() : String {
        return when (this) {
            Montserrat -> "Mnt"
            PTSerif -> "Ser"
        }
    }
    fun displayName() : String {
        return when (this) {
            Montserrat -> "Montserrat"
            PTSerif -> "PT Serif"
        }
    }
}