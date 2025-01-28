import React, { useState, useEffect } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import TimeService from "../service/TimeService";

export default function TopBox({
  title = "Top 5",
  dataService = async () => ({
    data: {
      example: {
        "Item 1": 100,
        "Item 2": 90,
        "Item 3": 80,
        "Item 4": 70,
        "Item 5": 60,
      },
    },
  }),
}) {
  const [leaderboardData, setLeaderboardData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(false);

  TimeService(setRefresh);

  useEffect(() => {
    dataService()
      .then((response) => {
        const distributionKey = response.data
          ? Object.keys(response.data)[0]
          : null;

        if (distributionKey && response.data[distributionKey]) {
          const formattedData = Object.entries(response.data[distributionKey])
            .map(([name, value]) => ({ name, value }))
            .sort((a, b) => b.value - a.value)
            .slice(0, 5);
          setLeaderboardData(formattedData);
        }

        setLoading(false);
      })
      .catch((err) => {
        console.error("Error loading data:", err);
        setError("Failed to load data");
        setLoading(false);
      });
  }, [dataService, refresh]);

  const getPositionColor = (index) => {
    switch (index) {
      case 0:
        return "text-yellow-500";
      case 1:
        return "text-gray-400";
      case 2:
        return "text-yellow-700";
      default:
        return "text-gray-800";
    }
  };

  if (loading) {
    return (
      <div className="w-full h-full flex items-center justify-center text-sm sm:text-base">
        Loading...
      </div>
    );
  }

  if (error) {
    return (
      <div className="w-full h-full flex items-center justify-center text-sm sm:text-base text-red-500">
        {error}
      </div>
    );
  }

  return (
    <div className="w-full h-full bg-white p-2 sm:p-4 overflow-hidden">
      <h2 className="text-lg sm:text-xl md:text-2xl font-bold mb-2 sm:mb-4 text-gray-800">
        {title}
      </h2>
      <ul className="space-y-1 sm:space-y-2">
        {leaderboardData.map((item, index) => (
          <li
            key={item.name}
            className="flex items-center justify-between py-1 sm:py-2 px-2 sm:px-3 rounded-md transition-all duration-300 hover:bg-gray-100"
          >
            <div className="flex items-center">
              <span
                className={`text-base sm:text-lg md:text-xl font-bold mr-1 sm:mr-2 ${getPositionColor(
                  index
                )}`}
              >
                {index + 1}.
              </span>
              <span className="text-xs sm:text-sm md:text-base font-semibold text-gray-800 truncate max-w-[120px] sm:max-w-[160px] md:max-w-[200px]">
                {item.name}
              </span>
            </div>
            <span className="text-xs sm:text-sm md:text-base font-bold text-gray-700 ml-1 sm:ml-2">
              {item.value.toLocaleString()}
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
}
