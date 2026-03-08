<script setup>
import ForkliftTable from "@/components/ForkliftTable.vue";
import MalfunctionDirectory from "@/components/MalfunctionDirectory.vue";
</script>

<template>
  <v-container>
    <v-row>
      <v-col>
        <h1>Справочник погрузчиков</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn color="primary">
          Добавить
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6"><ForkliftTable/></v-col>
      <v-col cols="6"><MalfunctionDirectory/></v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>