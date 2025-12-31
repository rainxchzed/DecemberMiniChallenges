package zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation

sealed interface HolidayGiftOrderEvent {
    data class OnMessage(val message: String) : HolidayGiftOrderEvent
}