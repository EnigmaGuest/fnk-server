<template>
  <n-card :bordered="false" title="项目主要技术栈" class="mt-12px" >
    <template #header-extra>
      <n-button text type="primary">更多技术栈</n-button>
    </template>
    <n-grid :item-responsive="true" responsive="screen" cols="m:2 l:3" :x-gap="8" :y-gap="8">
      <n-grid-item v-for="item in technologyList" :key="item.name">
        <div class="flex-col-center h-120px p-24px border-1px border-#efeff5 dark:border-#ffffff17 rounded-4px hover:shadow-sm cursor-pointer" :class="`rounded-${theme.round}px`">
         <div class="flex-col wh-full justify-between">
           <div class="flex">
             <eg-icon :icon="item.icon" class="text-20px"/>
             <p class="text-16px ml-6px">{{item.name}}</p>
           </div>
           <p >{{item.description}}</p>
         </div>
        </div>
      </n-grid-item>
    </n-grid>
  </n-card>
</template>
<script setup lang="ts">

import {useThemeStore} from "@/store";

const theme = useThemeStore();
// 技术列表
const technologyList = [
  {
    name: 'Java',
    description: 'Java 是一种可以撰写跨平台应用软件的面向对象的程序设计语言。',
    icon: "logos:java",
  },
  {
    name: 'SpringBoot',
    description: 'Spring Boot 是一个用于快速构建 Spring 应用程序的 Spring 框架。',
    icon: "logos:spring-icon",
  },
  {
    name: 'Mysql',
    description: 'MySQL 是一个关系型数据库管理系统，由瑞典 MySQL AB 公司开发，目前属于 Oracle 公司。',
    icon: "logos:mysql",
  },
  {
    name: 'Vue',
    description: 'Vue.js 是一套用于构建用户界面的渐进式框架。',
    icon: "logos:vue",
  },
  {
    name: 'TypeScript',
    description: 'TypeScript 是 JavaScript 的一个超集，支持 ECMAScript 6 标准。',
    icon: "logos:typescript-icon",
  },
  {
    name:"NaiveUI",
    description:"一个 Vue 3 + TS 组件库,可以说是非常好用的vue3 ts 开发组件库",
    icon:"logos:naiveui"
  }
]
</script>

<style scoped>

</style>