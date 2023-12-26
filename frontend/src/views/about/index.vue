<template>
  <app-container transparent>
    <n-space :vertical="true" :size="12">
      <n-card title="关于">
        <p class="text-16px">方脑壳后台管理系统后端采用SpringBoot3.2.0全家桶前端使用vue3+ts+unocss+pinia+axios等构建而成，附带权限管理和代码生成器的实现。是一个前后端一体的开发脚手架，可以直接开箱即用。代码生成适配本前端界面的vue代码，让你的开发起飞～</p>
      </n-card>
      <project-info />
      <java-dep/>
      <vue-dev-dep />
      <vue-pro-dep />
    </n-space>
  </app-container>
</template>
<script setup lang="ts">

import ProjectInfo from "./components/project-info.vue";
import JavaDep from "./components/java-dep.vue";
import VueDevDep from "./components/vue-dev-dep.vue";
import VueProDep from "./components/vue-pro-dep.vue";
</script>

<style scoped>

</style>