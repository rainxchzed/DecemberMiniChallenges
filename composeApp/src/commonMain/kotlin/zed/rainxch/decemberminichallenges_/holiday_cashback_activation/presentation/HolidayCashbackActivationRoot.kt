package zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import decemberminichallenges_.composeapp.generated.resources.Res
import decemberminichallenges_.composeapp.generated.resources.cashe_card
import decemberminichallenges_.composeapp.generated.resources.ic_mastercard
import decemberminichallenges_.composeapp.generated.resources.ic_visa
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.decemberminichallenges_.core.presentation.theme.montserratFontFamily
import zed.rainxch.decemberminichallenges_.core.utils.ObserveAsEvents
import zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.model.ActivationButtonState
import zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.model.CardType
import zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.utils.CardNumberVisualTransformation

@Composable
fun HolidayCashbackActivationRoot(
    viewModel: HolidayCashbackActivationViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is HolidayCashbackActivationEvent.OnMessage -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    HolidayCashbackActivationScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAction = viewModel::onAction
    )
}

@Composable
fun HolidayCashbackActivationScreen(
    state: HolidayCashbackActivationState,
    onAction: (HolidayCashbackActivationAction) -> Unit,
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
                            .border(1.dp, Color(0xff159302), RoundedCornerShape(10.dp))
                            .background(Color.Transparent)
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
            Button(
                onClick = {
                    when (state.buttonState) {
                        ActivationButtonState.DEFAULT -> {
                            onAction(HolidayCashbackActivationAction.ActivateCashback)
                        }

                        ActivationButtonState.ACTIVATING -> {}
                        ActivationButtonState.DONE -> {
                            onAction(HolidayCashbackActivationAction.ResetScreen)
                        }
                    }
                },
                enabled = state.isCardNumberValid && state.buttonState != ActivationButtonState.ACTIVATING,
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffAD3A25),
                    disabledContainerColor = Color(0xffFFBFB4),
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (state.buttonState == ActivationButtonState.ACTIVATING) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color(0xffC55539),
                            trackColor = Color(0xffFFFFFF)
                        )
                    }

                    Text(
                        text = state.buttonState.displayName,
                        fontFamily = montserratFontFamily(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        },
        containerColor = Color(0xffFFFAF0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.cashe_card),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .padding(top = 42.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(64.dp))

            Text(
                text = "Link your card to activate holiday cashback bonuses!",
                fontFamily = montserratFontFamily(),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 20.sp
            )

            Spacer(Modifier.height(20.dp))

            CardNumberTextField(
                cardNumber = state.cardNumber,
                cardType = state.cardType,
                showClearIcon = state.showClearIcon,
                isError = state.showInvalidError,
                onValueChange = {
                    onAction(HolidayCashbackActivationAction.UpdateCardNumber(it))
                },
                onClearClick = {
                    onAction(HolidayCashbackActivationAction.ClearCardNumber)
                }
            )
        }
    }
}

@Composable
fun CardNumberTextField(
    cardNumber: String,
    cardType: CardType,
    showClearIcon: Boolean,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = cardNumber,
            onValueChange = { newValue ->
                val digitsOnly = newValue.filter { it.isDigit() }
                onValueChange(digitsOnly)
            },
            placeholder = {
                Text(
                    text = "XXXX XXXX XXXX XXXX",
                    fontWeight = FontWeight.Normal,
                    fontFamily = montserratFontFamily(),
                    fontSize = 14.sp,
                    color = Color(0xffBAB4AD)
                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(
                        resource = when (cardType) {
                            CardType.VISA -> Res.drawable.ic_visa
                            CardType.MASTERCARD -> Res.drawable.ic_mastercard
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                if (showClearIcon) {
                    IconButton(onClick = onClearClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear",
                            tint = Color(0xff2C1F04)
                        )
                    }
                }
            },
            visualTransformation = CardNumberVisualTransformation(),
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontFamily = montserratFontFamily(),
                fontSize = 14.sp,
                color = Color(0xff2C1F04)
            ),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Color(0xff2C1F04),
                focusedBorderColor = Color(0xffF69C72),
                unfocusedBorderColor = Color(0xffE7D2BF),
                errorBorderColor = Color(0xffEB001B),
                errorCursorColor = Color(0xffEB001B)
            ),
            isError = isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (isError) {
            Text(
                text = "Invalid card number",
                color = Color(0xffEB001B),
                fontSize = 12.sp,
                fontFamily = montserratFontFamily(),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HolidayCashbackActivationScreen(
        state = HolidayCashbackActivationState(),
        onAction = {},
        snackbarHostState = SnackbarHostState()
    )
}