<script setup lang="ts">
const dialog = defineModel<boolean>();

const emit = defineEmits(['confirm']);

const confirmDialog = () => {
  emit('confirm');

  dialog.value = false;
};
</script>

<template>
  <v-dialog v-model="dialog" max-width="400" persistent>
    <v-card title="Не сохранять внесенные изменения? Вы уверены?">
      <template v-slot:actions>
        <v-spacer></v-spacer>

        <v-btn @click="confirmDialog">Не сохранять</v-btn>
        <v-btn @click="dialog = false">Отмена</v-btn>
      </template>
    </v-card>
  </v-dialog>
</template>

<style scoped>

</style>