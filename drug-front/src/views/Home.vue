<template>
  <div>
    <h1>Drug List</h1>
    <input type="text" v-model="searchText" placeholder="Search Drugs" @change="fetchDrugs()">
    <button v-if="this.currentUser && this.currentUser.resource_access['drug-system'].roles.includes('PHARMACIST')"
            @click="$router.push('/drug-create-edit')">Add drug</button>
    <div v-for="drug in filteredDrugs" :key="drug.id" class="drug-card" @click="edit(drug.id)">
      <h2>{{ drug.name }}</h2>
      <p>Price: ${{ drug.price.toFixed(2) }}</p>
    </div>
    <button v-if="currentPage > 1" @click="prevPage">Previous Page</button>
    <button v-if="hasMorePages" @click="nextPage">Next Page</button>
    <p v-if="loading">Loading drugs...</p>
    <p v-else-if="error">Error fetching drugs: {{ error }}</p>
  </div>
</template>

<script>
import axios from 'axios';
import authHeader from '@/services/auth-header';
import UserService from '@/services/user.service';
import {router} from "@/router";

export default {
  data() {
    return {
      drugs: [],
      loading: false,
      error: null,
      searchText: '',
      currentPage: 0,
      pageSize: 10,
      currentUser: null
    };
  },
  created() {
    this.fetchDrugs();
  },
  mounted() {
    UserService.getPublicContent().then(
        response => {
          this.currentUser = response.data;
        }
    );
  },
  computed: {
    filteredDrugs() {
      return this.drugs.filter(drug => drug.name.toLowerCase().includes(this.searchText.toLowerCase()));
    },
    hasMorePages() {
      // Assuming API response has a total count or indicates more pages are available
      // Replace with your actual logic to determine if there are more pages
      return this.drugs.length === this.pageSize; // Example logic (replace with yours)
    },
  },
  methods: {
    router() {
      return router
    },
    async fetchDrugs() {
      this.loading = true;
      this.error = null;
      try {
        const params = {
          page: this.currentPage,
          size: this.pageSize
        };
        const url = `http://localhost:8080/stock-service/drug/suggestions?name=${this.searchText}`; // Base API URL
        const response = await axios.get(url, { params, headers: authHeader() });
        this.drugs = response.data.content
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
        this.fetchDrugs();
      }
    },
    nextPage() {
      if (this.hasMorePages) {
        this.currentPage++;
        this.fetchDrugs();
      }
    },
    edit(id) {
      if(this.currentUser && this.currentUser.resource_access['drug-system'].roles.includes('PHARMACIST')) {
        this.$router.push(`/drug-edit/${id}`)
      }
    }
  },
};
</script>

<style scoped>
.drug-card {
  background-color: #f5f5f5;
  border: 1px solid #ddd;
  padding: 15px;
  margin-bottom: 15px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  width: 300px; /* Adjust width as needed */
}

.drug-card h2 {
  margin-top: 0;
}
</style>