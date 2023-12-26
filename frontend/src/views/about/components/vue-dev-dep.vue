<template>
  <n-card title="VUE开发依赖" :bordered="false" size="small" class="rounded-8px shadow-sm">
    <n-descriptions label-placement="left" bordered size="small">
      <n-descriptions-item v-for="item in devDependencies" :key="item.name" :label="item.name">
        {{ item.version }}
      </n-descriptions-item>
    </n-descriptions>
  </n-card>
</template>
<script setup lang="ts">
import {pkgJson} from "./index";
const { devDependencies } = pkgJson;
</script>

<style scoped>

</style>