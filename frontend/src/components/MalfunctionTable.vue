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
  },
  isLoading: {
    type: Boolean,
    default: () => false
  }
});

const itemsLength = computed(() => typedProps.malfunctions.length)

const headers = [
  { title: 'Код записи', value: 'id', align: 'center' },
  { title: 'Начало', value: 'startTimestamp', align: 'center' },
  { title: 'Окончание', value: 'endTimestamp', align: 'center' },
  { title: 'Время простоя', value: 'downtime', align: 'center' },
  { title: 'Причина', value: 'description', align: 'center' },
  { title: 'Действия', value: 'actions', align: 'center' }
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
      fixed-header
      density="compact"
      :loading="typedProps.isLoading"
      loading-text="Загрузка..."
      no-data-text="Нет записей">
    <template v-slot:item="{ item, index }">
      <tr>
        <td class="text-center">{{ item.id }}</td>
        <td class="text-center">{{ item.startTimestamp }}</td>
        <td class="text-center">{{ item.endTimestamp }}</td>
        <td class="text-center">{{ item.downtime }}</td>
        <td class="text-center">{{ item.description }}</td>
        <td class="text-center">
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