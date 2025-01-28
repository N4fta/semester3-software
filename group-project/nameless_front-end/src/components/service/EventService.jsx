import axios from "axios";

const API_BASE_URL = "http://" + import.meta.env.VITE_API_BASE_URL + "/events";

const EventService = {
  //#region piecharts
  getCountryDistribution: () =>
    axios.get(`${API_BASE_URL}/distribution/country`), // in the future when theres a lot of countries make this a barchart instead

  getOperatingSystemDistribution: () =>
    axios.get(`${API_BASE_URL}/distribution/os`),

  getLanguageDistribution: () =>
    axios.get(`${API_BASE_URL}/distribution/language`),

  getBrowserDistribution: () =>
    axios.get(`${API_BASE_URL}/distribution/browser`),

  getDeviceDistribution: () => axios.get(`${API_BASE_URL}/distribution/device`),

  getMarketingSourceDistribution: () =>
    axios.get(`${API_BASE_URL}/marketing/source`),
  //#endregion

  //#region top3box
  getItemCount: () => axios.get(`${API_BASE_URL}/items/types`),

  getItemCountBrands: () => axios.get(`${API_BASE_URL}/items/brands`),

  getItemCountCategories: () => axios.get(`${API_BASE_URL}/items/categories`),

  //#endregion

  getSessionCountByDay: (day) => axios.get(`${API_BASE_URL}/session/${day}`),

  //getEventsCountByDay: (day) => axios.get(`${API_BASE_URL}/by/${day}`),

  getEventsCountByWeek: (day) => axios.get(`${API_BASE_URL}/by/7`),

  getAllEvents: () => axios.get(`${API_BASE_URL}`),

  createEvent: (eventData) => axios.post(`${API_BASE_URL}`, eventData),
};

export default EventService;
