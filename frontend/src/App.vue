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
      <v-divider :thickness="20" color="white"></v-divider>
      <main>
        <MenuTab/>
      </main>
    </div>
  </v-app>
</template>