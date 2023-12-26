<template>
  <n-card :bordered="false" title="快捷操作" class="mt-12px">
    <n-grid :item-responsive="true" responsive="screen" cols="m:2 l:3" :x-gap="8" :y-gap="8">
      <n-grid-item v-for="item in quickList" :key="item.id">
        <div class=" flex-col-center h-120px   p-12px border-1px border-#efeff5 dark:border-#ffffff17 rounded-4px hover:shadow-sm cursor-pointer" :class="`rounded-${theme.round}px`" @click="goPage(item.path)">
          <eg-icon :icon="item.icon" class="text-28px text-primary"/>
          <p class="text-16px mt-12px">{{item.name}}</p>
        </div>
      </n-grid-item>
    </n-grid>
  </n-card>
</template>
<script setup lang="ts">


import {useThemeStore} from "@/store";
import {useRouter} from "vue-router";

const theme = useThemeStore();
const router = useRouter()
// 首页 文档 组件示例 系统管理  个人中心 关于
const quickList: Array<{
  id: number,
  name: string,
  icon: string,
  path: string
}> = [
  {
    id: 0,
    name: "首页",
    icon: "line-md:computer",
    path: "/home"
  },
  {
    id: 1,
    name: "文档",
    icon: "line-md:text-box",
    path: "/doc"
  },
  {
    id: 3,
    name: "系统管理",
    icon: "line-md:cog",
    path: "/system"
  },

  {
    id: 2,
    name: "组件示例",
    icon: "line-md:coffee-twotone",
    path: "/component"
  },
  {
    id: 4,
    name: "异常页",
    icon: "line-md:alert-circle",
    path: "/exception"
  },
  {
    id: 5,
    name: "关于",
    icon: "line-md:lightbulb-twotone",
    path: "/about"
  }
]

function goPage(path: string) {
  router.push(path)
}
</script>

<style scoped>

</style>