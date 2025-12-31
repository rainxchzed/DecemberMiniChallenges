package zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation

sealed interface HolidayCashbackActivationAction {
    data class UpdateCardNumber(val input: String) : HolidayCashbackActivationAction
    data object ClearCardNumber : HolidayCashbackActivationAction
    data object ActivateCashback : HolidayCashbackActivationAction
    data object DismissSnackbar : HolidayCashbackActivationAction
    data object ResetScreen : HolidayCashbackActivationAction
}