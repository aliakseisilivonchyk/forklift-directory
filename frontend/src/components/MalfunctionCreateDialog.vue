<script setup>
import {ref, shallowRef} from 'vue'

const typedProps = defineProps({
  forkliftId: {
    type: String,
    default: () => {}
  }
});

const dialog = shallowRef(false)

const startTimestamp = ref('');
const endTimestamp = ref('');
const description = ref('');

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
</script>

<template>
  <v-dialog v-model="dialog" max-width="600">
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn
          class="text-none font-weight-regular"
          text="Добавить"
          v-bind="activatorProps"
          color="primary"
      ></v-btn>
    </template>

    <v-card title="Проблемы с погрузчиком? опишите">
      <v-card-text>
        <v-divider></v-divider>

        <v-row density="comfortable">
          <v-col cols="12" md="3" sm="6">
            <v-label text="начало"></v-label>
          </v-col>

          <v-col cols="12" md="3" sm="6">
            <v-text-field v-model="startTimestamp" required></v-text-field>
          </v-col>

          <v-col cols="12" md="3" sm="6">
            <v-label text="окончание"></v-label>
          </v-col>

          <v-col cols="12" md="3" sm="6">
            <v-text-field v-model="endTimestamp" required></v-text-field>
          </v-col>

          <v-col cols="12" md="3" sm="6">
            <v-label text="описание инцидента"></v-label>
          </v-col>

          <v-col cols="12" md="12" sm="12">
            <v-textarea v-model="description"></v-textarea>
          </v-col>
        </v-row>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn color="primary" text="Сохранить"  @click="createMalfunction"></v-btn>
        <v-btn text="Выход" variant="plain" @click="dialog = false"></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>

</style>