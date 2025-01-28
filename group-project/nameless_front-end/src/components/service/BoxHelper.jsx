import { Rnd } from "react-rnd";
import { TrashIcon } from "@heroicons/react/24/solid";
import { findSnapPosition } from "../service/SnappingHelper";

export const DraggableResizableBox = ({
  id,
  left,
  top,
  width,
  height,
  onDragStop,
  onResizeStop,
  onDelete,
  children,
  isEditMode,
  otherBoxes,
}) => {
  return (
    <Rnd
      size={{ width, height }}
      position={{ x: left, y: top }}
      onDragStop={(e, d) => {
        if (isEditMode) {
          const snapPosition = findSnapPosition(
            { left: d.x, top: d.y, width, height },
            otherBoxes
          );
          onDragStop(id, snapPosition.left, snapPosition.top);
        }
      }}
      onResizeStop={(e, direction, ref, delta, position) => {
        if (isEditMode) {
          const snapPosition = findSnapPosition(
            {
              left: position.x,
              top: position.y,
              width: ref.offsetWidth,
              height: ref.offsetHeight,
            },
            otherBoxes
          );
          onResizeStop(
            id,
            ref.offsetWidth,
            ref.offsetHeight,
            snapPosition.left,
            snapPosition.top
          );
        }
      }}
      minWidth={50}
      minHeight={50}
      bounds="parent"
      className={"bg-white shadow-sm"}
      disableDragging={!isEditMode}
      enableResizing={isEditMode}
    >
      <div className="relative h-full w-full">
        {id !== 0 && isEditMode && (
          <button
            onClick={() => onDelete(id)}
            className="absolute top-2 right-2 bg-white p-1 rounded-full hover:bg-gray-200 z-50"
            title="Delete Box"
          >
            <TrashIcon className="h-5 w-5" />
          </button>
        )}
        <div className="absolute inset-0 h-full w-full">{children}</div>
        {isEditMode && (
          <div className="absolute bottom-2 left-2 text-xs text-gray-500 bg-gray-100 p-1 rounded">
            {`W: ${Math.round(width)}px, H: ${Math.round(
              height
            )}px, L: ${Math.round(left)}px, T: ${Math.round(top)}px`}
          </div>
        )}
      </div>
    </Rnd>
  );
};

export const isValidPosition = (newLeft, newTop, width, height, boxes) => {
  return !boxes.some((box) => {
    return (
      newLeft < box.left + box.width &&
      newLeft + width > box.left &&
      newTop < box.top + box.height &&
      newTop + height > box.top
    );
  });
};

export const findNextPosition = (
  width,
  height,
  boxes,
  clientWidth,
  clientHeight
) => {
  let newLeft = 0;
  let newTop = 0;
  let isPositionValid = false;

  while (!isPositionValid) {
    isPositionValid = isValidPosition(newLeft, newTop, width, height, boxes);

    if (!isPositionValid) {
      if (newLeft + width + 50 > clientWidth) {
        if (newTop + height + 50 > clientHeight) {
          return { newLeft: 0, newTop: 0 };
        }
        newLeft = 0;
        newTop += 50;
      } else {
        newLeft += 50;
      }
    }
  }

  return { newLeft, newTop };
};
