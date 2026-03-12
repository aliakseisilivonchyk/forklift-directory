<script setup>
import ForkliftTable from "@/components/ForkliftTable.vue";
import MalfunctionDirectory from "@/components/MalfunctionDirectory.vue";
import {ref} from 'vue';

const filterNumber = ref('');
const forklifts = ref([]);
const error = ref(null);
const selectedForkliftId = ref('');
const selectedForkliftNumber = ref('');
const isLoading = ref(false);

const fetchForklifts = async () => {
  isLoading.value = true;
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

  isLoading.value = false;
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
  <v-row class="flex-column align-stretch justify-start">
    <v-col class="flex-grow-0 flex-shrink-1">
      <h1>Справочник погрузчиков</h1>
    </v-col>
    <v-col class="flex-grow-0 flex-shrink-1">
      <v-row>
        <v-col cols="auto">
          <v-label>
            Номер погрузчика
          </v-label>
        </v-col>
        <v-col cols="2">
          <v-text-field variant="solo" v-model="filterNumber" bg-color="background"/>
        </v-col>
        <v-col cols="auto">
          <v-btn color="primary" @click="fetchForklifts" rounded="lg">
            <v-icon color="background">mdi-magnify</v-icon>
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
    </v-col>
    <v-col class="flex-grow-0 flex-shrink-1">
      <v-btn @click="addNewRow" color="primary" rounded="lg">
        Добавить
      </v-btn>
    </v-col>
    <v-col class="flex-grow-1 d-flex">
      <v-row>
        <v-col class="d-flex overflow-auto">
          <ForkliftTable :forklifts="forklifts" :isLoading="isLoading" @updateSelected="updateSelectedForklift"/>
        </v-col>
        <v-col class="d-flex overflow-auto">
          <MalfunctionDirectory :forkliftId="selectedForkliftId" :forkliftNumber="selectedForkliftNumber"/>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<style scoped>

</style>