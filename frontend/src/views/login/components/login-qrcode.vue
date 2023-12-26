<template>
  <div class="w-300px h-210px flex-col-center">
    <n-qr-code value="https://github.com/EnigmaGuest/fnk-server" :size="140" :color="theme.color"/>
  </div>
</template>
<script setup lang="ts">

import {useThemeStore} from "@/store";

const theme = useThemeStore();

</script>

<style scoped>

</style>