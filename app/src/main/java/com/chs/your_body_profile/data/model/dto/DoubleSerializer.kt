package com.chs.your_body_profile.data.model.dto

import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

object DoubleSerializer : JsonTransformingSerializer<Double>(Double.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return if (element is JsonPrimitive) {
            if (element.content.isNotEmpty()) {
                element
            } else JsonPrimitive(0.0)
        } else JsonPrimitive(0.0)
    }
}