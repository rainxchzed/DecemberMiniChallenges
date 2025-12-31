package zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation

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

class HolidayGiftOrderViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(HolidayGiftOrderState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HolidayGiftOrderState()
        )

    private val _events = Channel<HolidayGiftOrderEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: HolidayGiftOrderAction) {
        when (action) {
            is HolidayGiftOrderAction.SelectGiftType -> {
                _state.update {
                    it.copy(
                        selectedGiftType = action.giftType,
                        isGiftTypeDropdownExpanded = false
                    )
                }
            }

            is HolidayGiftOrderAction.ToggleGiftTypeDropdown -> {
                _state.update {
                    it.copy(isGiftTypeDropdownExpanded = !it.isGiftTypeDropdownExpanded)
                }
            }

            is HolidayGiftOrderAction.SelectDeliveryMethod -> {
                _state.update { it.copy(selectedDeliveryMethod = action.deliveryMethod) }
            }

            is HolidayGiftOrderAction.ToggleGiftWrap -> {
                _state.update { it.copy(includeGiftWrap = action.include) }
            }

            is HolidayGiftOrderAction.ToggleGreetingCard -> {
                _state.update { it.copy(addGreetingCard = action.add) }
            }

            is HolidayGiftOrderAction.PlaceOrder -> {
                placeOrder()
            }
        }
    }

    private fun placeOrder() {
        val currentState = _state.value

        if (currentState.selectedGiftType == null) {
            return
        }

        viewModelScope.launch {
            delay(500)

            _events.send(HolidayGiftOrderEvent.OnMessage("Your order has been placed!"))
        }
    }
}