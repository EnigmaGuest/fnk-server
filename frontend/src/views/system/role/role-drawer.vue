<template>
  <n-drawer v-model:show="visible" @after-enter="onAfterEnter" @after-leave="onAfterLeave" :width="props.width" >
    <n-drawer-content :title="`${title}角色`">
      <BaseForm label-width="120px" label-align="right" ref="formRef" :data="formData" :items="formItems" :grid-props="{cols:1}" @submit="onSubmit" >
        <template #roleScope="{model,field,item}">
          <menu-select v-model:value="model[field]" v-bind="item?.filedOptions" :loading="optionLoading"   check-strategy="all"/>
        </template>
      </BaseForm>
    </n-drawer-content>
  </n-drawer>
</template>
<script setup lang="ts">

import MenuSelect from "@/views/system/menu/menu-select.vue";
import BaseForm from "@/components/basic/form/index.vue";
import {computed, PropType, Ref, ref} from "vue";
import {BaseFormItemProps} from "@/components/basic/form/index";
import {querySystemRoleMenuList, saveOrUpdateSystemMenu, saveOrUpdateSystemRole} from "@/service";

const props = defineProps({
  show: {
    type: Boolean,
    default: false
  },
  isUpdate: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object,
    default: null
  },
  formItems: {
    type: Array as PropType<Array<BaseFormItemProps>>,
    default: []
  },
  width: {
    type: String,
    default: "1000px"
  },
})
const emits = defineEmits(["update:show","success"])

const visible = computed({
  get() {
    return props.show
  },
  set(value) {
    emits("update:show", value)
  }
})
const title = computed(()=>{
  return props.isUpdate ? "编辑" : "新增"
})
const formRef = ref()
const formData:Ref = ref({})
const optionLoading = ref(false)
async function onSubmit(valid: boolean) {
  if (valid) {
    const {data, error} = await saveOrUpdateSystemRole(formData.value,props.isUpdate)
    if (!error) {
      // @ts-ignore
      window.$message.success(`${props.isUpdate ? "编辑" : "新增"}${data?.roleName??''}成功`)
      emits("update:show",false)
      emits("success")
    }
  }
}
async function getRoleMenuList(id:string){
  optionLoading.value = true
  const {data,error} =  await querySystemRoleMenuList(id)
  if (!error) {
    formData.value.roleScope = data
  }
  optionLoading.value = false
}
async function onAfterEnter(){
  if (props.isUpdate) {
    const obj = {}
    Object.assign(obj,props.data)
    formData.value = obj
    await getRoleMenuList(props?.data?.id)
  }else {
    formData.value = {
      status: true,
      ...props?.data
    }
  }
}
function onAfterLeave(){
  formData.value = {}
}
</script>

<style scoped>

</style>