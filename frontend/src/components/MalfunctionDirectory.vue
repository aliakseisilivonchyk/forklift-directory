<script setup lang="ts">
import MalfunctionTable from "@/components/MalfunctionTable.vue";
import {ref, watch} from "vue";

const typedProps = defineProps({
  forkliftId: {
    type: String,
    default: () => {}
  },
  forkliftNumber: {
    type: String,
    default: () => []
  }
});

const malfunctions = ref([]);
const error = ref(null);

watch(() => typedProps.forkliftId, async (newId, oldId) => {
  await fetchMalfunctions(newId)
});

const fetchMalfunctions = async (id) => {
  malfunctions.value = [];
  error.value = null;

  try {
    const response = await fetch(`/api/forklifts/${id}/malfunctions`);

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    malfunctions.value = await response.json();
  } catch (err) {
    console.error(err);
    error.value = 'Failed to fetch user data.';
  }
}
</script>

<template>
  <h1>Простои по погрузчику {{typedProps.forkliftNumber}}</h1>
  <v-btn color="primary">
    Добавить
  </v-btn>
  <MalfunctionTable :malfunctions="malfunctions"/>
</template>

<style scoped>

</style>