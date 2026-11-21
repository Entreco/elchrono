package nl.entreco.elchrono.features.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import nl.entreco.elchrono.core.design.LocalDimens
import nl.entreco.elchrono.core.localization.resources.Res as Strings
import nl.entreco.elchrono.core.localization.resources.about_licence_gap
import nl.entreco.elchrono.core.localization.resources.about_logo_description
import nl.entreco.elchrono.core.localization.resources.about_privacy_gap
import nl.entreco.elchrono.core.localization.resources.about_support_gap
import nl.entreco.elchrono.core.localization.resources.about_tagline
import nl.entreco.elchrono.core.localization.resources.about_version_build_gap
import nl.entreco.elchrono.core.localization.resources.brand_wordmark
import nl.entreco.elchrono.core.localization.resources.intended_use_heading
import nl.entreco.elchrono.core.localization.resources.intended_use_summary
import nl.entreco.elchrono.core.localization.resources.licence_information_heading
import nl.entreco.elchrono.core.localization.resources.limitations_heading
import nl.entreco.elchrono.core.localization.resources.limitations_summary
import nl.entreco.elchrono.core.localization.resources.privacy_policy_heading
import nl.entreco.elchrono.core.localization.resources.support_information_heading
import nl.entreco.elchrono.core.localization.resources.version_build_heading
import nl.entreco.elchrono.features.about.resources.Res as AboutResources
import nl.entreco.elchrono.features.about.resources.entreco_logo
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutFeature(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceL),
    ) {
        Image(
            painter = painterResource(AboutResources.drawable.entreco_logo),
            contentDescription = stringResource(Strings.string.about_logo_description),
            modifier = Modifier.fillMaxWidth().aspectRatio(404f / 326f),
            contentScale = ContentScale.Fit,
        )
        Text(stringResource(Strings.string.brand_wordmark), style = MaterialTheme.typography.headlineLarge)
        Text(
            text = stringResource(Strings.string.about_tagline),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )

        AboutSection(
            title = Strings.string.version_build_heading,
            body = Strings.string.about_version_build_gap,
        )
        AboutSection(
            title = Strings.string.support_information_heading,
            body = Strings.string.about_support_gap,
        )
        AboutSection(
            title = Strings.string.privacy_policy_heading,
            body = Strings.string.about_privacy_gap,
        )
        AboutSection(
            title = Strings.string.licence_information_heading,
            body = Strings.string.about_licence_gap,
        )
        AboutSection(
            title = Strings.string.intended_use_heading,
            body = Strings.string.intended_use_summary,
        )
        AboutSection(
            title = Strings.string.limitations_heading,
            body = Strings.string.limitations_summary,
        )
    }
}

@Composable
private fun AboutSection(
    title: StringResource,
    body: StringResource,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceContainerLow,
    ) {
        Column(
            modifier = Modifier.padding(LocalDimens.current.spaceL),
            verticalArrangement = Arrangement.spacedBy(LocalDimens.current.spaceS),
        ) {
            Text(stringResource(title), style = MaterialTheme.typography.titleMedium)
            Text(stringResource(body), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
