import { useEffect, useState } from "react";
import { Area, AreaChart, CartesianGrid, XAxis, LabelList } from "recharts";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import TimeService from "../service/TimeService";
import {
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
} from "@/components/ui/chart";

// Config for chart styling
const chartConfig = {
  desktop: {
    label: "Events",
    color: "hsl(var(--chart-1))",
  },
};

export default function EventsChart({ dataService }) {
  const [chartData, setChartData] = useState([]);
  const [refresh, setRefresh] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Call TimeService for refresh logic
  TimeService(setRefresh);

  useEffect(() => {
    dataService() // Call dataService function to fetch data
      .then((response) => {
        const distributionKey = response.data
          ? Object.keys(response.data)[0]
          : null;

        if (distributionKey && response.data[distributionKey]) {
          const formattedData = Object.entries(
            response.data[distributionKey]
          ).map(([name, value]) => ({
            Date: name,
            Events: value,
          }));
          setChartData(formattedData);
        } else {
          setError("Invalid data format");
        }

        setLoading(false);
      })
      .catch((err) => {
        console.error("Error loading data:", err);
        setError("Failed to load data");
        setLoading(false);
      });
  }, [dataService, refresh]); // Trigger re-fetch when refresh changes

  // Show loading or error message
  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <Card className="h-full">
      <CardHeader className="pt-4 h-1/6">
        <CardTitle>Events last 7 days</CardTitle>
      </CardHeader>
      <CardContent className="h-5/6">
        <ChartContainer config={chartConfig} className="pb-0 h-full w-full">
          <AreaChart data={chartData} className="p-1">
            <CartesianGrid strokeDasharray="3 3" vertical={false} />
            <XAxis
              dataKey="Date"
              tickLine={true}
              axisLine={true}
              tickMargin={8}
              tickFormatter={(value) => value.slice(8, 10) + "th"}
            />
            <ChartTooltip
              cursor={false}
              content={
                <ChartTooltipContent
                  className="bg-white rounded"
                  indicator="line"
                />
              }
            />
            <Area
              dataKey="Events"
              type="linear"
              dot={{ fill: "#228B22" }}
              activeDot={{
                r: 5,
              }}
              fill="#FFFFFF"
              fillOpacity={1.0}
              strokeWidth={2}
              stroke="#228B22"
            >
              <LabelList
                position="top"
                offset={12}
                className="fill-foreground"
                fontSize={12}
              />
            </Area>
          </AreaChart>
        </ChartContainer>
      </CardContent>
    </Card>
  );
}
