# El Chrono visualization demo

This local, browser-based prototype has one countdown with a **Shape**
selector: **Disk**, **Battery**, **Hand fan**, or **Ring**. Circle is merged
into Disk rather than offered as a separate digital visualization.
Switch shapes while it runs without resetting the countdown or settings.
Set the duration from one minute through 24 hours in the Personalization
panel before starting; its default is two hours. Hide or show the panel with
the eye button beside the timer state.
The numeric ring thickness slider ranges from a 1 px hairline to a solid fill.
Its progress stroke has flat ends so rounded caps do not obscure the remaining
arc at large thicknesses.
Font, foreground color, background color, text color, and **Show text** are
shared across all shapes for the current page session only. Ring thickness
appears only for Ring and retains its value when switching away and back.
Font and text color controls are hidden when text is off, without clearing
their values. Duration is shared too and locks after starting. Each
visualization uses only those three selected colors: the disk center and
depleted area use the background color, its remaining sector uses the
foreground color, and all visualization text uses the text color. Disk and
digital-shape outlines and shadows do not add colors. The default
remaining disk has no clock-face numerals, consistent with the product's
no-clock design boundary. The exploratory **Show text** option also reveals
0–55 minute markers in 36 px text outside the disk, with space reserved around
them; that option is prototype-only and
does not change the product design boundary. The **Background color** preview
defaults to white and includes white, black, sky blue, and soft yellow presets
plus a custom color picker. It colors the disk's center and depleted area. The
foreground has four presets and a custom picker; text color has a custom picker.
The battery and fan display remaining duration inside the shape when text is
on; the disk displays it below and the ring in its center. The
battery drains linearly, the disk empties radially,
and the hand fan closes its opening angle from 180 degrees to zero as time runs
out, keeping its radius and rib lengths fixed rather than scaling horizontally.
Turning text off hides the
time and captions within the visualization while leaving the surrounding demo
controls visible.

Open the page source at [index.html](index.html); the design rationale and
remaining-time semantics are in
[M2-DOC-004](../accessible-screen-flows-and-theme-prototypes.md).

From the repository root, start a local web server:

```sh
python3 -m http.server 8000 --directory docs/design/demo
```

Open <http://localhost:8000>. Select a shape and any preview styling,
then select **Start demo**. Reloading starts a fresh demo. No install or
internet connection is required to run the prototype; it uses system fonts
only.

This is a client-feedback prototype, not the application timer. It has no
caregiver protection, pause control, persistence, audio, or interruption
recovery. When the countdown reaches zero, it resets immediately to the
configured duration without a completed screen. The timer uses the browser's
monotonic clock while the page is open; this is not evidence of timer
reliability across sleep, backgrounding, or restart. Settings are temporary
and do not change product requirements or select final visualization,
font, or color defaults.
