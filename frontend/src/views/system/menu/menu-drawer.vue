<template>
  <n-drawer v-model:show="visible" @after-enter="onAfterEnter" @after-leave="onAfterLeave" :width="props.width" >
    <n-drawer-content :title="`${title}菜单`">
      <BaseForm label-width="140px" label-align="right" ref="formRef" v-model:data="formData" :items="filedItems" :grid-props="{cols:1}" @submit="onSubmit" >
        <template #rootId="{model,field,item}">
          <menu-select v-model:value="model[field]" :options="item?.filedOptions?.options"   />
        </template>
      </BaseForm>
    </n-drawer-content>
  </n-drawer>
</template>
<script setup lang="ts">
import BaseForm from "@/components/basic/form/index.vue"
import {BaseFormItemProps} from "@/components/basic/form/index";
import {computed, PropType, Ref, ref, watch} from "vue";
import MenuSelect from "@/views/system/menu/menu-select.vue";
import {saveOrUpdateSystemMenu} from "@/service";

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
const filedItems = computed(()=>{
  const dynamicItems = []
  switch (formData.value?.type){
    case "TABLE":
      dynamicItems.push({field: 'remark', label: '备注', filedType:'string',isSearch: false,required:false})
      break;
    case "MENU":
      dynamicItems.push({field: 'path', label: '路径或者地址',labelMessage:"非网页为路径：同对应的后端项目中的routerPath。网页模式则为网址。", filedType:'string',isSearch: false,required:true})
      dynamicItems.push({field: 'permission', label: '权限标识', labelMessage:"controller中定义的权限标识",filedType:'string',isSearch: false,required:false})
      dynamicItems.push({field: 'isIframe', label: '是否为网页', filedType:'switch',isSearch: false,required:true})
      dynamicItems.push({field: 'remark', label: '备注', filedType:'string',isSearch: false,required:false})
      break;
    case "BUTTON":
      dynamicItems.push({field: 'permission', label: '权限标识', labelMessage:"controller中定义的权限标识",filedType:'string',isSearch: false,required:true})
      dynamicItems.push( {field: 'remark', label: '备注', filedType:'string',isSearch: false,required:false})
      break;
  }
  return props.formItems.concat(dynamicItems)
})

const visible = computed({
  get(){
    return props.show
  },
  set(val){
    emits("update:show",val)
  }

})
const emits  =defineEmits(["update:show","success"])
const formRef = ref()
const formData:Ref = ref({})
const title = computed(()=>{
  return props.isUpdate ? "编辑" : "新增"
})

async function onSubmit(valid: boolean) {
  if (valid) {
    if (formData.value?.type === "TABLE" || formData.value?.type === "BUTTON") {
      formData.value.path = ""
      formData.value.permission = ""
      formData.value.isIframe = false
    }
    const {data,error} = await saveOrUpdateSystemMenu(formData.value,props.isUpdate)
    if (!error) {
      // @ts-ignore
      window.$message.success(`${props.isUpdate ? "编辑" : "新增"}${data?.name??''}成功`)
      emits("update:show",false)
      emits("success")
      visible.value = false
    }

  }
}
function onAfterEnter(){
  if (props.isUpdate) {
    const obj = {}
    Object.assign(obj,props.data)
    formData.value = obj
  }else {
    formData.value = {
      visible: true,
      isIframe: false,
      type: "TABLE",
      rootId: '0',
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