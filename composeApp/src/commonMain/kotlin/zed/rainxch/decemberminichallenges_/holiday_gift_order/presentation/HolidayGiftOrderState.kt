package zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation

import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.DeliveryMethod
import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.GiftType

data class HolidayGiftOrderState(
    val selectedGiftType: GiftType? = null,
    val selectedDeliveryMethod: DeliveryMethod? = null,
    val includeGiftWrap: Boolean = false,
    val addGreetingCard: Boolean = false,
    val isGiftTypeDropdownExpanded: Boolean = false,
)