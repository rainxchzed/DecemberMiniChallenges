package zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.model

enum class ActivationButtonState(
    val displayName: String
) {
    DEFAULT("Activate"),
    ACTIVATING("Activating"),
    DONE("Done")
}