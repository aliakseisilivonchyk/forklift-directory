<script setup>
import {ref, shallowRef} from 'vue'
import dayjs from "dayjs";

const typedProps = defineProps({
  forkliftId: {
    type: String,
    default: () => {}
  }
});

const dialog = shallowRef(false)

const startTimestamp = ref(dayjs().format('DD.MM.YYYY HH:mm'));
const endTimestamp = ref('');
const description = ref('');
const isValid = ref(true);

const createMalfunction = async () => {
  try {
    const response = await fetch(`/api/forklifts/${typedProps.forkliftId}/malfunctions`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        startTimestamp: startTimestamp.value,
        endTimestamp: endTimestamp.value,
        description: description.value
      })
    });

    if (response.ok || response.status === 201) {
      const updatedItem = await response.json();

    } else {
      throw new Error('Deletion failed on server');
    }
  } catch (error) {
    console.error('There was an error!', error);
  }

  dialog.value = false;
};

const startTimestampRules = [
  value => !!value || 'Обязательное поле.',
  value => /^([0-2][0-9]|3[0-1])\.(0[1-9]|1[0-2])\.\d{4} ([0-1][0-9]|2[0-3]):([0-5][0-9])$/.test(value) || 'Формат даты DD.MM.YYYY HH:mm.'
];
</script>

<template>
  <v-dialog v-model="dialog" max-width="600">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn
          class="text-none font-weight-regular"
          text="Добавить"
          v-bind="activatorProps"
          color="primary"
          rounded="lg"></v-btn>
    </template>

    <v-card title="Проблемы с погрузчиком? опишите">
      <v-card-text>
        <v-form v-model="isValid" lazy-validation>
          <v-divider color="primary"></v-divider>

          <v-row class="flex-column justify-center">
            <v-col class="flex-grow-0 flex-shrink-1">
              <v-row>
                <v-col>
                  <v-label text="начало"></v-label>
                </v-col>
                <v-col>
                  <v-text-field v-model="startTimestamp" :rules="startTimestampRules" variant="outlined" required></v-text-field>
                </v-col>
                <v-col>
                  <v-label text="окончание"></v-label>
                </v-col>
                <v-col>
                  <v-text-field v-model="endTimestamp" variant="outlined"></v-text-field>
                </v-col>
              </v-row>
            </v-col>
            <v-col class="flex-grow-0 flex-shrink-1">
              <v-label text="описание инцидента"></v-label>
            </v-col>
            <v-col class="flex-grow-1 flex-shrink-0">
              <v-textarea v-model="description" variant="outlined"></v-textarea>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" text="Сохранить" :disabled="!isValid" @click="createMalfunction" rounded="lg"></v-btn>
        <v-btn text="Выход" variant="plain" @click="dialog = false"></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>

</style>