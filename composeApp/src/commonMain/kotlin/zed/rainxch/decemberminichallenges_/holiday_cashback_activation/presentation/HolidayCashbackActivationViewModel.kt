package zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.model.ActivationButtonState
import zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.model.CardType

class HolidayCashbackActivationViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(HolidayCashbackActivationState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HolidayCashbackActivationState()
        )

    private val _events = Channel<HolidayCashbackActivationEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: HolidayCashbackActivationAction) {
        when (action) {
            is HolidayCashbackActivationAction.UpdateCardNumber -> {
                handleCardNumberInput(action.input)
            }
            is HolidayCashbackActivationAction.ClearCardNumber -> {
                _state.update {
                    HolidayCashbackActivationState()
                }
            }
            is HolidayCashbackActivationAction.ActivateCashback -> {
                activateCashback()
            }
            is HolidayCashbackActivationAction.DismissSnackbar -> {
                _state.update { it.copy(showSuccessSnackbar = false) }
            }
            is HolidayCashbackActivationAction.ResetScreen -> {
                _state.update {
                    HolidayCashbackActivationState()
                }
            }
        }
    }

    private fun handleCardNumberInput(input: String) {
        val digitsOnly = input.filter { it.isDigit() }

        val limitedDigits = digitsOnly.take(16)

        val cardType = when {
            limitedDigits.startsWith("4") -> CardType.VISA
            limitedDigits.startsWith("2") || limitedDigits.startsWith("5") -> CardType.MASTERCARD
            else -> CardType.MASTERCARD
        }

        val showClear = limitedDigits.isNotEmpty()

        val isValid = limitedDigits.length == 16 && isValidLuhn(limitedDigits)
        val showError = limitedDigits.length == 16 && !isValidLuhn(limitedDigits)

        val buttonState = if (isValid) {
            ActivationButtonState.DEFAULT
        } else {
            ActivationButtonState.DEFAULT
        }

        _state.update {
            it.copy(
                cardNumber = limitedDigits,
                cardType = cardType,
                showClearIcon = showClear,
                isCardNumberValid = isValid,
                showInvalidError = showError,
                buttonState = buttonState
            )
        }
    }

    private fun isValidLuhn(cardNumber: String): Boolean {
        var sum = 0
        var shouldDouble = false

        for (i in cardNumber.length - 1 downTo 0) {
            var digit = cardNumber[i].digitToInt()

            if (shouldDouble) {
                digit *= 2
                if (digit > 9) {
                    digit -= 9
                }
            }

            sum += digit
            shouldDouble = !shouldDouble
        }

        return sum % 10 == 0
    }

    private fun activateCashback() {
        val currentState = _state.value

        if (!currentState.isCardNumberValid) return

        viewModelScope.launch {
            _state.update {
                it.copy(buttonState = ActivationButtonState.ACTIVATING)
            }

            delay(2000)

            _state.update {
                it.copy(
                    buttonState = ActivationButtonState.DONE,
                )
            }

            _events.send(HolidayCashbackActivationEvent.OnMessage("Holiday Cashback Activated!"))
        }
    }
}