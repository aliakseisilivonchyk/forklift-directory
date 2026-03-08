<script setup>
import ProfileHeader from "@/components/ProfileHeader.vue";
import MenuTab from "@/components/MenuTab.vue";
</script>

<template>
  <v-app>
    <div>
      <header>
        <ProfileHeader/>
      </header>

      <main>
        <MenuTab/>
      </main>
    </div>
  </v-app>
</template>