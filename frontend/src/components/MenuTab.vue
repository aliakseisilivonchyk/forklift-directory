<script setup>
import { ref } from 'vue'
import ForkliftDirectory from "@/components/ForkliftDirectory.vue";

const tab = ref('forklift-directory')
</script>

<template>
  <v-sheet class="d-flex">
    <v-tabs v-model="tab" color="primary" direction="vertical" bg-color="secondary" hide-slider>
      <v-tab value="users">Пользователи</v-tab>
      <v-tab value="alerts">Уведомления и напоминания</v-tab>
      <v-tab value="settings">Настройки АИС ОГПА</v-tab>
      <v-tab value="forklift-directory">Справочник погрузчиков</v-tab>
      <v-tab value="backup">Резервное копирование и восстановление</v-tab>
      <v-tab value="directories">Справочники</v-tab>
    </v-tabs>

    <v-divider :thickness="20" vertical color="white"></v-divider>

    <v-tabs-window v-model="tab" class="flex-fill">
      <v-tabs-window-item value="users">
        <v-sheet class="pa-5" color="secondary">Пользователи</v-sheet>
      </v-tabs-window-item>
      <v-tabs-window-item value="alerts">
        <v-sheet class="pa-5" color="secondary">Уведомления и напоминания</v-sheet>
      </v-tabs-window-item>
      <v-tabs-window-item value="settings">
        <v-sheet class="pa-5" color="secondary">Настройки АИС ОГПА</v-sheet>
      </v-tabs-window-item>
      <v-tabs-window-item value="forklift-directory">
        <v-sheet class="pa-5" color="secondary">
          <ForkliftDirectory/>
        </v-sheet>
      </v-tabs-window-item>
      <v-tabs-window-item value="backup">
        <v-sheet class="pa-5" color="secondary">Резервное копирование и восстановление</v-sheet>
      </v-tabs-window-item>
      <v-tabs-window-item value="directories">
        <v-sheet class="pa-5" color="secondary">Справочники</v-sheet>
      </v-tabs-window-item>
    </v-tabs-window>
  </v-sheet>
</template>

<style scoped>

</style>