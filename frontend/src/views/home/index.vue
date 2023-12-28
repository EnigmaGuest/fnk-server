<template>
  <app-container transparent>
    <n-card :bordered="false">
      <n-space justify="space-between" align="center">
        <n-space align="center">
          <n-avatar :src="avatarUrl" round size="large"></n-avatar>
          <div class="flex-col">
            <p class="text-18px font-semibold"> {{ us.userInfo?.username }}</p>
            <p class="text-14px">欢迎，海拉鲁最强骑士：{{ us.userInfo?.username }}，请问今天要去野炊吗？</p>
          </div>
        </n-space>
        <n-space>
          <n-statistic label="树根" tabular-nums value="120/120"></n-statistic>
          <n-statistic label="呀哈哈" tabular-nums value="117/1000"></n-statistic>
          <n-statistic label="神庙" tabular-nums value="72/152"></n-statistic>
        </n-space>
      </n-space>
    </n-card>
    <n-grid :item-responsive="true" :x-gap="16" :y-gap="16">
      <n-grid-item span="0:24 640:24 1024:16">
        <technology-main/>
      </n-grid-item>
      <n-grid-item span="0:24 640:24 1024:8">
        <quick-card />
      </n-grid-item>
    </n-grid>
    <div>
      <dynamic-info />
    </div>

  </app-container>
</template>
<script setup lang="ts">

import {useAuthStore} from "@/store";
import avatarUrl from "@/assets/images/linki.png"
import TechnologyMain from "@/views/home/components/technology-main.vue";
import QuickCard from "@/views/home/components/quick-card.vue";
import DynamicInfo from "@/views/home/components/dynamic-info.vue";

const us = useAuthStore()

</script>

<style scoped>

</style>