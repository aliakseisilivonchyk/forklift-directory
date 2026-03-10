<script setup>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ForkliftDirectory from "@/components/ForkliftDirectory.vue";
import {ref} from "vue";

const tab = ref('forklift-directory')
</script>

<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <v-row>
          <v-col cols="12">
            <ProfileHeader/>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-sheet color="secondary" rounded="lg">
              <v-tabs v-model="tab" color="primary" direction="vertical" bg-color="none" hide-slider>
                <v-tab value="users" class="text-wrap">Пользователи</v-tab>
                <v-tab value="alerts" class="text-wrap">Уведомления и напоминания</v-tab>
                <v-tab value="settings" class="text-wrap">Настройки АИС ОГПА</v-tab>
                <v-tab value="forklift-directory" class="text-wrap">Справочник погрузчиков</v-tab>
                <v-tab value="backup" class="text-wrap">Резервное копирование и восстановление</v-tab>
                <v-tab value="directories" class="text-wrap">Справочники</v-tab>
              </v-tabs>
            </v-sheet>
          </v-col>
          <v-col cols="10">
            <v-tabs-window v-model="tab" class="flex-fill">
              <v-tabs-window-item value="users">
                <v-sheet color="secondary" rounded="lg">Пользователи</v-sheet>
              </v-tabs-window-item>
              <v-tabs-window-item value="alerts">
                <v-sheet color="secondary" rounded="lg">Уведомления и напоминания</v-sheet>
              </v-tabs-window-item>
              <v-tabs-window-item value="settings">
                <v-sheet color="secondary" rounded="lg">Настройки АИС ОГПА</v-sheet>
              </v-tabs-window-item>
              <v-tabs-window-item value="forklift-directory">
                <v-sheet color="secondary" rounded="lg">
                  <ForkliftDirectory/>
                </v-sheet>
              </v-tabs-window-item>
              <v-tabs-window-item value="backup">
                <v-sheet color="secondary" rounded="lg">Резервное копирование и восстановление</v-sheet>
              </v-tabs-window-item>
              <v-tabs-window-item value="directories">
                <v-sheet color="secondary" rounded="lg">Справочники</v-sheet>
              </v-tabs-window-item>
            </v-tabs-window>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>