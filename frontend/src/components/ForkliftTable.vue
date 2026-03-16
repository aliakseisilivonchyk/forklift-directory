<script setup lang="ts">
import {computed, PropType, ref} from "vue";
import {Forklift} from "@/types/Forklift";
import ForkliftDeleteDialog from "@/components/ForkliftDeleteDialog.vue";
import ForkliftCancelUpdateDialog from "@/components/ForkliftCancelUpdateDialog.vue";

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

const cancelDialog = ref<boolean>(false);
const forkliftUpdated = ref<Forklift>(null);
const forkliftBeforeUpdate = ref<Forklift>(null);
const deleteDialog = ref<boolean>(false);
const deleteForkliftId = ref<number>(0);
const deleteForkliftTableIndex = ref<number>(0);
const itemsLength = computed(() => typedProps.forklifts.length);

const headers = [
  { title: 'Код записи', value: 'id', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Марка', value: 'brand', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Номер', value: 'number', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Грузоподъем -ность', value: 'carryingCapacity', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Активен', value: 'isActive', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Время и Дата изменения', value: 'updateTimestamp', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Пользователь', value: 'appUser', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Действия', value: 'actions', align: 'center', headerProps: { class: 'text-body-small' } }
];

const emit = defineEmits(['updateSelected']);

const selectForklift = (item: Forklift) => {
  //TODO: row.select(!row.isSelected);
  if(!item.isNew) {
    emit('updateSelected', item.id, item.number);
  }
};

const cancelNewRow = () => {
  typedProps.forklifts.pop();
};

const saveForklift = async (item: Forklift) => {
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
      throw new Error('Failed to save forklift.');
    }
  } catch (error) {
    console.error('Error caught: ', error);
  }
};

const updateExistingRow = (item: Forklift) => {
  forkliftBeforeUpdate.value = {
    id: item.id,
    brand: item.brand,
    number: item.number,
    carryingCapacity: item.carryingCapacity,
    isActive: item.isActive,
    updateTimestamp: item.updateTimestamp,
    appUser: item.appUser,
    isNew: item.isNew,
    isEdited: item.isEdited
  };

  item.isEdited = true;
  forkliftUpdated.value = item;
};

const cancelUpdateExistingRow = () => {
  forkliftUpdated.value.id = forkliftBeforeUpdate.value.id;
  forkliftUpdated.value.brand = forkliftBeforeUpdate.value.brand;
  forkliftUpdated.value.number = forkliftBeforeUpdate.value.number;
  forkliftUpdated.value.carryingCapacity = forkliftBeforeUpdate.value.carryingCapacity;
  forkliftUpdated.value.isActive = forkliftBeforeUpdate.value.isActive;
  forkliftUpdated.value.updateTimestamp = forkliftBeforeUpdate.value.updateTimestamp;
  forkliftUpdated.value.appUser = forkliftBeforeUpdate.value.appUser;
  forkliftUpdated.value.isNew = forkliftBeforeUpdate.value.isNew;
  forkliftUpdated.value.isEdited = forkliftBeforeUpdate.value.isEdited;
};

const showCancelForkliftDialog = () => {
  cancelDialog.value = true;
};

const updateForklift = async (item: Forklift) => {
  try {
    const response = await fetch(`/api/forklifts/${item.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(item)
    });

    if (response.ok || response.status === 201) {
      await response.json();
      item.isEdited = false;
    } else {
      throw new Error('Failed to update forklift.');
    }
  } catch (error) {
    console.error('Error caught: ', error);
  }
};

const showRemoveForkliftDialog = (id: number, index: number) => {
  deleteForkliftId.value = id;
  deleteForkliftTableIndex.value = index;
  deleteDialog.value = true;
};

const removeForklift = async (id: number, index: number) => {
  try {
    const response = await fetch(`/api/forklifts/${id}`, {
      method: 'DELETE'
    });

    if (response.ok || response.status === 204) {
      typedProps.forklifts.splice(index, 1);
    } else {
      throw new Error('Failed to delete forklift.');
    }
  } catch (error) {
    console.error('Error caught: ', error);
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
      no-data-text="Нет записей">
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
              <v-btn icon variant="text" @click="saveForklift(item)">
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
              <v-btn icon variant="text" @click="updateForklift(item)">
                <v-icon>mdi-check-bold</v-icon>
              </v-btn>
              <v-btn icon variant="text" @click="showCancelForkliftDialog">
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
              <v-btn icon variant="text" @click="showRemoveForkliftDialog(item.id, index)">
                <v-icon>mdi-close-thick</v-icon>
              </v-btn>
            </v-sheet>
          </td>
        </template>
      </tr>
    </template>
  </v-data-table-server>
  <ForkliftDeleteDialog
      v-model="deleteDialog"
      :forkliftId="deleteForkliftId"
      :forkliftTableIndex="deleteForkliftTableIndex"
      @confirm="removeForklift"/>
  <ForkliftCancelUpdateDialog
      v-model="cancelDialog"
      @confirm="cancelUpdateExistingRow"/>
</template>

<style scoped>

</style>