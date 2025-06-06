package com.chs.your_body_profile.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.text.ifEmpty
import kotlin.text.toLong

class DecimalMarkedNumberVisualTransformation() : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.text
            .filter { "^[0-9]".toRegex().matches(it.toString()) }
            .toLong()
            .toCommaFormat()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset == 0) 0 else formattedText.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                return text.length
            }
        }

        return TransformedText(
            text = AnnotatedString(formattedText.toString()),
            offsetMapping = offsetMapping
        )
    }
}