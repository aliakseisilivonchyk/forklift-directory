<script setup>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ForkliftDirectory from "@/components/ForkliftDirectory.vue";
import {ref} from "vue";

const tab = ref('forklift-directory')
</script>

<template>
  <v-app>
    <v-main>
      <v-container fluid class="fill-height d-flex">
        <v-row class="flex-column align-stretch justify-start">
          <v-col class="flex-grow-0 flex-shrink-1">
            <ProfileHeader/>
          </v-col>
          <v-col class="flex-grow-1 d-flex">
            <v-row>
              <v-col cols="1/8" class="d-flex">
                <v-sheet color="secondary" rounded="lg">
                  <v-tabs v-model="tab" color="primary" direction="vertical" bg-color="none" hide-slider>
                    <v-tab value="users">Пользователи</v-tab>
                    <v-tab value="alerts">Уведомления и напоминания</v-tab>
                    <v-tab value="settings">Настройки АИС ОГПА</v-tab>
                    <v-tab value="forklift-directory">Справочник погрузчиков</v-tab>
                    <v-tab value="backup">Резервное копирование и восстановление</v-tab>
                    <v-tab value="directories">Справочники</v-tab>
                  </v-tabs>
                </v-sheet>
              </v-col>
              <v-col cols="7/8" class="d-flex">
                <v-sheet color="secondary" rounded="lg" class="flex-grow-1 d-flex">
                  <v-tabs-window v-model="tab" class="flex-grow-1 d-flex">
                    <v-tabs-window-item value="users"><h1>Пользователи</h1></v-tabs-window-item>
                    <v-tabs-window-item value="alerts"><h1>Уведомления и напоминания</h1></v-tabs-window-item>
                    <v-tabs-window-item value="settings"><h1>Настройки АИС ОГПА</h1></v-tabs-window-item>
                    <v-tabs-window-item value="forklift-directory" class="flex-grow-1 d-flex">
                        <ForkliftDirectory/>
                    </v-tabs-window-item>
                    <v-tabs-window-item value="backup"><h1>Резервное копирование и восстановление</h1></v-tabs-window-item>
                    <v-tabs-window-item value="directories"><h1>Справочники</h1></v-tabs-window-item>
                  </v-tabs-window>
                </v-sheet>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<style>
.v-tab .v-btn__content, .v-tab {
  white-space: normal !important;
  word-break: normal !important;
  text-align: start !important;
}
</style>