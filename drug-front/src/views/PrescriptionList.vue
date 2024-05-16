<template>
  <div>
    <h1>Prescription List</h1>
    <button v-if="this.currentUser && this.currentUser.resource_access['drug-system'].roles.includes('DOCTOR')"
            @click="$router.push('/prescription-create')">Add prescription</button>
    <div v-for="prescription in prescriptions" :key="prescription.id" class="drug-card" @click="$router.push(`/prescription-edit/${prescription.id}`)">
      <h2>{{ prescription.series }}</h2>
      <p>{{ prescription.number }}</p>
      <p>{{ prescription.patient.firstName }} {{ prescription.patient.lastName }}</p>
    </div>
    <button v-if="currentPage > 1" @click="prevPage">Previous Page</button>
    <button v-if="hasMorePages" @click="nextPage">Next Page</button>
    <p v-if="loading">Loading prescriptions...</p>
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
      prescriptions: [],
      loading: false,
      error: null,
      searchText: '',
      currentPage: 0,
      pageSize: 10,
      currentUser: null
    };
  },
  created() {
    this.fetchPrescriptions();
  },
  mounted() {
    UserService.getPublicContent().then(
        response => {
          this.currentUser = response.data;
        }
    );
  },
  computed: {
    hasMorePages() {
      // Assuming API response has a total count or indicates more pages are available
      // Replace with your actual logic to determine if there are more pages
      return this.prescriptions.length === this.pageSize; // Example logic (replace with yours)
    },
  },
  methods: {
    router() {
      return router
    },
    async fetchPrescriptions() {
      this.loading = true;
      this.error = null;
      try {
        const params = {
          page: this.currentPage,
          size: this.pageSize
        };
        const url = `http://localhost:8080/prescription-service/prescription/all`; // Base API URL
        const response = await axios.get(url, { params, headers: authHeader() });
        this.prescriptions = response.data
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
        this.fetchPrescriptions();
      }
    },
    nextPage() {
      if (this.hasMorePages) {
        this.currentPage++;
        this.fetchPrescriptions();
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