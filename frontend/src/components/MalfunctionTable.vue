<script setup>
const headers = [
  { title: 'Код записи', value: 'id' },
  { title: 'Начало', value: 'startTimestamp' },
  { title: 'Окончание', value: 'endTimestamp' },
  { title: 'Время простоя', value: 'downtime' },
  { title: 'Причина', key: 'isActive', value: 'description' },
  { title: 'Действия', value: 'actions' }
]

const items = [
  {
    id: '1',
    startTimestamp: '16.04.2024 14:12',
    endTimestamp: '16.04.2024 15:47',
    downtime: '1ч 35мин',
    description: 'сломался'
  }
]
</script>

<template>
  <v-data-table-server :headers="headers" :items="items" hide-default-footer height="500px" fixed-header>
    <template v-slot:item.isActive="{ item }">
      <v-checkbox-btn v-model="item.isActive" :ripple="false"></v-checkbox-btn>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>