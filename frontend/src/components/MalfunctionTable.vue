<script setup lang="ts">
import {computed, PropType} from "vue";

interface Malfunction {
  id: bigint
  startTimestamp: string
  endTimestamp?: string
  downtime?: string
  description: number
}

const typedProps = defineProps({
  forkliftId: {
    type: String,
    default: () => {}
  },
  malfunctions: {
    type: Array as PropType<Array<Malfunction>>,
    default: () => []
  }
});

const itemsLength = computed(() => typedProps.malfunctions.length)

const headers = [
  { title: 'Код записи', value: 'id' },
  { title: 'Начало', value: 'startTimestamp' },
  { title: 'Окончание', value: 'endTimestamp' },
  { title: 'Время простоя', value: 'downtime' },
  { title: 'Причина', value: 'description' },
  { title: 'Действия', value: 'actions' }
]

const updateExistingRow = (item) => {
};

const removeExistingRow = async (item, index) => {
  try {
    const response = await fetch(`/api/forklifts/${typedProps.forkliftId}/malfunctions/${item.id}`, {
      method: 'DELETE'
    });

    if (response.ok || response.status === 204) {
      typedProps.malfunctions.splice(index, 1);
    } else {
      throw new Error('Deletion failed on server');
    }
  } catch (error) {
    console.error('There was an error!', error);
  }
};
</script>

<template>
  <v-data-table-server
      :headers="headers"
      :items="typedProps.malfunctions"
      :items-length="itemsLength"
      hide-default-footer
      fixed-header>
    <template v-slot:item="{ item, index }">
      <tr>
        <td>{{ item.id }}</td>
        <td>{{ item.startTimestamp }}</td>
        <td>{{ item.endTimestamp }}</td>
        <td>{{ item.downtime }}</td>
        <td>{{ item.description }}</td>
        <td>
          <v-btn icon variant="text" @click="updateExistingRow(item)">
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
          <v-btn icon variant="text" @click="removeExistingRow(item, index)">
            <v-icon>mdi-close-thick</v-icon>
          </v-btn>
        </td>
      </tr>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>