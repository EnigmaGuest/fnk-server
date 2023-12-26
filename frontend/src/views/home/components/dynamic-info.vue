<template>
  <n-card :bordered="false" title="项目动态" class="mt-12px">
    <n-list hoverable>
      <n-list-item v-for="item in activityList" :key="item.id">
        <template #prefix>
          <n-avatar round size="large" :src="avatarUrl"></n-avatar>
        </template>
        <n-thing :title="item.content" :description="item.time"/>
      </n-list-item>
    </n-list>
  </n-card>
</template>
<script setup lang="ts">


import avatarUrl from "@/assets/images/linki.png"

// 项目动态
const activityList: Array<{
  id: number,
  content: string,
  time: string
}> = [
  {
    id: 0,
    content: "计划启动",
    time: "2023-12-15"
  },
  {
    id: 1,
    content: "确定技术后端SpringBoot3.2、Jdk17 、Mysql5.7、MybatisPlus、sa-token、SpringDoc,前端Vue3、TypeScript、UnoCss、NaiveUI",
    time: "2023-12-15"
  },
  {
    id: 2,
    content: "完成Java后端框架搭建以及代码生成器，且完成界面的基本搭建，权限等核心功能设计。",
    time: "2023-12-22"
  },
  {
    id: 3,
    content: "完成核心功能：动态路由以及权限路由",
    time: "2023-12-25"
  },
  {
    id: 4,
    content: "上线第一个版本，并用老婆给项目取名称【方脑壳】😄,不想动脑壳就用方脑壳。",
    time: "2023-12-25"
  }, {
    id: 5,
    content: "完成第一个版本提交，后续会持续更新，欢迎大家提出宝贵意见。",
    time: "2023-12-26"
  }
]
</script>

<style scoped>

</style>