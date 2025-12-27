package zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model

import androidx.compose.ui.graphics.Brush
import zed.rainxch.decemberminichallenges_.core.presentation.theme.GreetingEditorColors

enum class GreetingBackground {
    GoldenEve,
    SnowyNight,
    EvergreenWish,
    FrostyLight;

    fun displayName(): String {
        return when (this) {
            GoldenEve -> "Golden Eve"
            SnowyNight -> "Snowy Night"
            EvergreenWish -> "Evergreen Wish"
            FrostyLight -> "Frosty Light"
        }
    }

    fun primaryColor(): Brush {
        return when (this) {
            GoldenEve -> GreetingEditorColors.goldenEveGradient
            SnowyNight -> GreetingEditorColors.snowyNightGradient
            EvergreenWish -> GreetingEditorColors.evergreenWish
            FrostyLight -> GreetingEditorColors.frostyLight
        }
    }
}