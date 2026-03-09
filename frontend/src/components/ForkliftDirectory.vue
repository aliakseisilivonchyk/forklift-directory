<script setup>
import ForkliftTable from "@/components/ForkliftTable.vue";
import MalfunctionDirectory from "@/components/MalfunctionDirectory.vue";
import {ref} from 'vue';

const number = ref('');
const forklifts = ref([]);
const error = ref(null);

const fetchUserData = async () => {
  forklifts.value = [];
  error.value = null;

  try {
    const params = new URLSearchParams({ number: number.value }).toString();
    const response = await fetch(`/api/forklifts?${params}`);

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    forklifts.value = await response.json();
  } catch (err) {
    console.error(err);
    error.value = 'Failed to fetch user data.';
  }
};
</script>

<template>
  <v-container>
    <v-row>
      <v-col>
        <h1>Справочник погрузчиков</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="2">
        <v-label>
          Номер погрузчика
        </v-label>
      </v-col>
      <v-col cols="2">
        <v-text-field v-model="number"/>
      </v-col>
      <v-col cols="2">
        <v-btn color="primary" @click="fetchUserData">
          Искать
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn color="primary">
          Добавить
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6"><ForkliftTable :forklifts="forklifts"/></v-col>
      <v-col cols="6"><MalfunctionDirectory/></v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>