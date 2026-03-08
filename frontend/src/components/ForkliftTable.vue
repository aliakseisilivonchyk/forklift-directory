<script setup>
const headers = [
  { title: 'Код записи', value: 'id' },
  { title: 'Марка', value: 'brand' },
  { title: 'Номер', value: 'number' },
  { title: 'Грузоподъемность', value: 'carryingCapacity' },
  { title: 'Активен', key: 'isActive', value: 'isActive' },
  { title: 'Время и Дата изменения', value: 'updateTimestamp' },
  { title: 'Пользователь', value: 'utilizer' },
  { title: 'Действия', value: 'actions' }
]

const items = [
  {
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },
  {
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },{
    id: '1',
    brand: 'Амкадор',
    number: '45-65 PH-1',
    carryingCapacity: '2,5',
    isActive: true,
    updateTimestamp: '16.04.2024 14:12',
    utilizer: 'Иванов И.И.'
  },
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
