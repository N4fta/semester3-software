const SNAP_THRESHOLD = 10;

export const findSnapPosition = (currentBox, otherBoxes) => {
  let { left, top } = currentBox;

  otherBoxes.forEach((box) => {
    const insideHorizontally =
      (left >= box.left && left < box.left + box.width) ||
      (left + currentBox.width > box.left &&
        left + currentBox.width <= box.left + box.width);
    const insideVertically =
      (top >= box.top && top < box.top + box.height) ||
      (top + currentBox.height > box.top &&
        top + currentBox.height <= box.top + box.height);

    if (insideHorizontally) {
      if (Math.abs(left - box.left) < SNAP_THRESHOLD) left = box.left;
      if (
        Math.abs(left + currentBox.width - (box.left + box.width)) <
        SNAP_THRESHOLD
      )
        left = box.left + box.width - currentBox.width;
    } else {
      if (Math.abs(left - (box.left + box.width)) < SNAP_THRESHOLD)
        left = box.left + box.width;
      if (Math.abs(box.left - (left + currentBox.width)) < SNAP_THRESHOLD)
        left = box.left - currentBox.width;
    }

    if (insideVertically) {
      if (Math.abs(top - box.top) < SNAP_THRESHOLD) top = box.top;
      if (
        Math.abs(top + currentBox.height - (box.top + box.height)) <
        SNAP_THRESHOLD
      )
        top = box.top + box.height - currentBox.height;
    } else {
      if (Math.abs(box.top - (top + currentBox.height)) < SNAP_THRESHOLD)
        top = box.top - currentBox.height;
      if (Math.abs(top - (box.top + box.height)) < SNAP_THRESHOLD)
        top = box.top + box.height;
    }
  });

  return { left, top };
};
