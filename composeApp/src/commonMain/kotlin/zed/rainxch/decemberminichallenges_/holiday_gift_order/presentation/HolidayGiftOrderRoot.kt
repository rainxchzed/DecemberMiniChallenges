package zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import decemberminichallenges_.composeapp.generated.resources.Res
import decemberminichallenges_.composeapp.generated.resources.gift_heart
import decemberminichallenges_.composeapp.generated.resources.ic_order
import decemberminichallenges_.composeapp.generated.resources.left_decor
import decemberminichallenges_.composeapp.generated.resources.right_decor
import decemberminichallenges_.composeapp.generated.resources.stars
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.decemberminichallenges_.core.presentation.theme.GreetingEditorColors
import zed.rainxch.decemberminichallenges_.core.presentation.theme.montserratFontFamily
import zed.rainxch.decemberminichallenges_.core.utils.ObserveAsEvents
import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.components.RoundedRadioButton
import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.DeliveryMethod
import zed.rainxch.decemberminichallenges_.holiday_gift_order.presentation.models.GiftType

@Composable
fun HolidayGiftOrderRoot(
    viewModel: HolidayGiftOrderViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is HolidayGiftOrderEvent.OnMessage -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    HolidayGiftOrderScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAction = viewModel::onAction
    )
}

@Composable
fun HolidayGiftOrderScreen(
    state: HolidayGiftOrderState,
    onAction: (HolidayGiftOrderAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color(0xffEEEEEE), RoundedCornerShape(10.dp))
                            .background(Color(0xffF6F6F6))
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xff159302),
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = snackbarData.visuals.message,
                            fontFamily = montserratFontFamily(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color(0xff05133E)
                        )

                        Spacer(Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color(0xff05133E),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box {
                Image(
                    painter = painterResource(Res.drawable.stars),
                    contentDescription = null,
                    modifier = Modifier.height(170.dp),
                    contentScale = ContentScale.Crop
                )

                Button(
                    onClick = {
                        onAction(HolidayGiftOrderAction.PlaceOrder)
                    },
                    enabled = state.selectedGiftType != null && state.selectedDeliveryMethod != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 24.dp
                        )
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff346CEE),
                        disabledContainerColor = Color(0xffA3BFFF),
                        contentColor = Color.White,
                        disabledContentColor = Color.White,
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_order),
                            contentDescription = null,
                            modifier = Modifier.size(22.dp)
                        )

                        Text(
                            text = "Place Order",
                            fontFamily = montserratFontFamily(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        containerColor = Color(0xffF6F7FF)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xff2563EB),
                                Color(0xff7996FC),
                                Color(0xffA5A7FF),
                            )
                        )
                    )
                    .padding(vertical = 16.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.gift_heart),
                        contentDescription = null,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(8.dp),
                        tint = Color(0xff366DEE)
                    )

                    Text(
                        text = "Festive Order Form",
                        fontFamily = montserratFontFamily(),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }

                Image(
                    painter = painterResource(Res.drawable.left_decor),
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .align(Alignment.CenterStart)
                )

                Image(
                    painter = painterResource(Res.drawable.right_decor),
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)
                        .align(Alignment.CenterEnd)
                )
            }

            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        Brush.linearGradient(
                            listOf(
                                Color(0xff2563EB),
                                Color(0xff7996FC),
                                Color(0xffA5A7FF),
                            )
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .background(Color.White)
                    .clickable(onClick = {
                        onAction(HolidayGiftOrderAction.ToggleGiftTypeDropdown)
                    })
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Gift Type *",
                        fontFamily = montserratFontFamily(),
                        fontWeight = FontWeight.Medium,
                        color = Color(0xff6D7B8E),
                        fontSize = 14.sp
                    )


                    Icon(
                        imageVector = if (state.isGiftTypeDropdownExpanded) {
                            Icons.Default.KeyboardArrowUp
                        } else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color(0xff05133E)
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = if (state.selectedGiftType != null) {
                        state.selectedGiftType.displayName
                    } else "Gift Type",
                    fontFamily = montserratFontFamily(),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff05133E),
                    fontSize = 18.sp
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                DropdownMenu(
                    expanded = state.isGiftTypeDropdownExpanded,
                    onDismissRequest = {
                        onAction(HolidayGiftOrderAction.ToggleGiftTypeDropdown)
                    },
                    containerColor = GreetingEditorColors.whiteBackground,
                    shadowElevation = 4.dp,
                    border = BorderStroke(
                        width = 1.dp,
                        Brush.linearGradient(
                            listOf(
                                Color(0xff2563EB),
                                Color(0xff7996FC),
                                Color(0xffA5A7FF),
                            )
                        )
                    ),
                    shape = RoundedCornerShape(10.dp),
                    offset = DpOffset(
                        x = 0.dp,
                        y = 4.dp
                    ),
                    modifier = Modifier.fillMaxWidth(.9f),
                ) {
                    GiftType.entries.forEach { giftType ->
                        Text(
                            text = giftType.displayName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    onAction(HolidayGiftOrderAction.SelectGiftType(giftType))
                                })
                                .padding(16.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = montserratFontFamily(),
                            color = Color(0xff05133E)
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        Brush.linearGradient(
                            listOf(
                                Color(0xff2563EB),
                                Color(0xff7996FC),
                                Color(0xffA5A7FF),
                            )
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .dropShadow(
                        shape = RoundedCornerShape(10.dp),
                        shadow = androidx.compose.ui.graphics.shadow.Shadow(
                            radius = 4.dp,
                            spread = 0.dp,
                            color = Color.Black.copy(alpha = .08f)
                        )
                    )
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Delivery Method *",
                    fontFamily = montserratFontFamily(),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff6D7B8E),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 6.dp, start = 6.dp)
                )

                Spacer(Modifier.height(16.dp))

                DeliveryMethod.entries.forEach { method ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        RadioButton(
                            selected = state.selectedDeliveryMethod == method,
                            onClick = {
                                onAction(HolidayGiftOrderAction.SelectDeliveryMethod(method))
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xff346CEE),
                                unselectedColor = Color(0xffD4E1FF)
                            )
                        )

                        Text(
                            text = method.displayName,
                            fontFamily = montserratFontFamily(),
                            fontWeight = FontWeight.Medium,
                            color = Color(0xff05133E),
                            fontSize = 18.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        Brush.linearGradient(
                            listOf(
                                Color(0xff2563EB),
                                Color(0xff7996FC),
                                Color(0xffA5A7FF),
                            )
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Extras",
                    fontFamily = montserratFontFamily(),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xff6D7B8E),
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(16.dp))

                RoundedRadioButton(
                    selected = state.includeGiftWrap,
                    onClick = {
                        onAction(HolidayGiftOrderAction.ToggleGiftWrap)
                    },
                    text = "Include Gift Wrap"
                )

                Spacer(Modifier.height(12.dp))

                RoundedRadioButton(
                    selected = state.addGreetingCard,
                    onClick = {
                        onAction(HolidayGiftOrderAction.ToggleGreetingCard)
                    },
                    text = "Add Greeting Card"
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HolidayGiftOrderScreen(
        state = HolidayGiftOrderState(isGiftTypeDropdownExpanded = true),
        onAction = {},
        snackbarHostState = SnackbarHostState()
    )
}