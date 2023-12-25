<template>

  <app-container>
   <exception-base type="403" />
  </app-container>
</template>
<script setup lang="ts">

import {useRouter} from "vue-router";
import {nextTick} from "vue";

const router = useRouter()
function goHome() {

  nextTick(() => {
    router.push({name: 'home'})
  })
}
</script>

<style scoped>

</style>