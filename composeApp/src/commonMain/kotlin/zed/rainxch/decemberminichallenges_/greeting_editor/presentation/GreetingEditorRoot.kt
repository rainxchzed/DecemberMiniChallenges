package zed.rainxch.decemberminichallenges_.greeting_editor.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import decemberminichallenges_.composeapp.generated.resources.Res
import decemberminichallenges_.composeapp.generated.resources.evergreen_wish_bottom
import decemberminichallenges_.composeapp.generated.resources.evergreen_wish_top_left
import decemberminichallenges_.composeapp.generated.resources.evergreen_wish_top_right
import decemberminichallenges_.composeapp.generated.resources.frosty_light_bottom
import decemberminichallenges_.composeapp.generated.resources.frosty_light_decor
import decemberminichallenges_.composeapp.generated.resources.frosty_light_top
import decemberminichallenges_.composeapp.generated.resources.golden_eve_decor
import decemberminichallenges_.composeapp.generated.resources.snowy_night_decor_bottom
import decemberminichallenges_.composeapp.generated.resources.snowy_night_decor_top
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zed.rainxch.decemberminichallenges_.core.presentation.theme.GreetingEditorColors
import zed.rainxch.decemberminichallenges_.core.presentation.theme.montserratFontFamily
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.components.BackgroundsDropdown
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.components.EditorTextField
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextColor
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextFontFamily
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextSize

@Composable
fun GreetingEditorRoot(
    viewModel: GreetingEditorViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GreetingEditorScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingEditorScreen(
    state: GreetingEditorState,
    onAction: (GreetingEditorAction) -> Unit,
) {
    Scaffold(
        topBar = {
            GreetingEditorTopbar(state, onAction)
        },
        containerColor = GreetingEditorColors.whiteBackground
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            EditorBackground(
                background = state.selectedBackground,
                modifier = Modifier.fillMaxSize()
            )

            EditorToolbar(state, onAction)

            EditorTextField(
                state = state,
                onAction = onAction,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun BoxScope.EditorToolbar(
    state: GreetingEditorState,
    onAction: (GreetingEditorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(22.dp)
            .statusBarsPadding()
            .padding(bottom = 16.dp)
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(16.dp))
            .dropShadow(
                shape = RoundedCornerShape(size = 16.dp),
                shadow = Shadow(
                    radius = 4.dp,
                    spread = 0.dp,
                    color = Color(0xff562100).copy(alpha = .24f)
                )
            )
            .background(GreetingEditorColors.whiteBackground)
            .padding(8.dp)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    onAction(GreetingEditorAction.OnToolbarBoldClick)
                })
                .background(
                    if (state.isCurrentlyBold) {
                        Color(0xffEFEFF0)
                    } else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FormatBold,
                contentDescription = null,
                tint = GreetingEditorColors.text
            )
        }
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    onAction(GreetingEditorAction.OnToolbarItalicClick)
                })
                .background(
                    if (state.isCurrentlyItalic) {
                        Color(0xffEFEFF0)
                    } else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FormatItalic,
                contentDescription = null,
                tint = GreetingEditorColors.text
            )
        }
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    onAction(GreetingEditorAction.OnToolbarUnderlineClick)
                })
                .background(
                    if (state.isCurrentlyUnderline) {
                        Color(0xffEFEFF0)
                    } else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FormatUnderlined,
                contentDescription = null,
                tint = GreetingEditorColors.text
            )
        }

        if (state.isSelectColorDropdownExpanded) {
            TextColorDropdown(state, onAction)
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    onAction(GreetingEditorAction.OnToolbarColorClick)
                })
                .background(
                    if (state.isSelectColorDropdownExpanded) {
                        Color(0xffEFEFF0)
                    } else Color.Transparent
                )
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(
                        width = 2.dp,
                        color = GreetingEditorColors.text,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(state.currentColor.color)
            )

            Icon(
                imageVector = if (state.isSelectColorDropdownExpanded) {
                    Icons.Default.KeyboardArrowUp
                } else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = GreetingEditorColors.text,
                modifier = Modifier.size(18.dp)
            )
        }

        if (state.isSelectFontFamilyDropdownExpanded) {
            FontFamilyDropdown(state, onAction)
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    onAction(GreetingEditorAction.OnToolbarFontFamilyClick)
                })
                .background(
                    if (state.isSelectFontFamilyDropdownExpanded) {
                        Color(0xffEFEFF0)
                    } else Color.Transparent
                )
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = state.currentFontFamily.label(),
                color = GreetingEditorColors.text,
                fontFamily = Font(state.currentFontFamily.fontResource).toFontFamily(),
                fontSize = 16.sp
            )

            Icon(
                imageVector = if (state.isSelectFontFamilyDropdownExpanded) {
                    Icons.Default.KeyboardArrowUp
                } else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = GreetingEditorColors.text,
                modifier = Modifier.size(18.dp)
            )
        }

        if (state.isSelectFontSizeDropdownExpanded) {
            FontSizeDropdown(state, onAction)
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    onAction(GreetingEditorAction.OnToolbarFontSizeClick)
                })
                .background(
                    if (state.isSelectFontSizeDropdownExpanded) {
                        Color(0xffEFEFF0)
                    } else Color.Transparent
                )
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                imageVector = Icons.Default.TextFormat,
                contentDescription = null,
                tint = GreetingEditorColors.text,
                modifier = Modifier.size(24.dp)
            )

            Icon(
                imageVector = if (state.isSelectFontSizeDropdownExpanded) {
                    Icons.Default.KeyboardArrowUp
                } else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = GreetingEditorColors.text,
                modifier = Modifier.size(18.dp)
            )
        }

        Icon(
            imageVector = Icons.Default.RestartAlt,
            contentDescription = null,
            tint = GreetingEditorColors.text,
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = {
                    onAction(GreetingEditorAction.OnToolbarResetClick)
                })
                .rotate(45f)
        )
    }
}

@Composable
fun FontFamilyDropdown(
    state: GreetingEditorState,
    onAction: (GreetingEditorAction) -> Unit,
    options: List<TextFontFamily> = TextFontFamily.entries.toList()
) {
    Box {
        DropdownMenu(
            expanded = state.isSelectFontFamilyDropdownExpanded,
            onDismissRequest = {
                onAction(GreetingEditorAction.OnToolbarFontFamilyDropdownDismiss)
            },
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
        ) {
            options.forEach { option ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            onAction(GreetingEditorAction.OnToolbarFontFamilyChange(option))
                        })
                        .background(GreetingEditorColors.whiteBackground)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = option.displayName(),
                        color = GreetingEditorColors.text,
                        fontFamily = Font(option.fontResource).toFontFamily(),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun FontSizeDropdown(
    state: GreetingEditorState,
    onAction: (GreetingEditorAction) -> Unit,
    options: List<TextSize> = TextSize.entries.toList()
) {
    Box {
        DropdownMenu(
            expanded = state.isSelectFontSizeDropdownExpanded,
            onDismissRequest = {
                onAction(GreetingEditorAction.OnToolbarFontSizeDropdownDismiss)
            },
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
        ) {
            options.forEach { option ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            onAction(GreetingEditorAction.OnToolbarFontSizeChange(option))
                        })
                        .background(GreetingEditorColors.whiteBackground)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = option.label(),
                        color = GreetingEditorColors.text,
                        fontFamily = montserratFontFamily(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun TextColorDropdown(
    state: GreetingEditorState,
    onAction: (GreetingEditorAction) -> Unit,
    options: List<TextColor> = TextColor.entries.toList()
) {
    Box {
        DropdownMenu(
            expanded = state.isSelectColorDropdownExpanded,
            onDismissRequest = {
                onAction(GreetingEditorAction.OnToolbarColorDropdownDismiss)
            },
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
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                options.forEach { option ->
                    Box(
                        Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .border(
                                width = 1.dp,
                                color = GreetingEditorColors.strockDark,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .background(option.color)
                            .clickable(onClick = {
                                onAction(GreetingEditorAction.OnToolbarColorChange(option))
                            })
                    )
                }
            }
        }
    }
}

@Composable
private fun EditorBackground(
    background: GreetingBackground,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(background.primaryColor()),
        contentAlignment = Alignment.Center
    ) {
        when (background) {
            GreetingBackground.GoldenEve -> {
                Image(
                    painter = painterResource(Res.drawable.golden_eve_decor),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(.8f)
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    contentScale = ContentScale.Crop
                )
            }

            GreetingBackground.SnowyNight -> {
                Image(
                    painter = painterResource(Res.drawable.snowy_night_decor_top),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .align(Alignment.TopCenter),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(Res.drawable.snowy_night_decor_bottom),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )
            }

            GreetingBackground.EvergreenWish -> {
                Image(
                    painter = painterResource(Res.drawable.evergreen_wish_top_left),
                    contentDescription = null,
                    modifier = Modifier
                        .height(164.dp)
                        .align(Alignment.TopStart),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(Res.drawable.evergreen_wish_top_right),
                    contentDescription = null,
                    modifier = Modifier
                        .height(164.dp)
                        .align(Alignment.TopEnd),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(Res.drawable.evergreen_wish_bottom),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )
            }

            GreetingBackground.FrostyLight -> {
                Image(
                    painter = painterResource(Res.drawable.frosty_light_top),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp)
                        .align(Alignment.TopCenter),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(Res.drawable.frosty_light_decor),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(Res.drawable.frosty_light_bottom),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
private fun GreetingEditorTopbar(
    state: GreetingEditorState,
    onAction: (GreetingEditorAction) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(GreetingEditorColors.whiteBackground)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "Winter Greeting Editor",
            fontFamily = montserratFontFamily(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = GreetingEditorColors.text
        )

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackgroundsDropdown(
                expanded = state.isSelectBackgroundExpanded,
                onDismissRequest = {
                    onAction(GreetingEditorAction.OnDismissBackgroundsDropdown)
                },
                onSelectOption = {
                    onAction(GreetingEditorAction.OnSelectGreetingBackground(it))
                },
                onExpandDropdown = {
                    onAction(GreetingEditorAction.OnExpandBackgroundsDropdown)
                },
                selectedOption = state.selectedBackground,
                modifier = Modifier.weight(1f)
            )

            OutlinedIconButton(
                onClick = {

                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = GreetingEditorColors.strockDark
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GreetingEditorScreen(
        state = GreetingEditorState(),
        onAction = {}
    )
}
