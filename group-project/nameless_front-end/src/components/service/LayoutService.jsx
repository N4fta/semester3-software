import axios from "axios";

const API_BASE_URL = "http://" + import.meta.env.VITE_API_BASE_URL + "/layout";

export const saveLayout = async (boxList) => {
  try {
    const response = await axios.post(API_BASE_URL, boxList);
    return response.data;
  } catch (error) {
    console.error("Error saving layout:", error);
    throw error;
  }
};

export const getLayout = async (id) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error retrieving layout:", error);
    throw error;
  }
};
