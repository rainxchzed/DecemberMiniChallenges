package zed.rainxch.decemberminichallenges_.holiday_cashback_activation.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        val formatted = buildString {
            trimmed.forEachIndexed { index, char ->
                append(char)
                if ((index + 1) % 4 == 0 && index != trimmed.length - 1) {
                    append(" ")
                }
            }
        }
        
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset >= trimmed.length) {
                    return formatted.length
                }
                val spaces = offset / 4
                return offset + spaces
            }
            
            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return 0
                val spaces = (offset - 1) / 5
                val position = offset - spaces
                return position.coerceIn(0, trimmed.length)
            }
        }
        
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}