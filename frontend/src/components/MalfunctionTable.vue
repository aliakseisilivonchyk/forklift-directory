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
  { title: 'Код записи', value: 'id', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Начало', value: 'startTimestamp', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Окончание', value: 'endTimestamp', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Время простоя', value: 'downtime', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Причина', value: 'description', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Действия', value: 'actions', align: 'center', headerProps: { class: 'text-body-small' } }
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
        <td class="text-center text-body-small">{{ item.id }}</td>
        <td class="text-center text-body-small">{{ item.startTimestamp }}</td>
        <td class="text-center text-body-small">{{ item.endTimestamp }}</td>
        <td class="text-center text-body-small">{{ item.downtime }}</td>
        <td class="text-center text-body-small">{{ item.description }}</td>
        <td class="text-center text-body-small">
          <v-sheet class="d-flex">
            <v-btn icon variant="text" @click="updateExistingRow(item)">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn icon variant="text" @click="removeExistingRow(item, index)">
              <v-icon>mdi-close-thick</v-icon>
            </v-btn>
          </v-sheet>
        </td>
      </tr>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>