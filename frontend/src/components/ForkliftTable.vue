<script setup lang="ts">
import {computed, PropType} from "vue";

interface Forklift {
  id: bigint
  brand: string
  number: string
  carryingCapacity: number
  isActive: boolean
  updateTimestamp: string
  appUser?: string
  isNew?: boolean
}

const typedProps = defineProps({
  forklifts: {
    type: Array as PropType<Array<Forklift>>,
    default: () => []
  }
});

const itemsLength = computed(() => typedProps.forklifts.length)

const headers = [
  { title: 'Код записи', value: 'id' },
  { title: 'Марка', value: 'brand' },
  { title: 'Номер', value: 'number' },
  { title: 'Грузоподъемность', value: 'carryingCapacity' },
  { title: 'Активен', key: 'isActive', value: 'isActive' },
  { title: 'Время и Дата изменения', value: 'updateTimestamp' },
  { title: 'Пользователь', value: 'appUser' },
  { title: 'Действия', value: 'actions' }
]

const emit = defineEmits(['updateSelected']);

const selectForklift = (item) => {
  //TODO: row.select(!row.isSelected);
  if(!item.isNew) {
    emit('updateSelected', item.id, item.number);
  }
}

const cancelNewRow = () => {
  typedProps.forklifts.pop();
};

const saveNewRow = async (item) => {
  try {
    const response = await fetch(`/api/forklifts`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(item)
    });

    if (response.ok || response.status === 201) {
      const updatedItem = await response.json();
      item.id = updatedItem.id;
      item.updateTimestamp = updatedItem.updateTimestamp;
      item.isNew = false;
    } else {
      throw new Error('Deletion failed on server');
    }
  } catch (error) {
    console.error('There was an error!', error);
  }
};

const updateExistingRow = (item) => {
};

const removeExistingRow = async (item, index) => {
  try {
    const response = await fetch(`/api/forklifts/${item.id}`, {
      method: 'DELETE'
    });

    if (response.ok || response.status === 204) {
      typedProps.forklifts.splice(index, 1);
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
      :items="typedProps.forklifts"
      :items-length="itemsLength"
      hide-default-footer
      height="500px"
      fixed-header
      select-strategy="single"
      @click:row="selectForklift">
    <template v-slot:item="{ item, index }">
      <tr @click="selectForklift(item)">
        <template v-if="item.isNew">
          <!-- Display inputs for the new row -->
          <td></td>
          <td><v-text-field variant="outlined" v-model="item.brand" hide-details dense single-line></v-text-field></td>
          <td><v-text-field variant="outlined" v-model="item.number" hide-details dense single-line></v-text-field></td>
          <td><v-text-field variant="outlined" v-model="item.carryingCapacity" hide-details dense single-line></v-text-field></td>
          <td><v-text-field variant="outlined" v-model="item.isActive" hide-details dense single-line></v-text-field></td>
          <td></td>
          <td></td>
          <td>
            <v-btn icon color="success" @click="saveNewRow(item)">
              <v-icon>mdi-check</v-icon>
            </v-btn>
            <v-btn icon color="error" @click="cancelNewRow()">
              <v-icon>mdi-cancel</v-icon>
            </v-btn>
          </td>
        </template>
        <template v-else>
          <!-- Default display for existing rows -->
          <td>{{ item.id }}</td>
          <td>{{ item.brand }}</td>
          <td>{{ item.number }}</td>
          <td>{{ item.carryingCapacity }}</td>
          <td>{{ item.isActive }}</td>
          <td>{{ item.updateTimestamp }}</td>
          <td>{{ item.appUser }}</td>
          <td>
            <v-btn icon color="success" @click="updateExistingRow(item)">
              <v-icon>mdi-check</v-icon>
            </v-btn>
            <v-btn icon color="error" @click="removeExistingRow(item, index)">
              <v-icon>mdi-cancel</v-icon>
            </v-btn>
          </td>
        </template>
      </tr>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>