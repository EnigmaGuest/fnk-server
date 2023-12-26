<template>
  <div class="flex-col-center wh-full">
    <icon-local-404 class="text-400px text-primary" v-if="props.type==='404'"/>
    <icon-local-403 class="text-400px text-primary" v-if="props.type==='403'"/>
    <icon-local-500 class="text-400px text-primary" v-if="props.type==='500'"/>
    <n-button type="primary" class="mt-24px" @click="backHome">
      <template #icon>
        <icon-line-md:telegram/>
      </template>
      返回首页
    </n-button>
  </div>
</template>
<script setup lang="ts">

import {PropType} from "vue";
import {useRoute, useRouter} from "vue-router";

type ExceptionTpe = '403' | '404' | '500'

const props = defineProps({
  type: {
    type: String as PropType<ExceptionTpe>,
    default: '404'
  }
})

const emits = defineEmits(['click'])

const router = useRouter()

function backHome() {
  const {VITE_ROUTE_HOME_PATH} = import.meta.env
  router.push({path: VITE_ROUTE_HOME_PATH})
  emits('click')
}
</script>

<style scoped>

</style>