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
  isEdited?: boolean
}

const typedProps = defineProps({
  forklifts: {
    type: Array as PropType<Array<Forklift>>,
    default: () => []
  },
  isLoading: {
    type: Boolean,
    default: () => false
  }
});

const itemsLength = computed(() => typedProps.forklifts.length)

const headers = [
  { title: 'Код записи', value: 'id', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Марка', value: 'brand', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Номер', value: 'number', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Грузоподъем -ность', value: 'carryingCapacity', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Активен', value: 'isActive', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Время и Дата изменения', value: 'updateTimestamp', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Пользователь', value: 'appUser', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Действия', value: 'actions', align: 'center', headerProps: { class: 'text-body-small' } }
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
  item.isEdited = true;
};

const cancelUpdateExistingRow = (item) => {
  item.isEdited = false;
};

const saveExistingRow = async (item) => {
  try {
    const response = await fetch(`/api/forklifts/${item.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(item)
    });

    if (response.ok || response.status === 201) {
      const updatedItem = await response.json();
      item.isEdited = false;
    } else {
      throw new Error('Deletion failed on server');
    }
  } catch (error) {
    console.error('There was an error!', error);
  }
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
      fixed-header
      select-strategy="single"
      density="compact"
      :loading="typedProps.isLoading"
      loading-text="Загрузка..."
      no-data-text="Нет записей"
      @click:row="selectForklift">
    <template v-slot:item="{ item, index }">
      <tr @click="selectForklift(item)">
        <template v-if="item.isNew">
          <!-- Display inputs for the new row -->
          <td></td>
          <td><v-text-field variant="outlined" v-model="item.brand" hide-details dense single-line rounded="xl"/></td>
          <td><v-text-field variant="outlined" v-model="item.number" hide-details dense single-line rounded="xl"/></td>
          <td><v-text-field variant="outlined" v-model="item.carryingCapacity" hide-details dense single-line rounded="xl"/></td>
          <td><v-checkbox-btn v-model="item.isActive" disabled/></td>
          <td></td>
          <td></td>
          <td>
            <v-sheet class="d-flex">
              <v-btn icon variant="text" @click="saveNewRow(item)">
                <v-icon>mdi-check-bold</v-icon>
              </v-btn>
              <v-btn icon variant="text" @click="cancelNewRow()">
                <v-icon>mdi-close-thick</v-icon>
              </v-btn>
            </v-sheet>
          </td>
        </template>
        <template v-else-if="item.isEdited">
          <!-- Display inputs for the edited row -->
          <td class="text-center text-body-small">{{ item.id }}</td>
          <td><v-text-field variant="outlined" v-model="item.brand" hide-details dense single-line rounded="xl"/></td>
          <td><v-text-field variant="outlined" v-model="item.number" hide-details dense single-line rounded="xl"/></td>
          <td><v-text-field variant="outlined" v-model="item.carryingCapacity" hide-details dense single-line rounded="xl"/></td>
          <td><v-checkbox-btn v-model="item.isActive" disabled/></td>
          <td class="text-center text-body-small">{{ item.updateTimestamp }}</td>
          <td class="text-center text-body-small">{{ item.appUser }}</td>
          <td>
            <v-sheet class="d-flex">
              <v-btn icon variant="text" @click="saveExistingRow(item)">
                <v-icon>mdi-check-bold</v-icon>
              </v-btn>
              <v-btn icon variant="text" @click="cancelUpdateExistingRow(item)">
                <v-icon>mdi-close-thick</v-icon>
              </v-btn>
            </v-sheet>
          </td>
        </template>
        <template v-else>
          <!-- Default display for existing rows -->
          <td class="text-center text-body-small">{{ item.id }}</td>
          <td class="text-center text-body-small">{{ item.brand }}</td>
          <td class="text-center text-body-small">{{ item.number }}</td>
          <td class="text-center text-body-small">{{ item.carryingCapacity }}</td>
          <td class="text-center text-body-small"><v-checkbox-btn v-model="item.isActive" disabled/></td>
          <td class="text-center text-body-small">{{ item.updateTimestamp }}</td>
          <td class="text-center text-body-small">{{ item.appUser }}</td>
          <td class="text-center">
            <v-sheet class="d-flex">
              <v-btn icon variant="text" @click="updateExistingRow(item)">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-btn icon variant="text" @click="removeExistingRow(item, index)">
                <v-icon>mdi-close-thick</v-icon>
              </v-btn>
            </v-sheet>
          </td>
        </template>
      </tr>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>