<template>
  <n-tooltip :placement="placement" trigger="hover" v-if="text">
    <template #trigger>
      <div class="flex-center h-full cursor-pointer dark:hover:bg-#333" :class="contentClass">
        <slot></slot>
      </div>
    </template>
    <span>{{ props.text }}</span>
  </n-tooltip>
  <n-tooltip :placement="placement" trigger="hover" v-else>
    <template #trigger>
      <slot></slot>
    </template>
  </n-tooltip>
</template>
<script setup lang="ts">
import {computed, PropType} from "vue";
import {useThemeStore} from "@/store";

const props = defineProps({
  text: {
    type: String,
    default: null
  },
  placement: {
    type: String as PropType<'top' | 'top-start' | 'top-end' | 'bottom' | 'bottom-start' | 'bottom-end' | 'left' | 'left-start' | 'left-end' | 'right' | 'right-start' | 'right-end'>,
    default: 'bottom'
  }
})
const theme = useThemeStore()

const contentClass = computed(() => {
  return `${theme.mode === "dark" ? 'hover:bg-primary' : 'hover:bg-#f6f6f6'}`
})
</script>

<style scoped>

</style>