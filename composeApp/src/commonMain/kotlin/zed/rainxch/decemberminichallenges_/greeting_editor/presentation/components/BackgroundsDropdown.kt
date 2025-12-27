package zed.rainxch.decemberminichallenges_.greeting_editor.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import zed.rainxch.decemberminichallenges_.core.presentation.theme.GreetingEditorColors
import zed.rainxch.decemberminichallenges_.core.presentation.theme.montserratFontFamily
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground

@Composable
fun BackgroundsDropdown(
    expanded: Boolean,
    onExpandDropdown: () -> Unit,
    onDismissRequest: () -> Unit,
    onSelectOption: (GreetingBackground) -> Unit,
    selectedOption: GreetingBackground,
    options: List<GreetingBackground> = GreetingBackground.entries.toList(),
    modifier: Modifier = Modifier
) {
    var columnWidth by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    Column(
        modifier = modifier
            .onGloballyPositioned {
                with(density) {
                    columnWidth = it.size.width.toDp()
                }
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = {
                    onExpandDropdown()
                })
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = GreetingEditorColors.strockDark
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
                .background(GreetingEditorColors.whiteBackground)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedOption.displayName(),
                color = GreetingEditorColors.text,
                fontFamily = montserratFontFamily(),
                fontWeight = FontWeight.Medium
            )

            Icon(
                imageVector = if (expanded) {
                    Icons.Default.KeyboardArrowUp
                } else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        Box {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismissRequest,
                containerColor = GreetingEditorColors.whiteBackground,
                shadowElevation = 4.dp,
                border = BorderStroke(
                    width = 1.dp,
                    color = GreetingEditorColors.strockDark
                ),
                shape = RoundedCornerShape(10.dp),
                offset = DpOffset(
                    x = 0.dp,
                    y = 4.dp
                ),
                modifier = Modifier.width(columnWidth)
            ) {
                options.forEach { option ->
                    if (option != selectedOption) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    onSelectOption(option)
                                })
                                .background(GreetingEditorColors.whiteBackground)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = option.displayName(),
                                color = GreetingEditorColors.text,
                                fontFamily = montserratFontFamily(),
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}