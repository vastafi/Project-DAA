import axios from 'axios';

const API_URL_LOGIN = 'http://192.168.100.7:9000/realms/master/protocol/openid-connect/token';
const API_URL_REGISTER = "http://192.168.100.7:9000/master/users";
const qs = require('qs');
class AuthService {
  login(user) {
    return axios
      .post(API_URL_LOGIN, qs.stringify({
        client_id: "drug-system",
        client_secret: "2TmgUGgVZ9bhxRU8Bqpanz9dWT6TtaSy",
        scope: "openid",
        grant_type: "password",
        username: user.username,
        password: user.password
      }),{
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
      .then(response => {
        if (response.data.access_token) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL_REGISTER, {
      username: user.username,
      enabled: true,
      firstName: user.firstName,
      lastName: user.lastName,
      credentials: [
        {
          type: "password",
          value: user.password
        }
      ]
    });
  }
}

export default new AuthService();
