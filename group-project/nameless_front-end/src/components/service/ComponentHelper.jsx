import BarChart from "../barChart/BarChart";
import PieChartLabelList from "../pieChart/PieChartLabelList";
import TopBox from "../box/TopBox";
import WorldHeatMap from "../WorldHeatMap";
import EventsChart from "../lineCharts/EventsChart";
import ScoreChart from "../score/ScoreChart";
import LiveFeedWidget from "../liveFeed/liveFeedWidget";
import EventService from "./EventService";

export const getComponent = (componentType) => {
  switch (componentType) {
    // Pie Charts
    case "CountryDistributionPieChart":
      return (
        <PieChartLabelList
          title="Country Distribution"
          dataService={EventService.getCountryDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "OperatingSystemDistributionPieChart":
      return (
        <PieChartLabelList
          title="Operating System Distribution"
          dataService={EventService.getOperatingSystemDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "LanguageDistributionPieChart":
      return (
        <PieChartLabelList
          title="Language Distribution"
          dataService={EventService.getLanguageDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "BrowserDistributionPieChart":
      return (
        <PieChartLabelList
          title="Browser Distribution"
          dataService={EventService.getBrowserDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "DeviceDistributionPieChart":
      return (
        <PieChartLabelList
          title="Device Distribution"
          dataService={EventService.getDeviceDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "MarketingSourcePieChart":
      return (
        <PieChartLabelList
          title="Marketing Source Distribution"
          dataService={EventService.getMarketingSourceDistribution}
          dataKey="value"
          nameKey="name"
        />
      );

    // Bar Charts
    case "CountryBarChart":
      return (
        <BarChart
          title="Country Event Distribution"
          dataService={EventService.getCountryDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "OperatingSystemBarChart":
      return (
        <BarChart
          title="Operating System Distribution"
          dataService={EventService.getOperatingSystemDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "LanguageBarChart":
      return (
        <BarChart
          title="Language Distribution"
          dataService={EventService.getLanguageDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "BrowserBarChart":
      return (
        <BarChart
          title="Browser Distribution"
          dataService={EventService.getBrowserDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "DeviceBarChart":
      return (
        <BarChart
          title="Device Distribution"
          dataService={EventService.getDeviceDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "MarketingSourceBarChart":
      return (
        <BarChart
          title="Marketing Source Distribution"
          dataService={EventService.getMarketingSourceDistribution}
          dataKey="value"
          nameKey="name"
        />
      );

    // Line Charts
    case "EventsLineChart":
      return <EventsChart dataService={EventService.getEventsCountByWeek} />;

    // Top Boxes
    case "TopCountriesBox":
      return (
        <TopBox
          title="Top Countries"
          dataService={EventService.getCountryDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "TopOperatingSystemsBox":
      return (
        <TopBox
          title="Top Operating Systems"
          dataService={EventService.getOperatingSystemDistribution}
          dataKey="value"
          nameKey="name"
        />
      );
    case "TopBrandsBox":
      return (
        <TopBox
          title="Top Brands"
          dataService={EventService.getItemCountBrands}
          dataKey="value"
          nameKey="name"
        />
      );
    case "TopCategoriesBox":
      return (
        <TopBox
          title="Top Categories"
          dataService={EventService.getItemCountCategories}
          dataKey="value"
          nameKey="name"
        />
      );
    case "TopItemsBox":
      return (
        <TopBox
          title="Top Items"
          dataService={EventService.getItemCount}
          dataKey="value"
          nameKey="name"
        />
      );

    // Other Widgets
    case "WorldHeatMap":
      return <WorldHeatMap />;
    case "SessionScoreChart":
      return (
        <ScoreChart
          title="Users"
          dataService={EventService.getSessionCountByDay}
        />
      );
    case "LiveFeedWidget":
      return (
        <LiveFeedWidget
          title="Live Feed"
          websocketUrl={"ws://" + import.meta.env.VITE_API_BASE_URL + "/ws"}
        />
      );

    default:
      return <div>Something went wrong.</div>;
  }
};
