package nl.entreco.elchrono.core.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DesignScreenHeader(
    brandLabel: String,
    title: String,
    backLabel: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    stackBelow: Dp = 520.dp,
) {
    BoxWithConstraints(modifier.fillMaxWidth()) {
        if (maxWidth < stackBelow) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceM),
            ) {
                BackButton(backLabel, onBack)
                Column(verticalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceXXS)) {
                    Text(brandLabel, style = MaterialTheme.typography.labelSmall)
                    Text(title, style = MaterialTheme.typography.titleLarge)
                    supportingText?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceL),
            ) {
                BackButton(backLabel, onBack)
                Column(verticalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceXXS)) {
                    Text(brandLabel, style = MaterialTheme.typography.labelSmall)
                    Text(title, style = MaterialTheme.typography.titleLarge)
                }
                supportingText?.let {
                    Spacer(Modifier.weight(1f))
                    Text(
                        it,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}

@Composable
private fun BackButton(
    backLabel: String,
    onBack: () -> Unit,
) {
    IconButton(
        onClick = onBack,
        modifier = Modifier
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .elChronoFocusOutline(CircleShape)
            .semantics { contentDescription = backLabel },
    ) {
        Text("‹", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun DesignPreviewCard(
    title: String,
    modifier: Modifier = Modifier,
    details: String? = null,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(LocalDimens.current.spaceXL),
            verticalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceL),
        ) {
            Text(title, style = MaterialTheme.typography.labelSmall)
            Box(Modifier.fillMaxWidth()) {
                content()
            }
            details?.let {
                Text(
                    it,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

@Composable
fun DesignStatusMessage(
    message: String,
    modifier: Modifier = Modifier,
    leadingContent: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceM),
    ) {
        leadingContent?.invoke()
        Text(message, style = MaterialTheme.typography.bodySmall)
    }
}

data class NavigationHint(
    val keyLabel: String,
    val actionLabel: String,
)

@Composable
fun NavigationHintBar(
    hints: List<NavigationHint>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceXL),
        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceXS),
    ) {
        hints.forEach { hint ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceXS),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(hint.keyLabel, style = MaterialTheme.typography.labelMedium)
                Text(hint.actionLabel, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
