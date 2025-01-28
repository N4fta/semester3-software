import React, { useState, useEffect } from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  LabelList,
} from "recharts";
import TimeService from "../service/TimeService";
import { vibrantColors } from "@/assets/colorList";

const truncateString = (str, maxLength) => {
  if (str.length > maxLength) {
    return str.substring(0, maxLength) + "...";
  }
  return str;
};

const BarChartComponent = ({
  title,
  dataService,
  xKey = "name",
  yKey = "visitors",
  maxLabelLength = 7,
}) => {
  const [chartData, setChartData] = useState([]);
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
            .map(([name, visitors]) => ({
              name: truncateString(name, maxLabelLength),
              fullName: name,
              visitors,
            }))
            .sort((a, b) => b[yKey] - a[yKey])
            .slice(0, 6); // Limit to top 6 items
          setChartData(formattedData);
        }

        setLoading(false);
      })
      .catch((err) => {
        console.error("Error loading data:", err);
        setError("Failed to load data");
        setLoading(false);
      });
  }, [dataService, refresh, maxLabelLength]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  const pickRandomColors = (length) => {
    return vibrantColors.slice(0, length);
  };

  const colors = pickRandomColors(chartData.length);

  return (
    <div className="w-full h-full relative bg-white rounded-lg shadow-md p-6">
      <div className="mb-4 h-1/6">
        <h2 className="text-2xl font-bold text-gray-800">{title}</h2>
      </div>
      <div className="h-5/6">
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={chartData}>
            {" "}
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey={xKey} />
            <YAxis />
            <Tooltip
              content={({ payload }) => {
                if (payload && payload.length) {
                  const { fullName, visitors } = payload[0].payload;
                  return (
                    <div className="tooltip-content bg-white px-1 rounded border shadow text-[12px] border-l-4 border-l-[#228B22]">
                      <strong>{fullName}</strong>
                      <br />
                      Visitors: {visitors}
                    </div>
                  );
                }
                return null;
              }}
            />
            <Bar dataKey={yKey} fill={colors[0]}>
              <LabelList
                dataKey={yKey}
                position="top"
                className="fill-gray-700 translate-y-[4.5px]"
                fontSize={12}
              />
            </Bar>
          </BarChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
};

export default BarChartComponent;
