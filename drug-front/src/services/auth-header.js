export default function authHeader() {
  let user = JSON.parse(localStorage.getItem('user'));
  // eslint-disable-next-line no-console
  console.log(user);

  if (user && user.access_token) {
    // eslint-disable-next-line no-console
    console.log("access token" + user.access_token)
    return { Authorization: 'Bearer ' + user.access_token }; // for Spring Boot back-end
  } else {
    return {};
  }
}
