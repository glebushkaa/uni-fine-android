package com.uni.fine.ui.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uni.fine.ui.theme.UniFineTheme

@Composable
fun UniButton(
    modifier: Modifier = Modifier,
    text: String,
    loading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        onClick = {
            if (!loading) onClick()
        },
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (loading) UniFineTheme.colors.amber.copy(alpha = 0.7f) else UniFineTheme.colors.amber,
            disabledContainerColor = UniFineTheme.colors.gray,
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AnimatedVisibility(
                modifier = Modifier.padding(end = UniFineTheme.padding.large),
                visible = loading,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally(),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    color = UniFineTheme.colors.white,
                    strokeWidth = 2.dp
                )
            }
            Text(
                text = text,
                color = UniFineTheme.colors.white,
                style = UniFineTheme.typography.body
            )
        }
    }
}