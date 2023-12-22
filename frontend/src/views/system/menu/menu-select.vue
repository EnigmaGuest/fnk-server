<template>
  <n-tree-select
      v-model:value="selectValue"
      :multiple="props.multiple"
      cascade
      checkable
      :loading="props.loading"
      :options="props.options"
      :check-strategy="props.checkStrategy"
      :default-expand-all="props.expandAll"
      :label-field="props.labelField"
      clearable
      :key-field="props.keyField"
  />
</template>
<script setup lang="ts">

import {computed, PropType, reactive, ref, watch} from "vue";
import {querySystemMenuByRootId} from "@/service/api/system";

const props = defineProps({
  value: {
    type: [String, Number, Array] as PropType<string | number | Array<string | number>>,
    default: null
  },
  options: {
    type: Array as PropType<Array<any>>,
    default: []
  },
  multiple: {
    type: Boolean,
    default: false
  },
  checkStrategy: {
    type: String as PropType<'all'|'parent'|'child'>,
    default: 'all'
  },
  expandAll: {
    type: Boolean,
    default: true
  },
  labelField:{
    type: String,
    default: 'name'
  },
  keyField:{
    type: String,
    default: 'id'
  },
  loading: {
    type: Boolean,
    default: false
  },
})
const emits = defineEmits(["update:value"])
const selectValue = computed({
  get(){
    return props.value
  },
  set(val){
    emits("update:value",val)
  }
})






</script>

<style scoped>

</style>