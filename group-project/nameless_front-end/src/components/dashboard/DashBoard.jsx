import React, { useState, useRef, useEffect } from "react";
import Sidebar from "./SideBar";
import BASWorldLogo from "@/assets/basworld-logo.png";
import { Button } from "../ui/button";
import axios from "axios";
import { getComponent } from "../service/ComponentHelper";
import { v4 as uuidv4 } from "uuid";
import { useLocation, useNavigate } from "react-router-dom";

import { DraggableResizableBox, findNextPosition } from "../service/BoxHelper";
import { getLayout, saveLayout } from "../service/LayoutService";
import "@/App.css";
import LoadingOverlay from "./LoadingOverlay";

export default function Dashboard() {
  const [showSidebar, setShowSidebar] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const [boxes, setBoxes] = useState([]);
  const [loading, setLoading] = useState(true);
  const mainRef = useRef(null);
  const [id, setId] = useState(1);

  const location = useLocation();
  const navigate = useNavigate();

  function haveSameItems(arr1, arr2) {
    if (!arr1 || !arr2) {
      return false;
    }

    if (arr1.length !== arr2.length) {
      return false;
    }

    const sortedArr1 = arr1.slice().sort((a, b) => a.id - b.id);
    const sortedArr2 = arr2.slice().sort((a, b) => a.id - b.id);

    return sortedArr1.every((item, index) => item.id === sortedArr2[index].id);
  }

  const getQueryParams = (param) => {
    const urlParams = new URLSearchParams(location.search);
    return urlParams.get(param);
  };

  const changeQueryParam = (newId) => {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set("id", newId);
    navigate(`?${urlParams.toString()}`, { replace: true });
  };

  useEffect(() => {
    try {
      setLoading(true);
      setBoxes([]);
      let currentId = getQueryParams("id");
      if (currentId === "new") {
        setLoading(false);
        return;
      }
      if (currentId) {
        setId(currentId);
      }
      getLayout(id)
        .then((layout) => {
          setBoxes(layout.boxes);
        })
        .catch(() => {
          setBoxes([]);
        })
        .finally(() => {
          setTimeout(() => {
            setLoading(false);
          }, 500);
        });
    } catch (error) {
      console.error("Something went wrong:", error);
    }
  }, [id]);

  const moveBox = (id, left, top) => {
    setBoxes((prevBoxes) =>
      prevBoxes.map((box) => (box.id === id ? { ...box, left, top } : box))
    );
  };

  const resizeBox = (id, width, height, left, top) => {
    setBoxes((prevBoxes) =>
      prevBoxes.map((box) =>
        box.id === id ? { ...box, width, height, left, top } : box
      )
    );
  };

  const deleteBox = (id) => {
    setBoxes((prevBoxes) => prevBoxes.filter((box) => box.id !== id));
  };

  const addBox = (width, height, componentType) => {
    const { clientWidth, clientHeight } = mainRef.current;

    const { newLeft, newTop } = findNextPosition(
      width,
      height,
      boxes,
      clientWidth,
      clientHeight
    );

    const id = uuidv4();

    setBoxes([
      ...boxes,
      {
        id: id,
        left: newLeft,
        top: newTop,
        width,
        height,
        type: componentType,
      },
    ]);
  };

  const toggleEditMode = () => {
    if (isEditMode == true) {
      setShowSidebar(false);
      handleSaveDashboard();
    }
    setIsEditMode(!isEditMode);
  };

  const handleSaveDashboard = () => {
    try {
      getLayout(id)
        .then((layout) => {
          if (!haveSameItems(boxes, layout.boxes)) {
            saveLayout(boxes).then((id) => {
              changeQueryParam(id);
            });
          }
        })
        .catch(() => {
          saveLayout(boxes).then((id) => {
            changeQueryParam(id);
          });
        });
    } catch (error) {
      console.error("Something went wrong:", error);
    }
  };

  const createNewDashboard = () => {
    setBoxes([]);
    setIsEditMode(true);
  };

  return (
    <>
      {loading && <LoadingOverlay />}
      <div className="flex flex-col bg-gray-100 min-w-full overflow-hidden">
        <header className="flex items-center justify-between p-4 bg-white border-b shadow-sm">
          <div className="flex items-center space-x-2">
            <img src={BASWorldLogo} alt="BAS World logo" className="h-8 w-30" />
            <h1 className="text-xl font-bold text-gray-800">Dashboard</h1>
          </div>
          <div className="flex space-x-2">
            <div className="space-x-2">
              {isEditMode && (
                <Button variant="outline" onClick={createNewDashboard}>
                  Clear Dashboard
                </Button>
              )}
            </div>
            <div className="space-x-2">
              <Button variant="outline" onClick={toggleEditMode}>
                {isEditMode ? "Save Dashboard" : "Edit Dashboard"}
              </Button>
              {isEditMode && (
                <Button
                  variant="outline"
                  onClick={() => setShowSidebar(!showSidebar)}
                >
                  {showSidebar ? "Hide Sidebar" : "Show Sidebar"}
                </Button>
              )}
            </div>
          </div>
        </header>

        <div className="flex flex-1 relative overflow-hidden">
          <main
            ref={mainRef}
            className="p-4 relative z-10 overflow-visible min-h-[calc(100dvh-72.6px)] overflow-x-auto w-dvw"
          >
            {boxes.map((box) => (
              <DraggableResizableBox
                key={box.id}
                id={box.id}
                left={box.left}
                top={box.top}
                width={box.width}
                height={box.height}
                isEditMode={isEditMode}
                onDragStop={moveBox}
                onResizeStop={resizeBox}
                onDelete={deleteBox}
                otherBoxes={boxes.filter((b) => b.id !== box.id)}
              >
                {getComponent(box.type)}
              </DraggableResizableBox>
            ))}
          </main>
          <Sidebar
            showSidebar={showSidebar}
            setShowSidebar={setShowSidebar}
            addBox={(width, height, componentType) =>
              addBox(width, height, componentType)
            }
          />
        </div>
      </div>
    </>
  );
}
