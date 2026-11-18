# Audio-provider and background-execution feasibility findings

| Field | Value |
|---|---|
| Document ID | M3-DOC-003 |
| Revision | 0.1 |
| Document owner | @Entreco |
| Author | Copilot; human accountability remains with the owner |
| Required reviewers | Provider/platform feasibility, privacy, licensing/policy, and accessibility scopes; appointments and competence confirmation pending |
| Approver | @Entreco, after required reviews and findings resolution |
| Related issue | [#36](https://github.com/Entreco/elchrono/issues/36) |
| Review PR | [PR #49](https://github.com/Entreco/elchrono/pull/49); required reviews and human acceptance pending |

Lifecycle status is derived from the branch and PR context, not stored in this
table. See [document controls](../governance/document-control.md#lifecycle-and-approval).

## Purpose and limits

This record assesses official YouTube and Spotify integration mechanisms and
audio/background constraints for El Chrono's browser, Android, iOS, desktop,
Android TV, Apple TV, Wear OS, and watchOS targets. It records provider
authorization, policy, subscription, lifecycle, volume, privacy/data-flow, and
testability findings to inform M3 architecture and M6 decisions.

This is a documentation-only feasibility assessment. No provider, API, SDK,
authentication flow, platform support baseline, playback behavior, or volume
limit is selected. No playback integration, account, credential, token,
provider approval, legal conclusion, or runtime test is established here. The
current app remains a starter shell. El Chrono's non-medical purpose is visual
support for ordinary waiting; optional audio is not needed to understand the
timer and must not become a prerequisite for timer operation.

## Evidence method and environment

Official provider and platform documentation listed in
[Sources](#sources) was reviewed on 2026-11-18. Repository configuration and
the current evidence in [browser/desktop feasibility](platform-feasibility-findings.md)
and [TV/watch feasibility](tv-watch-feasibility-findings.md) were used to
describe testability. No provider SDK was installed; no credentials, accounts,
or tokens were created; no provider playback, network capture, or device
testing was performed. Platform status below describes documented APIs and
repository/test evidence, not product support.

The established browser test evidence is limited to Chrome on macOS; the
desktop JVM proof is macOS-only. Android and iOS are configured targets, but
there is no provider integration or provider test evidence. TV and watch
launchers are unconfigured and unverified, with no usable local test target
recorded in M3-DOC-002.

## Provider findings

### YouTube

The official IFrame Player API embeds and controls a YouTube audiovisual player
in a web page or supported WebView. The player must have a viewport of at least
200 × 200 pixels; normal player controls, video metadata, advertising, and
standard player behavior cannot be removed or obscured. WebView integrations
must identify the API client with a `Referer` or other documented mechanism.
The IFrame API is not an audio-only streaming interface.

YouTube's API Services policies explicitly prohibit separating or downloading
audio from video and prohibit background playback of the YouTube player when
the API client's window is closed or minimized. Therefore, the reviewed
mechanism does not establish a hidden audio-only soundtrack or OS-background
playback for the timer. It could only be considered for an appropriately
visible, user-controlled embedded-video experience that preserves the
provider's full player experience; that would be a different UX from a
background music control and remains unselected.

Embedding a public video does not itself establish a user-account or OAuth
requirement. Adding YouTube API Services or authorized user data requires
separate credential/authorization analysis. Provider policy requires an API
client privacy policy and user-facing disclosures; the privacy policy must
describe data access, collection, storage, sharing, and use. Embedded playback
also sends requests to YouTube and must preserve player context such as
referrer information. The exact browser/WebView requests, cookies, ads, and
device data have not been captured for this app. Do not collect YouTube login
credentials. Any authorized-data flow would also require deletion and
revocation handling under the current provider policy.

YouTube playback depends on provider content/network availability. The API
policy does not authorize an app to extract audio or provide offline downloads
outside the provider's Premium experience. The timer therefore cannot rely on
YouTube playback or internet availability. The API/embedding documentation is
not a separate music-content licence for El Chrono; preserve the provider
player, advertising, and applicable terms. Product-specific licensing and
distribution review remains open.

### Spotify

Spotify's Web Playback SDK creates a Spotify Connect player in a web
application. Its documentation lists support for current major desktop and
mobile browsers, while noting iOS browser transfer/autoplay limitations. The
SDK requires a Spotify Premium user and OAuth access token with the `streaming`
scope; the token is requested again when expired (the getting-started
tutorial describes one-hour access tokens). Playback may require an explicit
user gesture, particularly on iOS, and iframe embedding may need autoplay and
encrypted-media permissions. The SDK exposes its own local playback volume;
this is not system-volume control.

The official Android and iOS SDKs are App Remote integrations: they delegate
playback, authentication, and provider services to the Spotify app and allow
El Chrono to control or observe that app's playback after authorization. They
are not independent El Chrono-hosted music streams. The Android SDK states that
the Spotify app handles audio focus, lock-screen controls, and calls; the iOS
SDK states that playback/authentication/networking and offline caching are
offloaded to the main Spotify app. Its offline support excludes Web API calls,
and initial authentication requires internet. On iOS, the Spotify app must be
installed on a physical device for the documented integration. Provider-app
availability, account eligibility, connection, and playback errors remain
external dependencies.
The documented mobile remote-control scope is `app-remote-control`; the
specific subscription eligibility for a future App Remote use must be
confirmed with Spotify. The developer policy separately limits streaming of
music recordings to Premium subscribers.

Spotify's current developer policy is a material eligibility gate, not a
provider approval. It limits streaming to Premium subscribers, imposes
commercial-use restrictions and requires prior written approval for commercial
use of the Web Playback SDK. It requires a privacy policy, data minimization,
an accessible disconnect mechanism, and deletion/no further processing of
personal data on disconnect. A streaming app must also show the relevant
Spotify content metadata and cover art while playing. The policy prohibits apps
targeted to children, alarm functionality without written approval, and
products integrated with streams or content from another service. El Chrono's
intended-use record does not define an age range or select a distribution
model. These provisions may conflict with a future target audience or product
configuration and require owner and competent provider/legal review before
Spotify is selected. This record does not interpret the policy's legal scope or
conclude that El Chrono is eligible or ineligible.

Spotify Connect's ability to route to some TV or wearable devices is not
evidence of an El Chrono app SDK or a provider integration on those targets.
Remote playback remains subject to Spotify account, app/device, network, and
policy constraints.

## Provider/platform feasibility matrix

The provider terms and constraints above apply to every platform row. In
particular, YouTube's player, advertising, online-content, and no-audio-
extraction/background-playback restrictions are not platform exceptions.
Spotify's streaming subscription, authorization, privacy, commercial-use, and
audience restrictions also apply to any supported client; a row without an
identified SDK is not an exception or an assumed free/unauthenticated route.
Where platform-specific offline/background behavior is not established below,
it remains unverified rather than inferred from a related platform or
provider-managed app.

| El Chrono target | YouTube | Spotify | Repository testability and status |
|---|---|---|---|
| Browser (Wasm) | IFrame Player API embed; >=200 × 200 viewport, user-visible standard player, provider identity/referrer, internet content; no OAuth for plain public embeds. No hidden or minimized-window background playback. | Web Playback SDK documented for Chrome, Firefox, Safari, and Edge on mobile/desktop; OAuth `streaming` scope and Premium account; browser autoplay and iOS transfer restrictions. Streaming is online; background-tab behavior is not established for El Chrono. | Browser target configured; Chrome rendering observed in M3-DOC-001. No provider SDK, auth, playback, licensing review, or network behavior tested. |
| Android | IFrame player through OS WebView/browser with app identity/referrer; preserve standard player/ads; internet required; no audio-only/background-player path. Public embed does not imply OAuth. | Android App Remote requires user authorization and Spotify app; Spotify app handles playback/focus. Spotify streaming eligibility applies; no separate El Chrono stream. Provider-app offline playback does not prove offline remote control. | Configured mobile target; M3-DOC-002 recorded no connected device or usable emulator. No provider, authorization, terms, or playback test. |
| iOS | IFrame player in an appropriate WebView with origin/referrer handling; preserve standard player/ads; internet required; no audio-only/background-player path. Public embed does not imply OAuth. | iOS App Remote requires user authorization and Spotify app; Spotify delegates playback/auth/network to it. Initial auth requires internet; Web API calls are not covered by SDK offline support; Spotify documents a physical-device requirement. | Configured mobile target and simulator builds previously demonstrated; no physical provider-app, SDK, authorization, or playback evidence. |
| Desktop (JVM) | Web embed would need an embedded browser surface and app identity/referrer; the current JVM launcher has no established WebView integration. Online-only player restrictions apply; no OAuth is implied for public embeds. | Web Playback SDK can run in supported browsers, not directly in the current JVM app without an appropriate browser surface. OAuth and Premium are required for streaming; no native desktop El Chrono SDK established. | macOS-only JVM proof; Windows/Linux scope and embedded-browser runtime undecided. Browser testing does not verify the JVM launcher. No provider test. |
| Android TV | No TV-specific API established. A WebView embed would need a visible standard YouTube player, provider identity, and internet; no hidden/background audio. | No Android TV support statement for the Android App Remote found in reviewed docs; no TV SDK established. Auth/subscription route and offline/background behavior on TV are unverified. | No configured TV module, usable emulator, or test device is recorded in M3-DOC-002; no provider test. |
| Apple TV | No tvOS-specific integration established. Any proposed embed needs a supported web/player surface, internet, full player experience, and must not extract audio or use minimized-window background playback. | No tvOS SDK/in-app playback method established. Spotify Connect routing is not an app integration; any auth, subscription, offline, or background behavior for an El Chrono tvOS app is unverified. | No repository tvOS target or available Apple TV simulator/device recorded in M3-DOC-002; no provider test. |
| Wear OS | No supported YouTube watch API established. No offline/background-only audio path or authentication route established. | Spotify Connect may route to some wearable clients, but this is not a Wear OS SDK or standalone El Chrono playback. No watch-specific auth, subscription, offline, or background path established. | No configured Wear OS target or usable watch test device recorded in M3-DOC-002; no provider test. |
| watchOS | No supported YouTube watch API, auth path, or background-only audio method established. | No watchOS Spotify SDK or direct watch playback integration established. The iOS SDK/Spotify app remote cannot be treated as watch support. | No configured watchOS target or available watch simulator/device recorded in M3-DOC-002; no provider test. |

The matrix is a research result, not a support matrix. An empty or unverified
capability must not be converted into a support claim by cross-compilation,
provider-device routing, or the presence of a platform SDK.

## Audio focus, lifecycle, volume, and testability

- **Browser:** Chrome's autoplay policy allows audible autoplay only in
  specified user-engagement or permission cases; other browser policies and
  user settings can also block playback. WebKit recommends treating playback
  as requiring a user gesture and checking the returned playback promise.
  Spotify's mobile-browser transfer may be treated as autoplay and require a
  prior user activation. A failed or blocked playback attempt must be
  distinguishable from active playback; do not make timer startup conditional
  on it.
- **Android:** Android audio focus can be lost to another app. Android 12+
  enforces fade/mute behavior in documented focus cases; apps targeting
  Android 15+ must be the top app or run a foreground service to request audio
  focus. Media3 documents `MediaSessionService` and media-playback foreground
  service permissions for background playback. These mechanisms require
  platform-specific lifecycle, notification, and interruption review. The
  Spotify Android App Remote states that the Spotify app handles focus and
  system integration; controller connectivity and provider errors still need
  explicit handling.
- **Apple platforms:** AVFoundation documents audio-session configuration and
  the Audio/AirPlay/Picture in Picture background mode for iOS/tvOS media
  playback. Background audio requires explicit capability/configuration; it
  is not established by using a shared Kotlin target. The default iOS audio
  session can silence other background audio and be silenced when the device
  locks. On Apple platforms, `AVAudioSession.outputVolume` is read-only and
  only the user changes system volume; Apple directs apps to `MPVolumeView`
  for an in-app system-volume interface. Do not claim arbitrary app control of
  system output volume. These references do not establish watchOS audio
  lifecycle behavior.
- **Android system volume and device routes:** Android exposes `AudioManager`
  volume APIs, but availability and behavior vary by stream, output route, OS,
  and device. An app-level provider volume is distinct from system volume.
  No patient volume limit or system-volume behavior is selected here. The
  requirements that patients can adjust volume without accessing caregiver
  settings do not yet define whether the control is provider-local, app-local,
  or system-wide; no platform-equivalent access mechanism has been validated.
- **Desktop, TV, and watch:** The desktop OS support set is undefined; TV/watch
  targets are not configured. Background execution, audio routing, system
  volume and remote/device controls need a target-specific prototype and
  review. Platform compilation or generic Android/iOS API documentation is
  insufficient evidence.
- **Timer independence:** Provider start/authentication, network loss,
  autoplay denial, account/subscription restrictions, focus interruption,
  route changes, disconnection, and playback failure must not stop, pause, or
  misrepresent the timer. This is a requirement for later design and
  verification, not implemented behavior. Keep the existing v1 audio deferral
  and do not infer start, pause, completion, retry, or interruption behavior.
- **Testability:** A later provider prototype should test enabled/disabled
  provider states, absent network, denied authorization, expired/rejected
  credentials, autoplay denial, focus loss, route change, app background/lock,
  provider disconnect, and explicit playback-state/error reporting on each
  approved target. Spotify's Web Playback SDK documents initialization,
  authentication, account, connection, and readiness errors; integration tests
  must not infer success from a connect request alone. Use test accounts and
  authorized devices only. No such tests were run for this assessment.

## Privacy and data-flow findings

No patient account, patient identity, telemetry, provider account, token, or
provider media was used in this assessment. The following candidate flows
describe what a later integration may expose; exact data fields and controller/
processor roles require a competent privacy review and provider-specific
validation before implementation.

| Candidate flow | Data and external parties | Requirements and unresolved points |
|---|---|---|
| YouTube embedded player | The app loads YouTube-hosted player code/content and supplies a selected video or playlist identifier plus origin/referrer context. YouTube and any content/ad-serving parties may receive connection, device/browser, playback, cookie, and interaction data. No app-side account token is required for a plain public embed by the reviewed IFrame interface. | Disclose the provider and data flow in an accessible privacy policy before enabling the API client. Preserve player controls/context; do not collect login credentials or extract audio. Establish whether consent is required for provider requests, cookies, or storage in each launch market. If authorized data is added, define least-privilege scopes, revocation, deletion, and retention. |
| Spotify Web Playback SDK | User authorizes the app through OAuth; the app supplies access tokens to the browser SDK, which connects a named player to Spotify. Spotify receives authorization, device/player, playback and potentially track-state requests; the SDK may expose playback metadata and a device ID to the app. A backend is not required by the client SDK example, but no architecture choice is made. | Request only necessary scopes; use an appropriate public-client flow such as PKCE when no client secret can be safely stored. Define token handling, expiry/refresh, local storage versus memory, secure deletion, and disconnect/revocation before implementation. Provide the required privacy policy and an accessible disconnect/delete path. |
| Spotify Android/iOS App Remote | The user authorizes El Chrono to control the Spotify app. El Chrono communicates with the provider app and may receive playback state/track metadata; Spotify's app handles provider authentication, networking, caching and media playback as documented. | Explain the handoff and permission to the user; request only needed scopes; define disconnect/revocation and deletion of any locally held account/device/playback data. Do not claim the timer has independent offline streaming or direct volume/system control. |
| Local timer/settings | The timer itself needs no provider account or internet. If a future setting stores provider selection or connection state, that local data and its deletion on disconnect/reset must be specified. | Keep timer duration/progress data separate from provider authorization and playback state. No persistence schema or retention period is selected. Avoid patient identifiers and do not log access tokens, account identifiers, or track history. |

Consent, lawful basis, notices, controller/processor roles, age-related
requirements, token storage, retention, and deletion depend on the selected
provider flow, data, audience, platform, market, and distribution model. This
record does not settle those legal/privacy questions. M1-DOC-006 keeps privacy
and market applicability open; appoint a competent reviewer before any
provider integration or personal-data processing.

## Findings and follow-up

| Finding | Disposition |
|---|---|
| M3-DOC-003-F01: Spotify eligibility under its audience, cross-service, alarm, subscription, and commercial-use policies is unresolved against El Chrono's age and distribution scope. | Blocking provider selection for Spotify until the product owner and competent provider/legal reviewer assess the actual intended audience, integration, and distribution model. No conclusion or provider approval is claimed. |
| M3-DOC-003-F02: YouTube's reviewed API policies prohibit audio extraction and background playback of the player when the API-client window is closed or minimized. | Do not design a hidden YouTube audio-only/background player through the reviewed API. Any visible embedded-video use would require separate product, UX, privacy, and provider-policy review. |
| M3-DOC-003-F03: Native TV/watch support and testability are unverified for both providers. | Keep those combinations unsupported/unverified until a target-specific integration, lifecycle, input/accessibility, policy, and device test plan is approved and evidenced. |
| M3-DOC-003-F04: Provider data flows, consent basis, token handling, and disconnect behavior are not product decisions. | Preserve no accounts/telemetry by default. Complete the privacy review and user-facing disclosures before implementing provider authorization or collecting provider data. |

These findings do not choose a provider, platform, subscription requirement,
caregiver/patient control, volume limit, playback start/stop behavior, offline
provider feature, timer interruption behavior, or legal applicability
conclusion. Any future change to the intended audience, claims, data flow, or
provider choice must be assessed against the non-medical intended-use boundary
and the applicable market review before affected work proceeds.

## Sources

Provider and platform guidance was reviewed on 2026-11-18. Provider policies
and SDK support can change; verify the exact current terms and supported
platforms before an implementation or release decision.

| Source | Relevance |
|---|---|
| [YouTube IFrame Player API](https://developers.google.com/youtube/iframe_api_reference) | Web player integration, events, and controls. |
| [YouTube player parameters](https://developers.google.com/youtube/player_parameters) | Embed dimensions, controls, autoplay, and player behavior. |
| [YouTube API Services Policies](https://developers.google.com/youtube/terms/developer-policies) | User control, privacy policy, and authorized-data requirements. |
| [YouTube embedded-player minimum functionality](https://developers.google.com/youtube/terms/required-minimum-functionality) | Referrer/app identity, standard player requirements, no audio separation, and no background playback. |
| [YouTube policy compliance guide](https://developers.google.com/youtube/terms/developer-policies-guide) | Privacy, user experience, and prohibited background/audio-only behavior examples. |
| [Spotify Web Playback SDK](https://developer.spotify.com/documentation/web-playback-sdk) | Browser platforms and local player model. |
| [Spotify Web Playback SDK getting started](https://developer.spotify.com/documentation/web-playback-sdk/tutorials/getting-started) | Premium account, access token, error events, and mobile-browser transfer limitations. |
| [Spotify Web Playback SDK reference](https://developer.spotify.com/documentation/web-playback-sdk/reference) | Token callback, player volume, connection state, and error events. |
| [Spotify Android SDK](https://developer.spotify.com/documentation/android) | Android App Remote, authorization, playback delegation, focus/lock-screen integration, and offline/online statements. |
| [Spotify iOS SDK](https://developer.spotify.com/documentation/ios) | iOS App Remote, delegation to Spotify app, offline limits, and physical-device requirement. |
| [Spotify authorization](https://developer.spotify.com/documentation/web-api/concepts/authorization) and [scopes](https://developer.spotify.com/documentation/web-api/concepts/scopes) | OAuth flows, PKCE for public clients, and playback permission scopes. |
| [Spotify Developer Policy](https://developer.spotify.com/policy) | Premium-only streaming, required metadata/cover art, commercial-use, privacy/disconnection, audience, alarm, and cross-service restrictions. |
| [Chrome autoplay policy](https://developer.chrome.com/blog/autoplay/) | Audible autoplay prerequisites and playback failure handling. |
| [WebKit autoplay policy for macOS](https://webkit.org/blog/7734/auto-play-policy-changes-for-macos/) | Safari autoplay controls and gesture/error handling. |
| [Android audio focus](https://developer.android.com/media/optimize/audio-focus) | Focus loss, Android-version behavior, and foreground requirements. |
| [Android background playback with Media3](https://developer.android.com/media/media3/session/background-playback) | Media session service and foreground-service requirements. |
| [Apple media playback configuration](https://developer.apple.com/documentation/avfoundation/configuring-your-app-for-media-playback) | iOS/tvOS audio session and background audio capability. |
| [Apple AVAudioSession outputVolume](https://developer.apple.com/documentation/avfaudio/avaudiosession/outputvolume) | Read-only system volume and user-operated MPVolumeView control. |
| [Android TV, Wear OS, and watchOS constraints](tv-watch-feasibility-findings.md) | Existing M3-DOC-002 official platform evidence and local target/test limitations. |
| [Browser and desktop evidence](platform-feasibility-findings.md) | Existing M3-DOC-001 launch/test evidence and scope limitations. |
| [Repository privacy and music requirements](../../AGENTS.md#privacy-and-music-integrations) | Product boundary for accounts, data flows, official integrations, and audio failures. |

## Revision history

| Revision | Change |
|---|---|
| 0.1 | Record issue #36 provider/platform feasibility, background/audio constraints, data-flow implications, sources, and unresolved review gates without implementing playback or selecting behavior. |
