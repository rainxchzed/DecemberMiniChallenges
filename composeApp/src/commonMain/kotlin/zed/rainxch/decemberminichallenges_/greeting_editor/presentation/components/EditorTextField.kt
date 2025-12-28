package zed.rainxch.decemberminichallenges_.greeting_editor.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import decemberminichallenges_.composeapp.generated.resources.Montserrat_Medium
import decemberminichallenges_.composeapp.generated.resources.PTSerif_Bold
import decemberminichallenges_.composeapp.generated.resources.PTSerif_Regular
import decemberminichallenges_.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import zed.rainxch.decemberminichallenges_.core.presentation.theme.GreetingEditorColors
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.GreetingEditorAction
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.GreetingEditorState
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.GreetingBackground
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextColor
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextFontFamily
import zed.rainxch.decemberminichallenges_.greeting_editor.presentation.model.TextSize

@Composable
fun EditorTextField(
    state: GreetingEditorState,
    onAction : (GreetingEditorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var formattingSpans by remember { mutableStateOf<List<FormattingSpan>>(emptyList()) }
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    // Preload font families
    val montserratFamily = FontFamily(Font(Res.font.Montserrat_Medium))
    val ptSerifFamily = FontFamily(Font(Res.font.PTSerif_Regular))

    // Build annotated string from spans
    val annotatedText = remember(textFieldValue.text, formattingSpans) {
        buildAnnotatedString {
            append(textFieldValue.text)

            formattingSpans.forEach { span ->
                val validStart = maxOf(0, span.start)
                val validEnd = minOf(span.end, textFieldValue.text.length)

                if (validStart < validEnd) {
                    val fontFamily = when (span.fontFamily) {
                        TextFontFamily.Montserrat -> montserratFamily
                        TextFontFamily.PTSerif -> ptSerifFamily
                    }

                    addStyle(
                        style = SpanStyle(
                            fontWeight = if (span.isBold) FontWeight.Bold else FontWeight.Normal,
                            fontStyle = if (span.isItalic) FontStyle.Italic else FontStyle.Normal,
                            textDecoration = if (span.isUnderline) TextDecoration.Underline else TextDecoration.None,
                            color = span.color.color,
                            fontSize = span.fontSize.size,
                            fontFamily = fontFamily
                        ),
                        start = validStart,
                        end = validEnd
                    )
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(16.dp)
            .then(
                if (state.selectedBackground != GreetingBackground.GoldenEve) {
                    Modifier.border(
                        width = 1.dp,
                        color = GreetingEditorColors.whiteBackground,
                        shape = RoundedCornerShape(16.dp)
                    )
                } else Modifier
            )
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
    ) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                val oldValue = textFieldValue
                textFieldValue = newValue

                formattingSpans = handleTextChange(
                    oldText = oldValue.text,
                    newText = newValue.text,
                    oldSelection = oldValue.selection,
                    newSelection = newValue.selection,
                    spans = formattingSpans,
                    currentFormatting = getCurrentFormatting(state)
                )
            },
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(
                fontSize = state.currentFontSize.size,
                fontFamily = FontFamily(Font(state.currentFontFamily.fontResource)),
                color = state.currentColor.color,
                textAlign = TextAlign.Center
            ),
            cursorBrush = SolidColor(GreetingEditorColors.whiteBackground)
        ) { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (textFieldValue.text.isEmpty() && !isFocused) {
                    Text(
                        text = "Type your winter greeting here...",
                        style = TextStyle(
                            fontSize = 34.sp,
                            fontFamily = FontFamily(Font(Res.font.PTSerif_Bold)),
                            color = GreetingEditorColors.whiteBackground.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                if (textFieldValue.text.isNotEmpty()) {
                    Text(
                        text = annotatedText,
                        style = TextStyle(textAlign = TextAlign.Center),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Keep text field for cursor/selection but make text transparent
                Box(modifier = Modifier.alpha(0.01f)) {
                    innerTextField()
                }
            }
        }
    }

    // Update toolbar based on cursor position
    LaunchedEffect(textFieldValue.selection, formattingSpans) {
        if (textFieldValue.selection.collapsed && textFieldValue.text.isNotEmpty()) {
            val cursorPos = textFieldValue.selection.start

            // Find the formatting at cursor position
            val formattingAtCursor = formattingSpans.firstOrNull { span ->
                cursorPos > span.start && cursorPos <= span.end
            } ?: formattingSpans.lastOrNull { span ->
                cursorPos == span.start
            }

            if (formattingAtCursor != null) {
                // Update state to reflect formatting at cursor
                onAction(GreetingEditorAction.UpdateToolbarFromCursor(
                    isBold = formattingAtCursor.isBold,
                    isItalic = formattingAtCursor.isItalic,
                    isUnderline = formattingAtCursor.isUnderline,
                    color = formattingAtCursor.color,
                    fontFamily = formattingAtCursor.fontFamily,
                    fontSize = formattingAtCursor.fontSize
                ))
            }
        }
    }

    // Apply formatting to selection when toolbar buttons are clicked
    LaunchedEffect(
        state.isCurrentlyBold, state.isCurrentlyItalic,
        state.isCurrentlyUnderline, state.currentColor,
        state.currentFontFamily, state.currentFontSize
    ) {
        if (!textFieldValue.selection.collapsed) {
            formattingSpans = applyFormattingToSelection(
                selection = textFieldValue.selection,
                spans = formattingSpans,
                formatting = getCurrentFormatting(state)
            )
        }
    }
}

data class FormattingSpan(
    val start: Int,
    val end: Int,
    val isBold: Boolean = false,
    val isItalic: Boolean = false,
    val isUnderline: Boolean = false,
    val color: TextColor,
    val fontFamily: TextFontFamily,
    val fontSize: TextSize
)

data class FormattingStyle(
    val isBold: Boolean,
    val isItalic: Boolean,
    val isUnderline: Boolean,
    val color: TextColor,
    val fontFamily: TextFontFamily,
    val fontSize: TextSize
)

fun getCurrentFormatting(state: GreetingEditorState): FormattingStyle {
    return FormattingStyle(
        isBold = state.isCurrentlyBold,
        isItalic = state.isCurrentlyItalic,
        isUnderline = state.isCurrentlyUnderline,
        color = state.currentColor,
        fontFamily = state.currentFontFamily,
        fontSize = state.currentFontSize
    )
}

fun handleTextChange(
    oldText: String,
    newText: String,
    oldSelection: TextRange,
    newSelection: TextRange,
    spans: List<FormattingSpan>,
    currentFormatting: FormattingStyle
): List<FormattingSpan> {
    val oldLength = oldText.length
    val newLength = newText.length
    val diff = newLength - oldLength

    return when {
        diff > 0 -> handleTextInsertion(
            insertPosition = oldSelection.start,
            insertedLength = diff,
            spans = spans,
            currentFormatting = currentFormatting,
        )

        diff < 0 -> handleTextDeletion(
            deleteStart = newSelection.start,
            deletedLength = -diff,
            spans = spans
        )

        else -> spans
    }
}

fun handleTextInsertion(
    insertPosition: Int,
    insertedLength: Int,
    spans: List<FormattingSpan>,
    currentFormatting: FormattingStyle,
): List<FormattingSpan> {
    val newSpans = mutableListOf<FormattingSpan>()

    spans.forEach { span ->
        when {
            span.end <= insertPosition -> {
                newSpans.add(span)
            }

            span.start < insertPosition && span.end > insertPosition -> {
                newSpans.add(span.copy(end = insertPosition))
                newSpans.add(
                    span.copy(
                        start = insertPosition + insertedLength,
                        end = span.end + insertedLength
                    )
                )
            }

            span.start >= insertPosition -> {
                newSpans.add(
                    span.copy(
                        start = span.start + insertedLength,
                        end = span.end + insertedLength
                    )
                )
            }
        }
    }

    val newSpan = FormattingSpan(
        start = insertPosition,
        end = insertPosition + insertedLength,
        isBold = currentFormatting.isBold,
        isItalic = currentFormatting.isItalic,
        isUnderline = currentFormatting.isUnderline,
        color = currentFormatting.color,
        fontFamily = currentFormatting.fontFamily,
        fontSize = currentFormatting.fontSize
    )
    newSpans.add(newSpan)

    return mergeAdjacentSpans(newSpans.sortedBy { it.start })
}

fun handleTextDeletion(
    deleteStart: Int,
    deletedLength: Int,
    spans: List<FormattingSpan>
): List<FormattingSpan> {
    val deleteEnd = deleteStart + deletedLength
    val newSpans = mutableListOf<FormattingSpan>()

    spans.forEach { span ->
        when {
            span.end <= deleteStart -> {
                newSpans.add(span)
            }

            span.start >= deleteEnd -> {
                newSpans.add(
                    span.copy(
                        start = span.start - deletedLength,
                        end = span.end - deletedLength
                    )
                )
            }

            else -> {
                val newStart = minOf(span.start, deleteStart)
                val newEnd = maxOf(deleteStart, span.end - deletedLength)

                if (newStart < newEnd) {
                    newSpans.add(
                        span.copy(
                            start = newStart,
                            end = newEnd
                        )
                    )
                }
            }
        }
    }

    return mergeAdjacentSpans(newSpans.sortedBy { it.start })
}

fun applyFormattingToSelection(
    selection: TextRange,
    spans: List<FormattingSpan>,
    formatting: FormattingStyle
): List<FormattingSpan> {
    val selectionStart = selection.min
    val selectionEnd = selection.max
    val newSpans = mutableListOf<FormattingSpan>()

    spans.forEach { span ->
        when {
            span.end <= selectionStart || span.start >= selectionEnd -> {
                newSpans.add(span)
            }

            span.start >= selectionStart && span.end <= selectionEnd -> {
                newSpans.add(
                    span.copy(
                        isBold = formatting.isBold,
                        isItalic = formatting.isItalic,
                        isUnderline = formatting.isUnderline,
                        color = formatting.color,
                        fontFamily = formatting.fontFamily,
                        fontSize = formatting.fontSize
                    )
                )
            }

            else -> {
                if (span.start < selectionStart) {
                    newSpans.add(span.copy(end = selectionStart))
                }

                val overlapStart = maxOf(span.start, selectionStart)
                val overlapEnd = minOf(span.end, selectionEnd)
                newSpans.add(
                    span.copy(
                        start = overlapStart,
                        end = overlapEnd,
                        isBold = formatting.isBold,
                        isItalic = formatting.isItalic,
                        isUnderline = formatting.isUnderline,
                        color = formatting.color,
                        fontFamily = formatting.fontFamily,
                        fontSize = formatting.fontSize
                    )
                )

                if (span.end > selectionEnd) {
                    newSpans.add(span.copy(start = selectionEnd))
                }
            }
        }
    }

    return mergeAdjacentSpans(newSpans.sortedBy { it.start })
}

fun mergeAdjacentSpans(spans: List<FormattingSpan>): List<FormattingSpan> {
    if (spans.isEmpty()) return spans

    val merged = mutableListOf<FormattingSpan>()
    var current = spans[0]

    for (i in 1 until spans.size) {
        val next = spans[i]

        if (current.end == next.start && spansHaveSameFormatting(current, next)) {
            current = current.copy(end = next.end)
        } else {
            merged.add(current)
            current = next
        }
    }

    merged.add(current)

    return merged
}

fun spansHaveSameFormatting(span1: FormattingSpan, span2: FormattingSpan): Boolean {
    return span1.isBold == span2.isBold &&
            span1.isItalic == span2.isItalic &&
            span1.isUnderline == span2.isUnderline &&
            span1.color == span2.color &&
            span1.fontFamily == span2.fontFamily &&
            span1.fontSize == span2.fontSize
}