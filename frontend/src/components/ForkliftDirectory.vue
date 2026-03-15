<script setup lang="ts">
import ForkliftTable from "@/components/ForkliftTable.vue";
import MalfunctionDirectory from "@/components/MalfunctionDirectory.vue";
import {ref} from 'vue';
import {Forklift} from "@/types/Forklift";

const filterNumber = ref<string>('');
const forklifts = ref<Forklift[]>([]);
const selectedForkliftId = ref<string>('');
const selectedForkliftNumber = ref<string>('');
const isLoading = ref<boolean>(false);

const fetchForklifts = async () => {
  isLoading.value = true;
  forklifts.value = [];

  try {
    const params = new URLSearchParams({ number: filterNumber.value }).toString();
    const response = await fetch(`/api/forklifts?${params}`);

    if (!response.ok) {
      throw new Error('Failed to fetch forklifts.');
    }

    forklifts.value = await response.json();
  } catch (error) {
    console.error('Error caught: ', error);
  }

  isLoading.value = false;
}

const clearFilter = async () => {
  filterNumber.value = '';

  await fetchForklifts();
}

const updateSelectedForklift = (id: string, number: string) => {
  selectedForkliftId.value = id;
  selectedForkliftNumber.value = number;
}

const addNewRow = () => {
  const newRow: Forklift = {
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
          <v-text-field variant="solo" v-model="filterNumber" bg-color="background" rounded="xl"/>
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
          <ForkliftTable
              :forklifts="forklifts"
              :isLoading="isLoading"
              @updateSelected="updateSelectedForklift"/>
        </v-col>
        <v-col class="d-flex overflow-auto">
          <MalfunctionDirectory
              :forkliftId="selectedForkliftId"
              :forkliftNumber="selectedForkliftNumber"/>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<style scoped>

</style>