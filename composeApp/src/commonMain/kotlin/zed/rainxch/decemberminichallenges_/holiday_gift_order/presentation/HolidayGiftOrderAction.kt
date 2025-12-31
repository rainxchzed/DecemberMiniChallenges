package zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation

import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.DeliveryMethod
import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.GiftType

sealed interface HolidayGiftOrderAction {
    data class SelectGiftType(val giftType: GiftType) : HolidayGiftOrderAction
    data object ToggleGiftTypeDropdown : HolidayGiftOrderAction
    data class SelectDeliveryMethod(val deliveryMethod: DeliveryMethod) : HolidayGiftOrderAction
    data object ToggleGiftWrap : HolidayGiftOrderAction
    data object ToggleGreetingCard : HolidayGiftOrderAction
    data object PlaceOrder : HolidayGiftOrderAction
}