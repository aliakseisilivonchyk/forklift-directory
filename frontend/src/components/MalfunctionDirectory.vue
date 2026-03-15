<script setup lang="ts">
import MalfunctionTable from "@/components/MalfunctionTable.vue";
import {ref, watch} from "vue";
import MalfunctionDialog from "@/components/MalfunctionDialog.vue";
import {Malfunction} from "@/types/Malfunction";

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

const malfunctions = ref<Malfunction[]>([]);
const isLoading = ref<boolean>(false);
const malfunction = ref<Malfunction>(null);

watch(() => typedProps.forkliftId, async (newId, _) => {
  await fetchMalfunctions(newId)
});

const fetchMalfunctions = async (id: string) => {
  isLoading.value = true;
  malfunctions.value = [];

  try {
    const response = await fetch(`/api/forklifts/${id}/malfunctions`);

    if (response.ok) {
      malfunctions.value = await response.json();
    } else {
      throw new Error('Failed to fetch malfunctions.');
    }
  } catch (error) {
    console.error('Error caught: ', error);
  }
  isLoading.value = false;
}

const updateMalfunction = (malfunctionToUpdate: Malfunction) => {
  malfunction.value = malfunctionToUpdate;
}
</script>

<template>
  <v-row class="flex-column align-stretch justify-start overflow-auto">
    <v-col class="flex-grow-0 flex-shrink-1"><h1>Простои по погрузчику {{typedProps.forkliftNumber}}</h1></v-col>
    <v-col class="flex-grow-0 flex-shrink-1">
      <MalfunctionDialog
          :forkliftId="typedProps.forkliftId"
          :malfunction="malfunction"/>
    </v-col>
    <v-col class="flex-grow-1 d-flex overflow-auto">
      <MalfunctionTable
          :forkliftId="typedProps.forkliftId"
          :malfunctions="malfunctions"
          :isLoading="isLoading"
          @update="updateMalfunction"/>
    </v-col>
  </v-row>
</template>

<style scoped>

</style>