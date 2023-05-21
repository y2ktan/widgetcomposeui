package com.ressphere.widgetwithcompose

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.background
import androidx.glance.appwidget.provideContent
import androidx.glance.color.ColorProvider
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState

object CounterWidget: GlanceAppWidget() {

    val countKey = intPreferencesKey("count")

    class SimpleCounterWidgetReceiver: GlanceAppWidgetReceiver() {
        override val glanceAppWidget: GlanceAppWidget
            get() = CounterWidget
    }

    override suspend fun provideGlance(
        context: Context, id: GlanceId) {

        provideContent {
            val count = currentState(key = countKey) ?: 0
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(Color.DarkGray, Color.DarkGray),
                verticalAlignment = Alignment.Vertical.CenterVertically,
                horizontalAlignment = Alignment.Horizontal.CenterHorizontally
            ) {
                Text(
                    text = count.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = ColorProvider(Color.White, Color.White),
                        fontSize = 26.sp
                    )
                )
                Button(
                    text = "Inc",
                    onClick = actionRunCallback(IncrementActionCallback::class.java)
                )
            }
        }
    }
}