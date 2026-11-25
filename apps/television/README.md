# Television targets

`androidtv` is a candidate Android TV launcher using the shared app shell. Run it
on an explicitly selected Android TV device or emulator with
`./kotlin run -m androidtv --device-id <adb-serial>`. The launcher and banner
do not establish TV support: focus, D-pad interaction, accessibility, lifecycle,
and display usability require verification and review.

The Android TV candidate uses the same Material controls as the other targets,
with shared focus indicators and remote focus navigation. Installation, launch,
and physical-remote verification on an Android TV remain pending.

On 2026-11-25, `./kotlin build -m androidtv` completed and produced a debug
APK. The `Television_4K` AVD profile is unusable because its Android TV system
image is missing, and `adb devices -l` found no connected device. The APK was
not installed or launched; remote navigation and TV-renderer behavior remain
unverified. See the [issue #82 verification record](../../docs/testing/test-protocol.md#issue-82-tv-packaging-and-support-evidence).

Apple TV remains without an app target. Xcode 27.2 has the tvOS 27.2 SDK,
simulator runtime, and Apple TV 4K simulator devices, but a bounded Kotlin
Toolchain build probe found that the current Compose animation, foundation,
Material 3, UI, and resources dependencies do not publish tvOS variants. The
shared Compose app therefore cannot currently be configured or built for tvOS
without a separately reviewed architecture/integration decision. No Apple TV
package, remote-focus behavior, or runtime launch has been verified. Mobile
builds and simulator availability do not establish television support.
