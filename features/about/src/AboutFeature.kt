package nl.entreco.elchrono.features.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.brand_wordmark
import nl.entreco.elchrono.core.localization.resources.company_logo_gap
import nl.entreco.elchrono.core.localization.resources.intended_use_heading
import nl.entreco.elchrono.core.localization.resources.intended_use_summary
import nl.entreco.elchrono.core.localization.resources.licence_information_gap
import nl.entreco.elchrono.core.localization.resources.limitations_heading
import nl.entreco.elchrono.core.localization.resources.limitations_summary
import nl.entreco.elchrono.core.localization.resources.privacy_policy_gap
import nl.entreco.elchrono.core.localization.resources.support_information_gap
import nl.entreco.elchrono.core.localization.resources.version_build_gap
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutFeature(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceL),
    ) {
        Text(stringResource(Strings.string.brand_wordmark), style = MaterialTheme.typography.headlineLarge)
        AboutItem(Strings.string.company_logo_gap)
        AboutItem(Strings.string.version_build_gap)
        AboutItem(Strings.string.support_information_gap)
        AboutItem(Strings.string.privacy_policy_gap)
        AboutItem(Strings.string.licence_information_gap)
        Spacer(Modifier.height(LocalDimens.current.spaceM))
        Text(stringResource(Strings.string.intended_use_heading), style = MaterialTheme.typography.titleLarge)
        Text(stringResource(Strings.string.intended_use_summary), textAlign = TextAlign.Center)
        Text(stringResource(Strings.string.limitations_heading), style = MaterialTheme.typography.titleLarge)
        Text(stringResource(Strings.string.limitations_summary), textAlign = TextAlign.Center)
    }
}

@Composable
private fun AboutItem(resource: StringResource) {
    Text(
        text = stringResource(resource),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}
