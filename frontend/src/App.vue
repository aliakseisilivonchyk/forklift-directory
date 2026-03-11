<script setup>
import ProfileHeader from "@/components/ProfileHeader.vue";
import ForkliftDirectory from "@/components/ForkliftDirectory.vue";
import {ref} from "vue";

const tab = ref('forklift-directory')
</script>

<template>
  <v-app>
    <v-main>
      <v-container fluid class="fill-height">
        <v-row>
          <v-col cols="12">
            <ProfileHeader/>
          </v-col>
          <v-col cols="12">
            <v-row>
              <v-col cols="1/8">
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
              <v-col cols="7/8">
                <v-tabs-window v-model="tab">
                  <v-tabs-window-item value="users">
                    <v-sheet color="secondary" rounded="lg"><h1>Пользователи</h1></v-sheet>
                  </v-tabs-window-item>
                  <v-tabs-window-item value="alerts">
                    <v-sheet color="secondary" rounded="lg"><h1>Уведомления и напоминания</h1></v-sheet>
                  </v-tabs-window-item>
                  <v-tabs-window-item value="settings">
                    <v-sheet color="secondary" rounded="lg"><h1>Настройки АИС ОГПА</h1></v-sheet>
                  </v-tabs-window-item>
                  <v-tabs-window-item value="forklift-directory">
                    <v-sheet color="secondary" rounded="lg">
                      <ForkliftDirectory/>
                    </v-sheet>
                  </v-tabs-window-item>
                  <v-tabs-window-item value="backup">
                    <v-sheet color="secondary" rounded="lg"><h1>Резервное копирование и восстановление</h1></v-sheet>
                  </v-tabs-window-item>
                  <v-tabs-window-item value="directories">
                    <v-sheet color="secondary" rounded="lg"><h1>Справочники</h1></v-sheet>
                  </v-tabs-window-item>
                </v-tabs-window>
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