<template>
  <div>
    <h1>Create Drug</h1>
    <form @submit.prevent="createDrug">
      <div class="form-group">
        <label for="id">ID</label>
        <input type="text" id="id" v-model="drug.id" disabled />
      </div>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" id="name" v-model="drug.name" required />
      </div>
      <div class="form-group">
        <label for="splitting">Splitting</label>
        <input type="number" id="splitting" v-model="drug.splitting" required />
      </div>
      <div class="form-group">
        <label for="doseConcentration">Dose Concentration</label>
        <input type="number" id="doseConcentration" v-model="drug.doseConcentration" required />
      </div>
      <div class="form-group">
        <label for="form">Form</label>
        <select id="form" v-model="drug.form">
          <option v-for="formOption in formOptions" :key="formOption.value" :value="formOption.value">{{ formOption.text }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="producer">Producer</label>
        <input type="text" id="producer" v-model="drug.producer" required />
      </div>
      <div class="form-group">
        <label for="country">Country</label>
        <input type="text" id="country" v-model="drug.country" required />
      </div>
      <div class="form-group">
        <label for="quantity">Quantity</label>
        <input type="number" id="quantity" v-model="drug.quantity" required />
      </div>
      <div class="form-group">
        <label for="price">Price</label>
        <input type="number" id="price" v-model="drug.price" required />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <textarea id="description" v-model="drug.description" rows="5" required></textarea>
      </div>
      <div class="form-group">
        <label for="category">Category</label>
        <select id="category" v-model="drug.category">
          <option v-for="categoryOption in categoryOptions" :key="categoryOption.value" :value="categoryOption.value">{{ categoryOption.text }}</option>
        </select>
      </div>
      <button type="submit">Create Drug</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "@/services/auth-header";

export default {
  data() {
    return {
      drug: {
        id: '', // Assuming ID is generated on server-side
        name: '',
        splitting: null,
        doseConcentration: null,
        form: null,
        producer: '',
        country: '',
        quantity: null,
        price: null,
        description: '',
        category: null,
      },
      formOptions: [], // Options for the form select
      categoryOptions: [], // Options for the category select
    };
  },
  methods: {
    async createDrug() {
      const url = `http://localhost:8080/stock-service/drug`;
      await axios.post(url, this.drug , {headers: authHeader()});
    },
  },
  async created() {
    if(this.$route.params.id) {
      const url = `http://localhost:8080/stock-service/drug/details/${this.$route.params.id}`;
      const response = await axios.get(url, {headers: authHeader()});
      this.drug = response.data
    }
  },
};
</script>

<style scoped>
.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}
</style>
