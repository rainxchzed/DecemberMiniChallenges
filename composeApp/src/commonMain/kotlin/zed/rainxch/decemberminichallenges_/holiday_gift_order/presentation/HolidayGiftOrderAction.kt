package zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation

import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.DeliveryMethod
import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.GiftType

sealed interface HolidayGiftOrderAction {
    data class SelectGiftType(val giftType: GiftType) : HolidayGiftOrderAction
    data object ToggleGiftTypeDropdown : HolidayGiftOrderAction
    data class SelectDeliveryMethod(val deliveryMethod: DeliveryMethod) : HolidayGiftOrderAction
    data class ToggleGiftWrap(val include: Boolean) : HolidayGiftOrderAction
    data class ToggleGreetingCard(val add: Boolean) : HolidayGiftOrderAction
    data object PlaceOrder : HolidayGiftOrderAction
}