# Television targets

`androidtv` is a candidate Android TV launcher using the shared app shell. Run it
on an explicitly selected Android TV device or emulator with
`./kotlin run -m androidtv --device-id <adb-serial>`. The launcher and banner
do not establish TV support: focus, D-pad interaction, accessibility, lifecycle,
and display usability require verification and review.

Apple TV remains unconfigured. Kotlin/Native exposes tvOS targets, but the
shared Compose UI and Kotlin Toolchain application path need a compatible,
reviewed tvOS integration. Mobile builds do not establish television support.
