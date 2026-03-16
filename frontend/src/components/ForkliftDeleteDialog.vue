<script setup lang="ts">
import {computed} from 'vue'

const props = defineProps({
  forkliftId: {
    type: Number,
    default: () => null
  },
  forkliftTableIndex: {
    type: Number,
    default: () => 0
  }
});

const dialog = defineModel<boolean>();

const forkliftId = computed(() => props.forkliftId);
const forkliftTableIndex = computed(() => props.forkliftTableIndex);

const emit = defineEmits(['confirm']);

const confirmDialog = () => {
  emit('confirm', forkliftId.value, forkliftTableIndex.value);

  dialog.value = false;
};
</script>

<template>
  <v-dialog v-model="dialog" max-width="400" persistent>
    <v-card title="Удалить погрузчик? Вы уверены?">
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