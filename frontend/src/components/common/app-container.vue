<template>
  <div  class="w-full flex-col  flex-1" :class="[transparent?'':'bg-container']" >
    <slot></slot>
  </div>

</template>
<script setup lang="ts">
import {computed} from "vue";
import {round} from "colord/helpers";
import {useThemeStore} from "@/store";

/**
 * @description 通用容器组件
 *
 */
const props = defineProps({
  /** 是否透明 */
  transparent: {
    type: Boolean,
    default: true
  },
  round: {
    type: Boolean,
    default: true
  }
})
const theme =  useThemeStore()


</script>

<style scoped>

</style>