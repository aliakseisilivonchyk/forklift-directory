<script setup lang="ts">
import {computed, PropType} from "vue";

interface Forklift {
  id: bigint
  brand: string
  number: string
  carryingCapacity: number
  isActive: boolean
  updateTimestamp: string
}

const typedProps = defineProps({
  forklifts: {
    type: Array as PropType<Array<Forklift>>,
    default: () => []
  }
});

const itemsLength = computed(() => typedProps.forklifts.length)

const headers = [
  { title: 'Код записи', value: 'id' },
  { title: 'Марка', value: 'brand' },
  { title: 'Номер', value: 'number' },
  { title: 'Грузоподъемность', value: 'carryingCapacity' },
  { title: 'Активен', key: 'isActive', value: 'isActive' },
  { title: 'Время и Дата изменения', value: 'updateTimestamp' },
  { title: 'Пользователь', value: 'appUser' },
  { title: 'Действия', value: 'actions' }
]
</script>

<template>
  <v-data-table-server :headers="headers" :items="typedProps.forklifts" :items-length="itemsLength" hide-default-footer height="500px" fixed-header>
    <template v-slot:item.isActive="{ item }">
      <v-checkbox-btn v-model="item.isActive" :ripple="false"></v-checkbox-btn>
    </template>
  </v-data-table-server>
</template>

<style scoped>

</style>
