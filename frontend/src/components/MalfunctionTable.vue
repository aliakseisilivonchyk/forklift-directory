<script setup lang="ts">
import {computed, PropType, ref} from "vue";
import humanizeDuration from 'humanize-duration';
import {Malfunction} from "@/types/Malfunction";
import MalfunctionDeleteDialog from "@/components/MalfunctionDeleteDialog.vue";

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

const deleteDialog = ref<boolean>(false);
const deleteMalfunctionId = ref<number>(0);
const deleteMalfunctionTableIndex = ref<number>(0);

const computedMalfunctionsLength = computed(() => typedProps.malfunctions.length);
const computedMalfunctions = computed(() => typedProps.malfunctions.map(malfunction => {
  return {
    ...malfunction,
    downtimeString: humanizeDuration(malfunction.downtime, { language: 'ru', units: ['h', 'm'], round: true })
  }
}));

const emit = defineEmits(['update']);

const headers = [
  { title: 'Код записи', value: 'id', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Начало', value: 'startTimestamp', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Окончание', value: 'endTimestamp', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Время простоя', value: 'downtime', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Причина', value: 'description', align: 'center', headerProps: { class: 'text-body-small' } },
  { title: 'Действия', value: 'actions', align: 'center', headerProps: { class: 'text-body-small' } }
];

const updateMalfunction = (malfunctionToUpdate: Malfunction) => {
  emit('update', malfunctionToUpdate);
};

const showDeleteMalfunctionDialog = (id: number, index: number) => {
  deleteMalfunctionId.value = id;
  deleteMalfunctionTableIndex.value = index;
  deleteDialog.value = true;
};

const deleteMalfunction = async (id: number, index: number) => {
  try {
    const response = await fetch(`/api/forklifts/${typedProps.forkliftId}/malfunctions/${id}`, {
      method: 'DELETE'
    });

    if (response.ok || response.status === 204) {
      typedProps.malfunctions.splice(index, 1);
    } else {
      throw new Error('Failed to delete malfunction.');
    }
  } catch (error) {
    console.error('Error caught: ', error);
  }
};
</script>

<template>
  <v-data-table-server
      :headers="headers"
      :items="computedMalfunctions"
      :items-length="computedMalfunctionsLength"
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
        <td class="text-center text-body-small">{{ item.downtimeString }}</td>
        <td class="text-center text-body-small">{{ item.description }}</td>
        <td class="text-center text-body-small">
          <v-sheet class="d-flex">
            <v-btn icon variant="text" @click="updateMalfunction(item)">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn icon variant="text" @click="showDeleteMalfunctionDialog(item.id, index)">
              <v-icon>mdi-close-thick</v-icon>
            </v-btn>
          </v-sheet>
        </td>
      </tr>
    </template>
  </v-data-table-server>
  <MalfunctionDeleteDialog
      v-model="deleteDialog"
      :malfunctionId="deleteMalfunctionId"
      :malfunctionTableIndex="deleteMalfunctionTableIndex"
      @confirm="deleteMalfunction"/>
</template>

<style scoped>

</style>