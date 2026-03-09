<script setup lang="ts">
import {computed, PropType} from "vue";

interface Malfunction {
  id: bigint
  startTimestamp: string
  endTimestamp: string
  description: number
}

const typedProps = defineProps({
  malfunctions: {
    type: Array as PropType<Array<Malfunction>>,
    default: () => []
  }
});

const itemsLength = computed(() => typedProps.malfunctions.length)

const headers = [
  { title: 'Код записи', value: 'id' },
  { title: 'Начало', value: 'startTimestamp' },
  { title: 'Окончание', value: 'endTimestamp' },
  { title: 'Время простоя', value: 'downtime' },
  { title: 'Причина', key: 'isActive', value: 'description' },
  { title: 'Действия', value: 'actions' }
]
</script>

<template>
  <v-data-table-server
      :headers="headers"
      :items="typedProps.malfunctions"
      :items-length="itemsLength"
      hide-default-footer height="500px"
      fixed-header>
  </v-data-table-server>
</template>

<style scoped>

</style>