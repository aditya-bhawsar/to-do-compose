package com.aditya.to_do.data.models

import androidx.compose.ui.graphics.Color
import com.aditya.to_do.ui.theme.HighPriorityColor
import com.aditya.to_do.ui.theme.LowPriorityColor
import com.aditya.to_do.ui.theme.MediumPriorityColor
import com.aditya.to_do.ui.theme.NonePriorityColor

enum class Priority(val color : Color) {
    HIGH (HighPriorityColor),
    MEDIUM (MediumPriorityColor),
    LOW (LowPriorityColor),
    NONE (NonePriorityColor),
}