<script setup lang="ts">
import {computed} from 'vue'

const props = defineProps({
  malfunctionId: {
    type: Number,
    default: () => null
  },
  malfunctionTableIndex: {
    type: Number,
    default: () => 0
  }
});

const dialog = defineModel<boolean>();

const malfunctionId = computed(() => props.malfunctionId);
const malfunctionTableIndex = computed(() => props.malfunctionTableIndex);

const emit = defineEmits(['confirm']);

const confirmDialog = () => {
  emit('confirm', malfunctionId.value, malfunctionTableIndex.value);

  dialog.value = false;
};
</script>

<template>
  <v-dialog v-model="dialog" max-width="400" persistent>
    <v-card title="Удалить информацию о простое? Вы уверены?">
      <template v-slot:actions>
        <v-spacer></v-spacer>

        <v-btn @click="confirmDialog">Удалить</v-btn>
        <v-btn @click="dialog = false">Отмена</v-btn>
      </template>
    </v-card>
  </v-dialog>
</template>

<style scoped>

</style>