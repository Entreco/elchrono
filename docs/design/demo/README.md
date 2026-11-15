# El Chrono visualization demo

This local, browser-based prototype has one countdown with a **Shape**
selector: **Clock** (formerly Disk), **Battery**, **Hand fan**, or **Ring**.
Circle is merged into Clock rather than offered as a separate digital visualization.
Switch shapes while it runs without resetting the countdown or settings.
Set the duration from one minute through 24 hours in the Personalization
panel before starting; its default is two hours. Hide or show the panel with
the eye button beside the timer state.
The numeric ring thickness slider ranges from a 1 px hairline to a solid fill.
Its progress stroke has flat ends so rounded caps do not obscure the remaining
arc at large thicknesses.
Font, foreground color, background color, text color, **Show remaining**, and
**Show details** are
shared across all shapes for the current page session only. Ring thickness
appears only for Ring and retains its value when switching away and back.
Font and text color controls are hidden when neither remaining time nor
circular progress numerals are visible, without clearing
their values. Duration is shared too and locks after starting. Each
visualization uses only those three selected colors: the disk center and
depleted area use the background color, its remaining sector uses the
foreground color, and all visualization text uses the text color. Clock and
digital-shape outlines and shadows do not add colors.
The **Show details** slider steps through Off, then 2–12 equal sections
(the input positions 0–11 map to Off, 2, 3, ..., 12). It defaults to six sections.
Off hides all extra separators and labels; it never hides the underlying fill.
Clock always keeps a continuous fill; details affect only its outer labels.
Ring has radial section boundaries only, with no intermediate duration labels.
Clock has 36 px duration labels outside the shape.
For configured durations below one hour, labels show whole seconds with an
`s` suffix: five minutes with twelve sections shows 0s, 275s, 250s, ..., 25s
clockwise. For durations of one hour or more, labels show minutes, or
minutes:seconds when a boundary is not a whole minute. The unit is based on
configured duration, not remaining time, so it does not change during countdown.
One hour with two sections shows 0 and 30; with ten sections it shows
0, 54, 48, ..., 6 clockwise on Clock.
Clock labels count counterclockwise from zero at the top, matching its
remaining-time sweep.
The circular-number option is exploratory, prototype-only and
does not change the product design boundary. The **Background color** preview
defaults to white and includes white, black, sky blue, and soft yellow presets
plus a custom color picker. It colors the disk's center and depleted area. The
foreground has four presets and a custom picker; text color has a custom picker.
**Show remaining** independently controls the remaining-time display.
Shapes have no explanatory captions.
**Show details** uses the same section count for battery divisions
and fixed fan separators, and remains independent of Show remaining.
Both preferences remain shared when switching shapes.
The battery and fan display remaining duration inside the shape when remaining is
on; the disk displays it below and the ring in its center. The
battery drains linearly with its terminal outside the rectangular body, the disk empties radially,
and the hand fan empties from the right edge toward the fixed left edge over
180 degrees, like a disk sweep. Its radius and separator positions stay fixed:
separators are clipped to the remaining sector, not folded or compressed.
Turning remaining off and details to Off leaves only the shape's fill and
surrounding demo controls.

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
