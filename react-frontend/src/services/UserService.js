import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/api/users";

class UserService {

  async getUserIdByUserName(userName){
    return await axios.get(`${USER_API_BASE_URL}/${userName}/userId`, { transformResponse: [data => JSON.stringify(data).replaceAll("\"", "")] });
  }

  async getCaloriesGoalByUserId(id){
    return await axios.get(`${USER_API_BASE_URL}/${id}/caloriesGoal`);
  }

  async getDurationGoalByUserId(id){
    return await axios.get(`${USER_API_BASE_URL}/${id}/durationGoal`);
  }

  async getCaloriesTotalBetweenDates(id, caloriesGoalStartDate, caloriesGoalEndDate){
    return await axios.get(`${USER_API_BASE_URL}/${id}/caloriesTotalBetweenDates?startDate=${caloriesGoalStartDate}&endDate=${caloriesGoalEndDate}`);
  }

  async getDurationTotalBetweenDates(id, durationGoalStartDate, durationGoalEndDate){
    return await axios.get(`${USER_API_BASE_URL}/${id}/durationTotalBetweenDates?startDate=${durationGoalStartDate}&endDate=${durationGoalEndDate}`);
  }

  async updateCaloriesGoal(id, caloriesGoalStartDate, caloriesGoalEndDate, calories){
    return await axios.put(`${USER_API_BASE_URL}/${id}/caloriesGoal?startDate=${caloriesGoalStartDate}&endDate=${caloriesGoalEndDate}&caloriesGoal=${calories}`);
  }

  async updateDurationGoal(id, durationGoalStartDate, durationGoalEndDate, duration){
    return await axios.put(`${USER_API_BASE_URL}/${id}/durationGoal?startDate=${durationGoalStartDate}&endDate=${durationGoalEndDate}&durationGoal=${duration}`);
  }
}

export default new UserService();