import { useEffect } from "react";

export default function TimeService(setRefresh) {
  useEffect(() => {
    const interval = setInterval(() => {
      setRefresh((prev) => !prev);
    }, 5000);

    return () => clearInterval(interval);
  }, []);
}
