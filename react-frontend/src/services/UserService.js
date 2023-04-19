import axios from 'axios';

// const USER_API_BASE_URL = "http://44.239.243.104:8080/api/users";
// const USER_API_BASE_URL = "http://exercisemanager.net:8080/api/users/Alice/userId";
const USER_API_BASE_URL = "http://localhost:8080/api/users";

class UserService {

  async createExercise(id, exerciseName, date, duration){
    return await axios.post(`${USER_API_BASE_URL}/${id}/exerciseList?exerciseName=${exerciseName}&date=${date}&duration=${duration}`);
  }

  async getWeightByUserName(userName){
    return await axios.get(`${USER_API_BASE_URL}/${userName}/weightByUserName`);
  }

  async calculateCalories(weight, exerciseName, duration){
    return await axios.get(`${USER_API_BASE_URL}/calories?weight=${weight}&exerciseName=${exerciseName}&duration=${duration}`);
  }

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

  async getDailyCaloriesSumMap(id, caloriesStartDate, caloriesEndDate) {
    return await axios.get(`${USER_API_BASE_URL}/${id}/dailyCalories?startDate=${caloriesStartDate}&endDate=${caloriesEndDate}`);
  }

  async getDailyDurationSumMap(id, startDate, endDate) {
    return await axios.get(`${USER_API_BASE_URL}/${id}/dailyDurations?startDate=${startDate}&endDate=${endDate}`);
  }

  async getSmallestDurationBetweenDates(id, startDate, endDate) {
    return await axios.get(`${USER_API_BASE_URL}/${id}/smallestDurationBetweenDates?startDate=${startDate}&endDate=${endDate}`);
  }

  async getSmallestCaloriesBetweenDates(id, startDate, endDate) {
    return await axios.get(`${USER_API_BASE_URL}/${id}/smallestCaloriesBetweenDates?startDate=${startDate}&endDate=${endDate}`);
  }

  async getUnderAveragedCaloriesDateBetweenDates(id, caloriesGoal, startDate, endDate) {
    return await axios.get(`${USER_API_BASE_URL}/${id}/underAveragedCaloriesDateBetweenDates?caloriesGoal=${caloriesGoal}&startDate=${startDate}&endDate=${endDate}`);
  }

  async getUnderAveragedDurationDateBetweenDates(id, durationGoal, startDate, endDate) {
    return await axios.get(`${USER_API_BASE_URL}/${id}/underAveragedDurationDateBetweenDates?durationGoal=${durationGoal}&startDate=${startDate}&endDate=${endDate}`);
  }

}

export default new UserService();
