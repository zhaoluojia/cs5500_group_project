import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/api/users";

class UserService {
  getAllUser(){
    return axios.get(`${USER_API_BASE_URL}/allUsers`);
  }

  getUserById(id){
    return axios.get(`${USER_API_BASE_URL}/${id}`);
  }

  getUserByUserName(userName){
    return axios.get(`${USER_API_BASE_URL}/${userName}`);
  }

  getAllExerciseByUserName(userName) {
    return axios.get(`${USER_API_BASE_URL}/${userName}/exercisesByUserName`);
  }
}

export default new UserService();