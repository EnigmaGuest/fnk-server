<template>
  <div class="flex-center nowrap-hidden h-56px text-primary transition-all">
    <icon-local-logo class="text-32px " :class="{ 'mr-2':!props.collapsed}"/>
    <h2 v-show="!props.collapsed" class="text-xl ">{{VITE_APP_TITLE}}</h2>
  </div>
</template>
<script setup lang="ts">

const props = defineProps({
  // 侧边栏是否折叠
  collapsed: {
    type: Boolean,
  },
});

const {VITE_APP_TITLE} = import.meta.env;
</script>

<style scoped lang="scss">

</style>