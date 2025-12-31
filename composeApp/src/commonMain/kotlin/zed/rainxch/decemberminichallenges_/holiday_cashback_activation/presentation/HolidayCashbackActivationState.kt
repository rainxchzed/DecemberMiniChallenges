package zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation

import zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.model.ActivationButtonState
import zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.model.CardType

data class HolidayCashbackActivationState(
    val cardNumber: String = "",
    val cardType: CardType = CardType.MASTERCARD,
    val showClearIcon: Boolean = false,
    val isCardNumberValid: Boolean = false,
    val showInvalidError: Boolean = false,
    val buttonState: ActivationButtonState = ActivationButtonState.DEFAULT,
    val showSuccessSnackbar: Boolean = false
)