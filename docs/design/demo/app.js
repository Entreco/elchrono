(() => {
  let durationMs = 2 * 60 * 60 * 1000;
  let durationSeconds = durationMs / 1000;
  const visualizations = [...document.querySelectorAll("[data-view]")];
  const shapeSelect = document.getElementById("shape-select");
  const stateLabel = document.getElementById("timer-state");
  const startButton = document.getElementById("start-button");
  const timerHeading = document.getElementById("timer-heading");
  const durationHoursInput = document.getElementById("duration-hours");
  const durationMinutesInput = document.getElementById("duration-minutes");
  const durationControl = document.getElementById("duration-control");
  const durationError = document.getElementById("duration-error");
  const personalizationPanel = document.getElementById("personalization-panel");
  const personalizationToggle = document.getElementById("personalization-toggle");
  const fontSelect = document.getElementById("font-select");
  const fontControl = document.getElementById("font-control");
  const textColorControl = document.getElementById("text-color-control");
  const colorPicker = document.getElementById("color-picker");
  const textColorPicker = document.getElementById("text-color-picker");
  const backgroundColorPicker = document.getElementById("background-color-picker");
  const backgroundSwatches = [...document.querySelectorAll("[data-background-color]")];
  const showRemainingInput = document.getElementById("show-remaining");
  const showProgressInput = document.getElementById("show-progress");
  const swatches = [...document.querySelectorAll("[data-color]")];
  const ringThicknessControl = document.getElementById("ring-thickness-control");
  const ringThicknessInput = document.getElementById("ring-thickness");
  const ringThicknessValue = document.getElementById("ring-thickness-value");
  const ringProgress = document.getElementById("ring-progress");
  const diskProgress = document.getElementById("disk-progress");
  const digitalProgress = document.getElementById("digital-progress");
  const digitalFill = document.getElementById("digital-fill");
  const fanPanel = document.getElementById("fan-panel");
  const fanClip = document.getElementById("fan-clip");
  const ringWrap = document.querySelector(".ring-wrap");
  const ring = document.querySelector(".progress-ring");
  const ringCircles = [...ring.querySelectorAll("circle")];
  const root = document.documentElement;
  const preferences = {
    shape: "disk",
    foregroundColor: "#267b68",
    backgroundColor: "#ffffff",
    textColor: "#23322f",
    font: "sans",
    ringThickness: 15,
    showRemaining: true,
    showProgress: true,
  };
  const minuteLabels = [];
  let deadline = null;
  let timerInterval = null;

  function formatDigital(seconds) {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const remainingSeconds = seconds % 60;
    return [hours, minutes, remainingSeconds]
      .map((part) => String(part).padStart(2, "0"))
      .join(":");
  }

  function formatDuration(seconds) {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const parts = [];
    if (hours > 0) parts.push(`${hours} ${hours === 1 ? "hour" : "hours"}`);
    if (minutes > 0) parts.push(`${minutes} ${minutes === 1 ? "minute" : "minutes"}`);
    return parts.join(" ");
  }

  function formatRingThickness(thickness) {
    if (thickness === 1) return "Hairline";
    if (thickness === 234) return "Solid fill";
    return `${thickness} px`;
  }

  function formatRing(seconds) {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    if (hours > 0) return `${hours}h ${String(minutes).padStart(2, "0")}m`;
    if (minutes > 0) return `${minutes} min`;
    return `${seconds} sec`;
  }

  function formatAccessibleTime(seconds) {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const parts = [];
    if (hours > 0) parts.push(`${hours} ${hours === 1 ? "hour" : "hours"}`);
    if (minutes > 0) parts.push(`${minutes} ${minutes === 1 ? "minute" : "minutes"}`);
    if (parts.length === 0) parts.push("less than one minute");
    return `${parts.join(" ")} remaining`;
  }

  function createMinuteLabels() {
    for (const [container, radius] of [[diskProgress, 50], [ringWrap, 118 / 280 * 100]]) {
      for (let minute = 0; minute < 60; minute += 5) {
        const label = document.createElement("span");
        label.className = "minute-label";
        label.textContent = String(container === diskProgress ? (60 - minute) % 60 : minute);
        const angle = (minute * 6 - 90) * Math.PI / 180;
        label.style.left = `calc(${50 + Math.cos(angle) * radius}% + ${Math.cos(angle) * 32}px)`;
        label.style.top = `calc(${50 + Math.sin(angle) * radius}% + ${Math.sin(angle) * 32}px)`;
        label.setAttribute("aria-hidden", "true");
        container.append(label);
        minuteLabels.push(label);
      }
    }
  }

  function currentRemainingSeconds() {
    if (deadline === null) return durationSeconds;
    return Math.max(0, Math.ceil((deadline - performance.now()) / 1000));
  }

  function updateFan(fractionRemaining) {
    const endAngle = -Math.PI + Math.PI * fractionRemaining;
    const endX = 150 + 145 * Math.cos(endAngle);
    const endY = 150 + 145 * Math.sin(endAngle);
    const path = fractionRemaining === 0
      ? ""
      : `M150 150 L5 150 A145 145 0 0 1 ${endX} ${endY} Z`;
    fanPanel.setAttribute("d", path);
    fanClip.setAttribute("d", path);
  }

  function updateDuration() {
    const hours = Number(durationHoursInput.value);
    const minutes = Number(durationMinutesInput.value);
    const totalMinutes = hours * 60 + minutes;
    const valid = durationHoursInput.value.trim() !== ""
      && durationMinutesInput.value.trim() !== ""
      && Number.isInteger(hours)
      && Number.isInteger(minutes)
      && hours >= 0
      && hours <= 24
      && minutes >= 0
      && minutes <= 59
      && totalMinutes >= 1
      && totalMinutes <= 1440;

    durationError.hidden = valid;
    durationHoursInput.setAttribute("aria-invalid", String(!valid));
    durationMinutesInput.setAttribute("aria-invalid", String(!valid));
    startButton.disabled = !valid;
    if (!valid) return;

    durationSeconds = totalMinutes * 60;
    durationMs = durationSeconds * 1000;
    timerHeading.textContent = formatDuration(durationSeconds);
    startButton.textContent = `Start demo (${formatDuration(durationSeconds)})`;
    updateProgress(currentRemainingSeconds());
  }

  function updateProgress(remainingSeconds) {
    const percentage = Math.max(0, Math.min(100, (remainingSeconds / durationSeconds) * 100));
    const fillAngle = `${(percentage * 3.6).toFixed(2)}deg`;
    const accessibleTime = formatAccessibleTime(remainingSeconds);
    const ringRadius = Math.max(1, (236 - preferences.ringThickness) / 2);
    const ringCircumference = 2 * Math.PI * ringRadius;

    diskProgress.style.setProperty("--fill-angle", fillAngle);
    diskProgress.style.setProperty("--start-angle", `${(360 - percentage * 3.6).toFixed(2)}deg`);
    diskProgress.setAttribute("aria-valuenow", percentage.toFixed(1));
    diskProgress.setAttribute("aria-valuetext", accessibleTime);
    document.getElementById("disk-time").textContent = `${formatDigital(remainingSeconds)} remaining`;

    document.getElementById("digital-time").textContent = formatDigital(remainingSeconds);
    updateFan(percentage / 100);
    digitalFill.style.width = `${percentage}%`;
    digitalProgress.setAttribute("aria-valuenow", percentage.toFixed(1));
    digitalProgress.setAttribute("aria-valuetext", accessibleTime);

    document.getElementById("ring-time").textContent = formatRing(remainingSeconds);
    ringProgress.style.strokeDasharray = `${ringCircumference}`;
    ringProgress.style.strokeDashoffset = `${ringCircumference * (1 - percentage / 100)}`;
    ring.setAttribute("aria-valuenow", percentage.toFixed(1));
    ring.setAttribute("aria-valuetext", accessibleTime);
  }

  function updateAppearanceControls() {
    const preference = preferences;
    root.style.setProperty("--accent", preference.foregroundColor);
    root.style.setProperty("--visualization-background", preference.backgroundColor);
    root.style.setProperty("--visualization-foreground", preference.foregroundColor);
    root.style.setProperty("--visualization-text", preference.textColor);
    document.body.classList.remove("font-rounded", "font-serif", "font-mono");
    if (preference.font !== "sans") {
      document.body.classList.add(`font-${preference.font}`);
    }

    fontSelect.value = preference.font;
    colorPicker.value = preference.foregroundColor;
    textColorPicker.value = preference.textColor;
    backgroundColorPicker.value = preference.backgroundColor;
    showRemainingInput.checked = preference.showRemaining;
    showProgressInput.checked = preference.showProgress;
    const hasVisibleText = preference.showRemaining
      || (preference.showProgress && ["disk", "ring"].includes(preference.shape));
    fontControl.hidden = !hasVisibleText;
    textColorControl.hidden = !hasVisibleText;
    ringThicknessInput.value = String(preference.ringThickness);
    ringThicknessValue.value = formatRingThickness(preference.ringThickness);
    ringThicknessValue.textContent = formatRingThickness(preference.ringThickness);
    ring.style.setProperty("--ring-thickness", `${preference.ringThickness}px`);
    const ringRadius = Math.max(1, (236 - preference.ringThickness) / 2);
    for (const circle of ringCircles) {
      circle.setAttribute("r", String(ringRadius));
    }
    ringThicknessControl.hidden = preference.shape !== "ring";
    shapeSelect.value = preference.shape;
    const selectedView = preference.shape === "battery" || preference.shape === "fan"
      ? "digital"
      : preference.shape;
    digitalProgress.dataset.shape = preference.shape;
    for (const visualization of visualizations) {
      visualization.hidden = visualization.dataset.view !== selectedView;
      visualization.classList.toggle("show-remaining", preference.showRemaining);
      visualization.classList.toggle("show-progress", preference.showProgress);
    }
    for (const label of minuteLabels) {
      label.hidden = !preference.showProgress;
    }
    for (const swatch of swatches) {
      swatch.setAttribute("aria-pressed", String(swatch.dataset.color === preference.foregroundColor));
    }
    for (const swatch of backgroundSwatches) {
      swatch.setAttribute(
        "aria-pressed",
        String(swatch.dataset.backgroundColor === preference.backgroundColor),
      );
    }
    updateProgress(currentRemainingSeconds());
  }

  function tick() {
    const now = performance.now();
    if (deadline !== null && now >= deadline) {
      deadline = now + durationMs;
    }
    updateProgress(currentRemainingSeconds());
  }

  shapeSelect.addEventListener("change", () => {
    preferences.shape = shapeSelect.value;
    updateAppearanceControls();
  });

  fontSelect.addEventListener("change", () => {
    preferences.font = fontSelect.value;
    updateAppearanceControls();
  });

  for (const swatch of swatches) {
    swatch.addEventListener("click", () => {
      preferences.foregroundColor = swatch.dataset.color;
      updateAppearanceControls();
    });
  }

  colorPicker.addEventListener("input", () => {
    preferences.foregroundColor = colorPicker.value;
    updateAppearanceControls();
  });

  textColorPicker.addEventListener("input", () => {
    preferences.textColor = textColorPicker.value;
    updateAppearanceControls();
  });

  backgroundColorPicker.addEventListener("input", () => {
    preferences.backgroundColor = backgroundColorPicker.value;
    updateAppearanceControls();
  });

  for (const swatch of backgroundSwatches) {
    swatch.addEventListener("click", () => {
      preferences.backgroundColor = swatch.dataset.backgroundColor;
      updateAppearanceControls();
    });
  }

  showRemainingInput.addEventListener("change", () => {
    preferences.showRemaining = showRemainingInput.checked;
    updateAppearanceControls();
  });

  showProgressInput.addEventListener("change", () => {
    preferences.showProgress = showProgressInput.checked;
    updateAppearanceControls();
  });

  ringThicknessInput.addEventListener("input", () => {
    preferences.ringThickness = Number(ringThicknessInput.value);
    updateAppearanceControls();
  });

  durationHoursInput.addEventListener("input", updateDuration);
  durationMinutesInput.addEventListener("input", updateDuration);

  personalizationToggle.addEventListener("click", () => {
    const isExpanded = personalizationToggle.getAttribute("aria-expanded") === "true";
    personalizationPanel.hidden = isExpanded;
    personalizationToggle.setAttribute("aria-expanded", String(!isExpanded));
    personalizationToggle.querySelector("span").textContent = isExpanded
      ? "Show personalization"
      : "Hide personalization";
  });

  startButton.addEventListener("click", () => {
    if (deadline !== null || startButton.disabled) return;
    deadline = performance.now() + durationMs;
    durationHoursInput.disabled = true;
    durationMinutesInput.disabled = true;
    startButton.disabled = true;
    durationControl.disabled = true;
    startButton.textContent = "Demo running";
    stateLabel.textContent = "Running";
    stateLabel.classList.add("is-running");
    timerInterval = window.setInterval(tick, 1000);
    tick();
  });

  window.addEventListener("pagehide", () => {
    if (timerInterval !== null) window.clearInterval(timerInterval);
  });

  createMinuteLabels();
  updateAppearanceControls();
  updateDuration();
  updateProgress(durationSeconds);
})();
