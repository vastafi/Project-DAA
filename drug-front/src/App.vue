<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-primary">
      <a href class="navbar-brand" @click.prevent>Pharmacy</a>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/home" class="nav-link">
            <font-awesome-icon icon="home" />Home
          </router-link>
        </li>
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to="/admin" class="nav-link">Admin Board</router-link>
        </li>
        <li v-if="showDoctorBoard" class="nav-item">
          <router-link to="/prescriptions" class="nav-link">Doctor Board</router-link>
        </li>
      </div>

      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
        </li>
      </div>
    </nav>

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import UserService from '@/services/user.service';
export default {
  data: function() {
return {
    currentUser: null
  };
},
  mounted() {
    if(localStorage.getItem('user')) {
      UserService.getPublicContent().then(
          response => {
            this.currentUser = response.data;
          }
      );
    }
  },

  computed: {
    showAdminBoard() {
      // eslint-disable-next-line no-undef
      if (this.currentUser && this.currentUser.resource_access['drug-system'].roles) {
        return this.currentUser.resource_access['drug-system'].roles.includes('ADMIN');
      }

      return false;
    },
    showDoctorBoard() {
      // eslint-disable-next-line no-undef
      if (this.currentUser && this.currentUser.resource_access['drug-system'].roles) {
        return this.currentUser.resource_access['drug-system'].roles.includes('DOCTOR');
      }

      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>
