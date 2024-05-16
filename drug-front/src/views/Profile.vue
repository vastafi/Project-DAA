<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        Profile
      </h3>
      <p>Email: {{ currentUser.email }}</p>
      <p>Username: {{ currentUser.preferred_username }}</p>
      <p>Is email verified: {{ currentUser.email_verified }}</p>
    </header>

  </div>
</template>

<script>
import UserService from "@/services/user.service";

export default {
  name: 'Profile',
  data () {
    return {
      currentUser: null
    }
  },
  mounted() {
    UserService.getPublicContent().then(
        response => {
          this.currentUser = response.data;
        }
    );
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>