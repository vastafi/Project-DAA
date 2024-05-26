import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://192.168.100.7:9000/realms/master/protocol/openid-connect/userinfo';
class UserService {
  getPublicContent() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  getUserBoard() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL, { headers: authHeader() });
  }
}

export default new UserService();
