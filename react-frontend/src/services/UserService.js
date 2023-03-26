import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/api/users";

class UserService {
  getUserById(id){
    return axios.get(`${USER_API_BASE_URL}/${id}`);
  }
}

export default new UserService();