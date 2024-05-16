<template>
  <div>
    <h1>Create Prescription</h1>
    <form @submit.prevent="createPrescription">
      <div class="form-group">
        <label for="id">ID</label>
        <input type="text" id="id" v-model="prescription.id" disabled />
      </div>
      <div class="form-group">
        <label for="series">Series</label>
        <input type="text" id="series" v-model="prescription.series" required />
      </div>
      <div class="form-group">
        <label for="number">Number</label>
        <input type="number" id="number" v-model="prescription.number" required />
      </div>
      <p>Status: {{ prescription?.status?.name }}</p>
      <div class="form-group">
        <label for="cui">CUI</label>
        <input type="text" id="cui" v-model="prescription.cui" required />
      </div>
      <div class="form-group">
        <label for="validityDays">Validity Days</label>
        <input type="number" id="validityDays" v-model="prescription.validityDays" required />
      </div>
      <div class="form-group">
        <label for="patient">Patient</label>
        <select id="patient" v-model="prescription.patient" required>
          <option v-for="patient in patients" :key="patient.id" :value="patient">{{ patient.firstName }}  {{ patient.lastName}}</option>
        </select>
      </div>
      <h3>Prescription Drugs</h3>
      <div v-for="(drug, index) in prescription.prescriptionDrugs" :key="index">
        <div class="drug-form-group">
          <label>Drug {{ index + 1 }}</label>
          <div class="drug-fields">
            <input type="text" v-model="drug.name" placeholder="Name" required />
            <input type="text" v-model="drug.description" placeholder="Description" required />
            <input type="text" v-model="drug.activeSubstance" placeholder="Active Substance" required />
            <input type="text" v-model="drug.administrationMethod" placeholder="Administration Method" required />
            <input type="number" v-model="drug.dosage" placeholder="Dosage" required />
          </div>
          <button type="button" @click="removeDrug(index)">Remove Drug</button>
        </div>
      </div>
      <button type="button" @click="addDrug">Add Drug</button>
      <button type="submit" v-if="!prescription.id">Create Prescription</button>
      <button type="submit" v-else>Edit Prescription</button>
      <button @click="activatePrescription(prescription.id)">Activate Prescription</button>
      <button @click="cancelPrescription(prescription.id)">Cancel Prescription</button>
      <button @click="searchDrugs(prescription.id)">Search drugs in pharmacies</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import authHeader from "@/services/auth-header";
import UserService from "@/services/user.service";
export default {
  data() {
    return {
      prescription: {
        id: '', // Assuming ID is generated on server-side
        series: '',
        number: null,
        createdDate: null, // Assuming created date is handled on server-side
        cui: '',
        validityDays: null,
        patient: null,
        prescriptionDrugs: [],
      },
      patients: [], // List of patients (fetched from your API)
      currentPage: 0,
      pageSize: 100,
      currentUser: null,
      showDrugSentModal: true
    };
  },
  mounted() {
    UserService.getPublicContent().then(
        response => {
          this.currentUser = response.data;
        }
    );
  },
  methods: {
    async createPrescription() {
      let url;
      if(this.$route.params.id) {
        url = `http://localhost:8080/prescription-service/prescription/update`;
      } else {
        url = `http://localhost:8080/prescription-service/prescription/create`
      }
      await axios.post(url, this.prescription , {headers: authHeader()});
    },
    addDrug() {
      this.prescription.prescriptionDrugs.push({
        id: null, // Assuming ID is generated on server-side
        name: '',
        description: '',
        activeSubstance: '',
        administrationMethod: '',
        dosage: null,
      });
    },
    removeDrug(index) {
      this.prescription.prescriptionDrugs.splice(index, 1);
    },
    async activatePrescription(id) {
      const url = `http://localhost:8080/prescription-service/prescription/activate?id=${id}`
      await axios.put(url, null,{headers: authHeader()})
    },
    async searchDrugs(id) {
      const url = `http://localhost:8080/prescription-service/prescription/release-drug?id=${id}`
      await axios.get(url, {headers: authHeader()})
    },
    async cancelPrescription(id) {
      const url = `http://localhost:8080/prescription-service/prescription/cancel?id=${id}`
      await axios.put(url, null,{headers: authHeader()})
    }
  },
  async created() {
    const patientsUrl = `http://localhost:8080/prescription-service/patient/all`;
    const params = {
      page: this.currentPage,
      size: this.pageSize
    };
    this.patients = (await axios.get(patientsUrl, {params, headers: authHeader()})).data.content;

    if(this.$route.params.id) {
      const prescriptionUrl = `http://localhost:8080/prescription-service/prescription/${this.$route.params.id}`;
      this.prescription = (await axios.get(prescriptionUrl, {headers: authHeader()})).data;
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

.drug-form-group {
  margin-bottom: 15px;
  border: 1px;
}

.modal {
  position: fixed; /* Stay in place */
  z-index: 9999; /* High stacking order */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.modal-content {
  background-color: #fefefe;
  margin: auto; /* Center the modal content vertically */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be wider or narrower */
}

.modal-content button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: auto;
}
</style>