"use client";

import { Pie, PieChart, LabelList } from "recharts";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import {
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
} from "@/components/ui/chart";

import React, { useState, useEffect } from "react";
import TimeService from "../service/TimeService";
import { vibrantColors } from "@/assets/colorList";

export default function PieChartLabelList({
  title,
  dataService,
  dataKey = "value",
  nameKey = "name",
}) {
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
            .map(([name, value], index) => ({
              browser: name,
              visitors: value,
              fill: vibrantColors[index % vibrantColors.length],
            }))
            .sort((a, b) => b["visitors"] - a["visitors"])
            .slice(0, 6); // Limit to top 6 items
          if (areEqual(formattedData, chartData)) {
            return;
          }
          setChartData(formattedData);
        } else {
          setError(`Invalid data format: ${distributionKey} not found.`);
        }

        setLoading(false);
      })
      .catch((err) => {
        console.error("Error loading data:", err);
        setError("Failed to load data");
        setLoading(false);
      });
  }, [dataService, refresh]);

  const areEqual = (arr1, arr2) => {
    if (arr1.length !== arr2.length) return false;
    return arr1.every((obj1) =>
      arr2.some(
        (obj2) =>
          obj1.browser === obj2.browser && obj1.visitors === obj2.visitors
      )
    );
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  const chartConfig = {};

  if (chartData.length === 0) {
    return <div>No data available to display the chart.</div>;
  }

  const pickRandomColors = (length) => {
    return vibrantColors.slice(0, length);
  };

  const colors = pickRandomColors(chartData.length);

  return (
    <Card className="flex flex-col h-full w-full">
      <CardHeader className="items-center pb-0 h-1/6">
        <CardTitle className="text-[clamp(1rem, 5vw, 2rem)] font-bold text-gray-800">
          {title}
        </CardTitle>
      </CardHeader>
      <CardContent className="flex-1 pb-0 !px-0">
        <ChartContainer
          config={chartConfig}
          className="mx-auto h-5/6 px-0 mt-6 w-full"
        >
          <PieChart>
            <ChartTooltip
              content={
                <ChartTooltipContent
                  className="bg-white rounded shadow-lg"
                  nameKey="visitors"
                  hideLabel
                />
              }
            />
            <Pie
              data={chartData}
              dataKey="visitors"
              labelLine={false}
              label={({ payload, ...props }) => {
                return (
                  <text
                    cx={props.cx}
                    cy={props.cy}
                    x={props.x}
                    y={props.y}
                    textAnchor={props.textAnchor}
                    dominantBaseline={props.dominantBaseline}
                    fill="hsla(var(--foreground))"
                  >
                    {payload.browser}
                  </text>
                );
              }}
              nameKey="browser"
            >
              <LabelList
                dataKey="visitors"
                className="fill-background !text-white"
                stroke="none"
                fontSize={12}
                fill="#ffffff"
              />
            </Pie>
          </PieChart>
        </ChartContainer>
      </CardContent>
    </Card>
  );
}
