<template>
  <n-drawer v-model:show="visible" @after-enter="onAfterEnter" @after-leave="onAfterLeave" :width="props.width">
    <n-drawer-content :title="title+'系统用户'">
      <BaseForm label-width="120px" label-align="right" ref="formRef" v-model:data="formData" :items="props.formItems"
                :grid-props="{cols:1}" @submit="onSubmit">
      </BaseForm>
    </n-drawer-content>
  </n-drawer>
</template>
<script setup lang="ts">
import BaseForm from "@/components/basic/form/index.vue"
import {BaseFormItemProps} from "@/components/basic/form/index";
import {computed, PropType, Ref, ref, watch} from "vue";
import {querySystemUserRoleList, saveOrUpdateSystemUser} from "@/service";

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

const visible = computed({
  get() {
    return props.show
  },
  set(val) {
    emits("update:show", val)
  }

})
const emits = defineEmits(["update:show", "success"])
const formRef = ref()
const formData: Ref = ref({})
const title = computed(() => {
  return props.isUpdate ? "编辑" : "新增"
})

async function onSubmit(valid: boolean) {
  if (valid) {
    // 接口请求
    const {data,error} = await saveOrUpdateSystemUser(formData.value,props.isUpdate)
    if (!error) {
      // @ts-ignore
      window.$message.success(title.value+`${data.username}成功`)
      emits("update:show", false)
      emits("success")
      visible.value = false
    }
  }
}

async function onGetUserRoleList(id: string) {

  const {data} =  await querySystemUserRoleList(id)
  return data
}
async function onAfterEnter() {
  if (props.isUpdate) {
    const obj = {}
    Object.assign(obj, props.data)
    formData.value = obj
    // 排除密码
    formData.value.password = null
    formData.value.roleIdList = await onGetUserRoleList(formData.value.id)
  } else {
    formData.value = {
      status: true,
      ...props?.data
    }
  }
}

function onAfterLeave() {
  formData.value = {}
}
</script>

<style scoped>

</style>