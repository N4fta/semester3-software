import React, { useState } from "react";
import { Button } from "@/components/ui/button";
import {
  X,
  ChevronDown,
  ChevronUp,
  ChartColumn,
  ChartLine,
  ChartPie,
  Medal,
  Map,
  AlignLeft,
} from "lucide-react";

const Sidebar = ({ showSidebar, setShowSidebar, addBox }) => {
  const defaultOpenState = false;

  const [isEventsOpen, setIsEventsOpen] = useState(defaultOpenState);
  const [isCountriesOpen, setIsCountriesOpen] = useState(defaultOpenState);
  const [isOperatingSystemsOpen, setIsOperatingSystemsOpen] =
    useState(defaultOpenState);
  const [isLanguagesOpen, setIsLanguagesOpen] = useState(defaultOpenState);
  const [isBrowsersOpen, setIsBrowsersOpen] = useState(defaultOpenState);
  const [isDevicesOpen, setIsDevicesOpen] = useState(defaultOpenState);
  const [isMarketingSourcesOpen, setIsMarketingSourcesOpen] =
    useState(defaultOpenState);
  const [isBrandsOpen, setIsBrandsOpen] = useState(defaultOpenState);
  const [isItemsOpen, setIsItemsOpen] = useState(defaultOpenState);
  const [isCategoriesOpen, setIsCategoriesOpen] = useState(defaultOpenState);
  const [isSessionsOpen, setIsSessionsOpen] = useState(defaultOpenState);
  const [isLiveFeedOpen, setIsLiveFeedOpen] = useState(defaultOpenState);

  const handleAddBox = (width, height, componentType) => {
    addBox(width, height, componentType);
  };

  const events = [
    {
      label: "Events by country",
      jsxComponent: <Map className="m-1 w-4 aspect-square" />,
      type: "WorldHeatMap",
      width: 600,
      height: 340,
    },
    {
      label: "Events over time",
      jsxComponent: <ChartLine className="m-1 w-4 aspect-square" />,
      type: "EventsLineChart",
      width: 640,
      height: 340,
    },
  ];

  const countries = [
    {
      label: "Country",
      jsxComponent: <ChartPie className="m-1 w-4 aspect-square" />,
      type: "CountryDistributionPieChart",
      width: 330,
      height: 340,
    },
    {
      label: "Country",
      jsxComponent: <ChartColumn className="m-1 w-4 aspect-square" />,
      type: "CountryBarChart",
      width: 570,
      height: 340,
    },
  ];

  const operatingSystems = [
    {
      label: "Operating System",
      jsxComponent: <ChartPie className="m-1 w-4 aspect-square" />,
      type: "OperatingSystemDistributionPieChart",
      width: 330,
      height: 340,
    },
    {
      label: "Operating System",
      jsxComponent: <ChartColumn className="m-1 w-4 aspect-square" />,
      type: "OperatingSystemBarChart",
      width: 570,
      height: 340,
    },
  ];

  const languages = [
    {
      label: "Language",
      jsxComponent: <ChartPie className="m-1 w-4 aspect-square" />,
      type: "LanguageDistributionPieChart",
      width: 330,
      height: 340,
    },
    {
      label: "Language",
      jsxComponent: <ChartColumn className="m-1 w-4 aspect-square" />,
      type: "LanguageBarChart",
      width: 570,
      height: 340,
    },
  ];

  const browsers = [
    {
      label: "Browser",
      jsxComponent: <ChartPie className="m-1 w-4 aspect-square" />,
      type: "BrowserDistributionPieChart",
      width: 330,
      height: 340,
    },
    {
      label: "Browser",
      jsxComponent: <ChartColumn className="m-1 w-4 aspect-square" />,
      type: "BrowserBarChart",
      width: 570,
      height: 340,
    },
  ];

  const devices = [
    {
      label: "Device",
      jsxComponent: <ChartPie className="m-1 w-4 aspect-square" />,
      type: "DeviceDistributionPieChart",
      width: 330,
      height: 340,
    },
    {
      label: "Device",
      jsxComponent: <ChartColumn className="m-1 w-4 aspect-square" />,
      type: "DeviceBarChart",
      width: 570,
      height: 340,
    },
  ];

  const marketingSources = [
    {
      label: "Marketing Source",
      jsxComponent: <ChartPie className="m-1 w-4 aspect-square" />,
      type: "MarketingSourcePieChart",
      width: 330,
      height: 340,
    },
    {
      label: "Marketing Source",
      jsxComponent: <ChartColumn className="m-1 w-4 aspect-square" />,
      type: "MarketingSourceBarChart",
      width: 570,
      height: 340,
    },
  ];

  const brands = [
    {
      label: "Top 5 Brands",
      jsxComponent: <Medal className="m-1 w-4 aspect-square" />,
      type: "TopBrandsBox",
      width: 350,
      height: 340,
    },
  ];

  const items = [
    {
      label: "Top 5 Items",
      jsxComponent: <Medal className="m-1 w-4 aspect-square" />,
      type: "TopItemsBox",
      width: 350,
      height: 340,
    },
  ];

  const categories = [
    {
      label: "Top 5 Categories",
      jsxComponent: <Medal className="m-1 w-4 aspect-square" />,
      type: "TopCategoriesBox",
      width: 350,
      height: 340,
    },
  ];

  const sessions = [
    {
      label: "Sessions",
      jsxComponent: <AlignLeft className="m-1 w-4 aspect-square" />,
      type: "SessionScoreChart",
      width: 350,
      height: 340,
    },
  ];

  const liveFeed = [
    {
      label: "Live Feed",
      jsxComponent: <AlignLeft className="m-1 w-4 aspect-square" />,
      type: "LiveFeedWidget",
      width: 600,
      height: 400,
    },
  ];

  const renderChartList = (charts, isOpen, setIsOpen, title) => (
    <div className="mb-4">
      <Button
        variant="outline"
        className="flex justify-between w-full mb-2"
        onClick={() => setIsOpen(!isOpen)}
        aria-expanded={isOpen}
        aria-controls={`${title.toLowerCase().replace(" ", "-")}-list`}
      >
        {title}
        {isOpen ? (
          <ChevronUp className="h-4 w-4" aria-hidden="true" />
        ) : (
          <ChevronDown className="h-4 w-4" aria-hidden="true" />
        )}
      </Button>
      {isOpen && (
        <ul
          id={`${title.toLowerCase().replace(" ", "-")}-list`}
          className="space-y-2 pl-4"
        >
          {charts.map((chart) => (
            <li key={chart.type}>
              <Button
                variant="link"
                className="w-fit text-left"
                onClick={() =>
                  handleAddBox(
                    chart.width || 400,
                    chart.height || 400,
                    chart.type
                  )
                }
              >
                {chart.jsxComponent}
                {chart.label}
              </Button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );

  return (
    <div
      className={`w-64 bg-white p-4 overflow-y-auto shadow-md absolute top-0 right-0 z-[9999] h-[calc(100dvh-72.4px)] max-h-fit text-left ${
        !showSidebar && "hidden"
      }`}
    >
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-bold text-gray-800">Widgets</h2>
        <Button
          variant="ghost"
          size="icon"
          onClick={() => setShowSidebar(false)}
          aria-label="Close sidebar"
        >
          <X className="h-4 w-4" />
        </Button>
      </div>

      {renderChartList(events, isEventsOpen, setIsEventsOpen, "Events")}

      {renderChartList(sessions, isSessionsOpen, setIsSessionsOpen, "Sessions")}

      {renderChartList(
        countries,
        isCountriesOpen,
        setIsCountriesOpen,
        "Countries"
      )}

      {renderChartList(
        operatingSystems,
        isOperatingSystemsOpen,
        setIsOperatingSystemsOpen,
        "Operating Systems"
      )}

      {renderChartList(
        languages,
        isLanguagesOpen,
        setIsLanguagesOpen,
        "Languages"
      )}

      {renderChartList(browsers, isBrowsersOpen, setIsBrowsersOpen, "Browsers")}

      {renderChartList(devices, isDevicesOpen, setIsDevicesOpen, "Devices")}

      {renderChartList(
        marketingSources,
        isMarketingSourcesOpen,
        setIsMarketingSourcesOpen,
        "Marketing Sources"
      )}

      {renderChartList(brands, isBrandsOpen, setIsBrandsOpen, "Brands")}

      {renderChartList(items, isItemsOpen, setIsItemsOpen, "Items")}

      {renderChartList(
        categories,
        isCategoriesOpen,
        setIsCategoriesOpen,
        "Categories"
      )}

      {renderChartList(
        liveFeed,
        isLiveFeedOpen,
        setIsLiveFeedOpen,
        "Live Feed"
      )}
    </div>
  );
};

export default Sidebar;
