<template>
    <n-card title="表单配置" class="mb-12px">
      <form :data="formSetting">
        <n-grid x-gap="12" :cols="6">
          <n-gi>
            <n-form-item label="每行的个数，请注意如果再inline的时候">
              <n-input-number v-model:value="formSetting.cols" :min="1" :max="4"/>
            </n-form-item>
          </n-gi>
          <n-gi>
            <n-form-item label="labelWidth">
              <n-input-number v-model:value="formSetting.labelWidth" :min="1"/>
            </n-form-item>
          </n-gi>
          <n-gi>
            <n-form-item label="inline">
              <n-switch v-model:value="formSetting.inline" :checked-value="true"/>
            </n-form-item>
          </n-gi>
          <n-gi>
            <n-form-item label="labelAlign">
              <n-radio-group v-model:value="formSetting.labelAlign">
                <n-radio label="left" value="left">left</n-radio>
                <n-radio label="right" value="right">right</n-radio>
              </n-radio-group>
            </n-form-item>
          </n-gi>
          <n-gi>
            <n-form-item label="labelPlacement">
              <n-radio-group v-model:value="formSetting.labelPlacement">
                <n-radio label="left" value="left">left</n-radio>
                <n-radio label="top" value="top">top</n-radio>
              </n-radio-group>
            </n-form-item>
          </n-gi>
        </n-grid>
      </form>
    </n-card>
    <n-card :content-style="{margin:0}">
      <BaseForm :label-width="`${formSetting.labelWidth}px`" :label-align="formSetting.labelAlign"
                ref="formRef" v-model:data="formData" :items="formFiledList"
                :label-placement="formSetting.labelPlacement" :inline="formSetting.inline"
                :grid-props="{cols:formSetting.cols}" @submit="onSubmit">
      </BaseForm>
    </n-card>
</template>
<script setup lang="ts">
import BaseForm from "@/components/basic/form/index.vue"
import {BaseFormItemProps} from "@/components/basic/form/index";
import {reactive, ref} from "vue";

const formFiledList: Array<BaseFormItemProps> = [
  {field: 'name', label: '菜单名称', filedType: 'string', isSearch: false, required: true},
  {field: 'routeKey', label: '路由key全局唯一', filedType: 'string', isSearch: false, required: true},
  {field: 'orderSort', label: '显示顺序', filedType: 'number', isSearch: false, required: true},
  {field: 'isIframe', label: '是否为网页', filedType: 'switch', isSearch: false, required: true},
  {field: 'path', label: '请求路径', filedType: 'string', isSearch: false, required: true},
  {field: 'icon', label: 'icones ', filedType: 'string', isSearch: false, required: true},
  {field: 'localIcon', label: '本地icon', filedType: 'string', isSearch: false, required: true},
  {field: 'visible', label: '是否显示', filedType: 'switch', isSearch: false, required: true},
  {field: 'permission', label: '权限标识', filedType: 'string', isSearch: false, required: true},
  {field: 'type', label: '菜单类型', filedType: 'string', isSearch: false, required: true},
  {field: 'remark', label: '备注', filedType: 'string', isSearch: false, required: true},
]

const formSetting = ref({
  cols: 1,
  labelWidth: 120,
  inline: false,
  labelAlign: 'right' as any,
  labelPlacement: 'left' as any,
  showLabel: true,
})
const formData = ref()

function onSubmit(valid: boolean) {
  if (valid) {
    // @ts-ignore
    window.$message.success('验证成功')
  }
}

</script>

<style scoped>

</style>