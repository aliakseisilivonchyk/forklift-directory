<script setup>
import ForkliftTable from "@/components/ForkliftTable.vue";
import MalfunctionDirectory from "@/components/MalfunctionDirectory.vue";
import {ref} from 'vue';

const filterNumber = ref('');
const forklifts = ref([]);
const error = ref(null);
const selectedForkliftId = ref('');
const selectedForkliftNumber = ref('');

const fetchForklifts = async () => {
  forklifts.value = [];
  error.value = null;

  try {
    const params = new URLSearchParams({ number: filterNumber.value }).toString();
    const response = await fetch(`/api/forklifts?${params}`);

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    forklifts.value = await response.json();
  } catch (err) {
    console.error(err);
    error.value = 'Failed to fetch user data.';
  }
}

const clearFilter = async () => {
  filterNumber.value = '';

  await fetchForklifts();
}

const updateSelectedForklift = (id, number) => {
  selectedForkliftId.value = id;
  selectedForkliftNumber.value = number;
}

const addNewRow = () => {
  const newRow = {
    brand: '',
    number: '',
    carryingCapacity: 0,
    isActive: true,
    isNew: true,
    isEdited: false
  };

  forklifts.value.push(newRow);
};
</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col cols="auto">
        <h1>Справочник погрузчиков</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="auto">
        <v-label>
          Номер погрузчика
        </v-label>
      </v-col>
      <v-col cols="2">
        <v-text-field variant="outlined" v-model="filterNumber"/>
      </v-col>
      <v-col cols="auto">
        <v-btn color="primary" @click="fetchForklifts" rounded="lg">
          <v-icon color="white">mdi-magnify</v-icon>
          Искать
        </v-btn>
      </v-col>
      <v-col cols="auto">
        <v-btn color="black" variant="text" @click="clearFilter">
          <v-icon color="primary">mdi-close</v-icon>
          <u>Сбросить фильтр</u>
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="auto">
        <v-btn @click="addNewRow" color="primary" rounded="lg">
          Добавить
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6"><ForkliftTable :forklifts="forklifts" @updateSelected="updateSelectedForklift"/></v-col>
      <v-col cols="6"><MalfunctionDirectory :forkliftId="selectedForkliftId" :forkliftNumber="selectedForkliftNumber"/></v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>