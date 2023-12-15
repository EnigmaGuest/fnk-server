<script setup lang="ts">
import {useThemeStore} from "@/store";
import {subscribeThemeStore} from "@/store/subscribe";
import {nextTick, provide, ref} from "vue";
import { zhCN, dateZhCN } from 'naive-ui'

const theme = useThemeStore()
subscribeThemeStore()

</script>

<template>
  <n-config-provider :theme="theme.systemTheme" :theme-overrides="theme.getNaiveThemeOverrides" :date-locale="dateZhCN" :locale="zhCN">
    <app-provider>
      <router-view  />
    </app-provider>
  </n-config-provider>
</template>

<style scoped>

</style>
