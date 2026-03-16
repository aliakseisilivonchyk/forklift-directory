<script setup lang="ts">
import {PropType, ref, shallowRef, watch} from 'vue'
import dayjs from "dayjs";
import {Malfunction} from "@/types/Malfunction";

const typedProps = defineProps({
  forkliftId: {
    type: String,
    default: () => {}
  },
  malfunction: {
    type: Object as PropType<Malfunction>,
    default: () => {}
  }
});

const dialog = shallowRef<boolean>(false);

const malfunctionToSubmit = ref<Malfunction>({
  startTimestamp: dayjs().format('DD.MM.YYYY HH:mm'),
  isNew: true
});
const isFormValid = ref<boolean>(true);

const emit = defineEmits(['submit']);

const submitMalfunction = async () => {
  if (malfunctionToSubmit.value.isNew) {
    await createMalfunction();
  } else {
    await updateMalfunction();
  }

  dialog.value = false;

  emit('submit');
}

const createMalfunction = async () => {
  try {
    const response: Response = await fetch(`/api/forklifts/${typedProps.forkliftId}/malfunctions`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(malfunctionToSubmit.value)
    });

    if (response.ok || response.status === 201) {
      await response.json();
    } else {
      throw new Error('Failed to create malfunction.');
    }
  } catch (error) {
    console.error('Error caught: ', error);
  }
};

const updateMalfunction = async () => {
  try {
    const response = await fetch(`/api/forklifts/${typedProps.forkliftId}/malfunctions/${malfunctionToSubmit.value.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(malfunctionToSubmit.value)
    });

    if (response.ok || response.status === 201) {
      await response.json();
    } else {
      throw new Error('Failed to update malfunction.');
    }
  } catch (error) {
    console.error('Error caught: ', error);
  }
};

const startTimestampRules = [
  value => !!value || 'Обязательное поле.',
  value => /^([0-2][0-9]|3[0-1])\.(0[1-9]|1[0-2])\.\d{4} ([0-1][0-9]|2[0-3]):([0-5][0-9])$/.test(value) ||
      'Формат даты DD.MM.YYYY HH:mm.'
];

const endTimestampRules = [
  value => /^(([0-2][0-9]|3[0-1])\.(0[1-9]|1[0-2])\.\d{4} ([0-1][0-9]|2[0-3]):([0-5][0-9]))?$/.test(value) ||
      'Формат даты DD.MM.YYYY HH:mm.'
];

watch(() => typedProps.malfunction, async (newMalfunction, _) => {
  malfunctionToSubmit.value.id = newMalfunction.id;
  malfunctionToSubmit.value.startTimestamp = newMalfunction.startTimestamp;
  malfunctionToSubmit.value.endTimestamp = newMalfunction.endTimestamp;
  malfunctionToSubmit.value.description = newMalfunction.description;
  malfunctionToSubmit.value.isNew = false;

  dialog.value = true;
});

const createDefaultMalfunction = () => {
  malfunctionToSubmit.value.startTimestamp = dayjs().format('DD.MM.YYYY HH:mm');
  malfunctionToSubmit.value.endTimestamp = '';
  malfunctionToSubmit.value.description = '';
  malfunctionToSubmit.value.isNew = true;
};
</script>

<template>
  <v-dialog v-model="dialog" max-width="600">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn
          class="text-none font-weight-regular"
          v-bind="activatorProps"
          color="primary"
          rounded="lg"
          @click="createDefaultMalfunction"
          :disabled="forkliftId === ''">Добавить</v-btn>
    </template>

    <v-card title="Проблемы с погрузчиком? Опишите">
      <v-card-text>
        <v-form v-model="isFormValid" lazy-validation>
          <v-divider color="primary"></v-divider>

          <v-row class="flex-column justify-center">
            <v-col class="flex-grow-0 flex-shrink-1">
              <v-row>
                <v-col>
                  <v-label>Начало</v-label>
                </v-col>
                <v-col>
                  <v-text-field v-model="malfunctionToSubmit.startTimestamp" :rules="startTimestampRules" variant="outlined" required/>
                </v-col>
                <v-col>
                  <v-label>Окончание</v-label>
                </v-col>
                <v-col>
                  <v-text-field v-model="malfunctionToSubmit.endTimestamp" :rules="endTimestampRules" variant="outlined"/>
                </v-col>
              </v-row>
            </v-col>
            <v-col class="flex-grow-0 flex-shrink-1">
              <v-label>Описание инцидента</v-label>
            </v-col>
            <v-col class="flex-grow-1 flex-shrink-0">
              <v-textarea v-model="malfunctionToSubmit.description" variant="outlined"/>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" :disabled="!isFormValid" @click="submitMalfunction" rounded="lg">Сохранить</v-btn>
        <v-btn variant="plain" @click="dialog = false">Выход</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>

</style>