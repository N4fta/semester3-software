import { Chart } from "react-google-charts";
import EventService from "./service/EventService";
import React, { useState, useEffect } from "react";
import TimeService from "./service/TimeService";

const country_codes = new Map();
country_codes.set("USA", "United States");
country_codes.set("US", "United States");
country_codes.set("Czechia", "Czech Republic");
country_codes.set("eSwatini", "Swaziland");
country_codes.set("Kingdom of Eswatini", "Swaziland");
country_codes.set("Kosovo", "XK");
country_codes.set("Republic of Kosovo", "XK");
country_codes.set("South Sudan", "SS");
country_codes.set("Republic of South Sudan", "SS");
country_codes.set("Côte d'Ivoire", "Ivory Coast");
country_codes.set("Cote d'Ivoire", "Ivory Coast");
country_codes.set("Republic of Côte d'Ivoire", "Ivory Coast");
country_codes.set("Republic of Cote d'Ivoire", "Ivory Coast");
country_codes.set("North Macedonia", "Macedonia");
country_codes.set("The Netherlands", "Netherlands");
country_codes.set("Great Britain", "United Kingdom");
country_codes.set("Britain", "United Kingdom");
country_codes.set(
  "United Kingdom of Great Britain and Northern Ireland",
  "United Kingdom"
);
country_codes.set("UK", "United Kingdom");
country_codes.set("The Bahamas", "Bahamas");
country_codes.set("Republic of South Africa", "South Africa");
country_codes.set("RSA", "South Africa");
country_codes.set("Congo", "CG");
country_codes.set("Republic of the Congo", "CG");
country_codes.set("Congo Republic", "CG");
country_codes.set("Congo-Brazzaville", "CG");
country_codes.set("DR Congo", "CD");
country_codes.set("Democratic Republic of the Congo", "CD");
country_codes.set("DRC", "CD");
country_codes.set("Congo-Kinshasa", "CD");
country_codes.set("UAE", "United Arab Emirates");
country_codes.set("Emirates", "United Arab Emirates");

const countries = [
  "Albania",
  "Algeria",
  "Andorra",
  "Angola",
  "Antigua and Barbuda",
  "Argentina",
  "Armenia",
  "Australia",
  "Austria",
  "Azerbaijan",
  "Bahamas",
  "Bahrain",
  "Bangladesh",
  "Barbados",
  "Belarus",
  "Belgium",
  "Belize",
  "Benin",
  "Bolivia",
  "Bosnia and Herzegovina",
  "Botswana",
  "Brazil",
  "Brunei",
  "Bulgaria",
  "Burkina Faso",
  "Burundi",
  "Cabo Verde",
  "Cambodia",
  "Cameroon",
  "Canada",
  "Central African Republic",
  "Chad",
  "Chile",
  "China",
  "Colombia",
  "Comoros",
  "Cook Islands",
  "Costa Rica",
  "Ivory Coast",
  "Croatia",
  "Cuba",
  "Cyprus",
  "Czech Republic",
  "CD",
  "Denmark",
  "Djibouti",
  "Dominica",
  "Dominican Republic",
  "Ecuador",
  "Egypt",
  "El Salvador",
  "Equatorial Guinea",
  "Eritrea",
  "Estonia",
  "Eswatini",
  "Ethiopia",
  "Fiji",
  "Finland",
  "France",
  "Gabon",
  "Gambia",
  "Georgia",
  "Germany",
  "Ghana",
  "Greece",
  "Grenada",
  "Guatemala",
  "Guinea",
  "Guinea-Bissau",
  "Guyana",
  "Haiti",
  "Honduras",
  "Hungary",
  "Iceland",
  "India",
  "Indonesia",
  "Iran",
  "Iraq",
  "Ireland",
  "Israel",
  "Italy",
  "Jamaica",
  "Japan",
  "Jordan",
  "Kazakhstan",
  "Kenya",
  "Kiribati",
  "Korea",
  "XK",
  "Kuwait",
  "Kyrgyzstan",
  "Laos",
  "Latvia",
  "Lebanon",
  "Lesotho",
  "Liberia",
  "Libya",
  "Liechtenstein",
  "Lithuania",
  "Luxembourg",
  "Madagascar",
  "Malawi",
  "Malaysia",
  "Maldives",
  "Mali",
  "Malta",
  "Marshall Islands",
  "Mauritania",
  "Mauritius",
  "Mexico",
  "Micronesia",
  "Moldova",
  "Monaco",
  "Mongolia",
  "Montenegro",
  "Morocco",
  "Mozambique",
  "Namibia",
  "Nauru",
  "Nepal",
  "Netherlands",
  "New Zealand",
  "Nicaragua",
  "Niger",
  "Nigeria",
  "Niue",
  "Macedonia",
  "Norway",
  "Oman",
  "Pakistan",
  "Palau",
  "Panama",
  "Papua New Guinea",
  "Paraguay",
  "Peru",
  "Philippines",
  "Poland",
  "Portugal",
  "Qatar",
  "South Korea",
  "CG",
  "Romania",
  "Russia",
  "Rwanda",
  "Saint Kitts and Nevis",
  "Saint Lucia",
  "Saint Vincent and the Grenadines",
  "Samoa",
  "San Marino",
  "Sao Tome and Principe",
  "Saudi Arabia",
  "Senegal",
  "Serbia",
  "Seychelles",
  "Sierra Leone",
  "Singapore",
  "Slovakia",
  "Slovenia",
  "Solomon Islands",
  "Somalia",
  "South Africa",
  "SS",
  "Spain",
  "Sri Lanka",
  "Sudan",
  "Suriname",
  "Sweden",
  "Switzerland",
  "Syria",
  "Tajikistan",
  "Tanzania",
  "Thailand",
  "Timor-Leste",
  "Togo",
  "Tonga",
  "Trinidad and Tobago",
  "Tunisia",
  "Turkey",
  "Turkmenistan",
  "Tuvalu",
  "Uganda",
  "Ukraine",
  "United Arab Emirates",
  "United Kingdom",
  "Uruguay",
  "Uzbekistan",
  "Vanuatu",
  "Venezuela",
  "Vietnam",
  "Yemen",
  "Zambia",
  "Zimbabwe",
  "Greenland",
  "New Caledonia",
  "Isle of Man",
  "Falkland Islands",
  "Western Sahara",
  "Bhutan",
  "North Korea",
  "French Guiana",
  "Swaziland",
  "Taiwan",
  "Myanmar",
  "Puerto Rico",
  "TL",
  "SJ",
];

const countries_map = new Map();
for (let i = 0; i < countries.length; i++) {
  countries_map.set(countries[i], 0);
}

export const options = {};

function WorldHeatMap() {
  const [chartData, setChartData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(false);

  TimeService(setRefresh);

  useEffect(() => {
    // This is to temporarly disable console warnings and errors because this component needs a google API key to not spam the console (Now its free..).
    console.warn = () => {};
    console.error = () => {};
  }, []);

  useEffect(() => {
    EventService.getCountryDistribution()
      .then((response) => {
        const distributionKey = response.data
          ? Object.keys(response.data)[0]
          : null;

        if (distributionKey && response.data[distributionKey]) {
          const formattedData = Object.entries(
            response.data[distributionKey]
          ).map(([name, value]) => ({
            name: name,
            value: value,
          }));
          setChartData(formattedData);
        } else {
          setError(`Invalid data format: ${distributionKey} not found.`);
        }

        setLoading(false);
      })
      .catch((err) => {
        console.log("Error loading data:", err);
        setError("Failed to load data");
        setLoading(false);
      });
  }, [refresh]);
  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }
  var new_data = [["Country", "Visitors"]];
  var country_code = null;
  var country_value = null;
  for (var i = 0; i < chartData.length; i++) {
    try {
      country_code = chartData[i].name;
      if (country_codes.has(country_code)) {
        country_code = country_codes.get(country_code);
      }
      countries_map.set(country_code, chartData[i].value);
    } catch {}
  }

  for (let [key, value] of countries_map) {
    new_data.push([key, value]);
  }

  return (
    <Chart
      chartEvents={[
        {
          eventName: "select",
          callback: ({ chartWrapper }) => {
            const chart = chartWrapper.getChart();
            const selection = chart.getSelection();
            if (selection.length === 0) return;
            const region = data[selection[0].row + 1];
          },
        },
      ]}
      chartType="GeoChart"
      width="100%"
      height="100%"
      data={new_data}
      options={options}
    />
  );
}

export default WorldHeatMap;
