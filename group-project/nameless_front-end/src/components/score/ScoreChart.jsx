import React, { useEffect, useState } from "react";
import TimeService from "../service/TimeService";

const statsKeys = ["last24Hours", "last3Days", "last7Days", "last30Days"];
const days = [1, 3, 7, 30];

function StatItem({ label, value }) {
  return (
    <div className="text-left p-2 w-fit border-b-2 rounded h-fit">
      <span className="font-bold block text-5xl">{value}</span>
      <p className=" text-gray-600">{label}</p>
    </div>
  );
}

export default function ScoreChart({ title, dataService }) {
  const [refresh, setRefresh] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState();
  const [stats, setStats] = useState({
    last24Hours: 0,
    last3Days: 0,
    last7Days: 0,
    last30Days: 0,
  });

  TimeService(setRefresh);

  useEffect(() => {
    setLoading(true);

    Promise.all(
      days.map((current, index) =>
        dataService(current).then((response) => {
          return { key: statsKeys[index], value: response.data };
        })
      )
    )
      .then((results) => {
        const updatedStats = results.reduce(
          (acc, { key, value }) => {
            acc[key] = value;
            return acc;
          },
          { ...stats }
        );

        setStats(updatedStats);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error loading data:", err);
        setError("Failed to load data");
        setLoading(false);
      });
  }, [dataService, refresh]);
  return (
    <div className=" p-4 w-full h-full mx-auto">
      <h2 className="text-2xl font-bold text-gray-800 h-1/6">{title}</h2>
      <div className="grid grid-cols-2 auto-rows-fr gap-4 h-5/6">
        <StatItem label="Last 24 Hours" value={stats.last24Hours} />
        <StatItem label="Last 3 Days" value={stats.last3Days} />
        <StatItem label="Last 7 Days" value={stats.last7Days} />
        <StatItem label="Last 30 Days" value={stats.last30Days} />
      </div>
    </div>
  );
}
