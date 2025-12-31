package zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation

sealed interface HolidayCashbackActivationEvent {
    data class OnMessage(val message: String) : HolidayCashbackActivationEvent
}