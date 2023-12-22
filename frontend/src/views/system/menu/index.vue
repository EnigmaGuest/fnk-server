<template>
  <div>
    <eg-tree-table :loading="pageState.loading" :get-data="onGetData" :search-form-items="formFiledList"
                   :data="tableData" :columns="columns"
                   @edit="onEdit" :row-action="rowAction" @delete="onDelete"
                   title="菜单管理" desc="系统的菜单管理" @add="onAdd"/>

    <MenuDrawer v-model:show="pageState.drawerVisible" :is-update="pageState.isUpdate" :data="pageState.editData"
                @success="onGetData"
                :form-items="formFiledList"></MenuDrawer>
  </div>
</template>
<script setup lang="tsx">
import {computed, reactive, Ref, ref} from "vue";
import {BaseFormItemProps} from "@/components/basic/form/index";
import {ITableColumn} from "@/components/basic/table/index";
import MenuDrawer from "@/views/system/menu/menu-drawer.vue";
import {deleteSystemMenu, queryAllSystemMenu} from "@/service/api/system";
import {NButton} from 'naive-ui'
import {groupMenuItems} from "@/utils";


const formRef = ref()
const formData = reactive({})
const pageState = reactive({
  drawerVisible: false,
  isUpdate: false,
  editData: null,
  loading: false,

})
const tableData: Ref = ref()

const menuTypeOptions = computed(() => [
  {label: '目录', value: 'TABLE'},
  {label: '菜单', value: 'MENU'},
  {label: '按钮', value: 'BUTTON'},
])

const formFiledList = computed(() => [
  {
    field: 'rootId', label: '上级ID', filedType: 'string', slot: 'rootId', required: true, filedOptions: {
      options: [{
        id: '0',
        name: '根目录',
        children: tableData.value
      }]
    }
  },
  {field: 'name', label: '菜单名称', filedType: 'string', isSearch: true, required: true},
  {field: 'type', label: '菜单类型', filedType: 'select', required: true, filedOptions: {options: menuTypeOptions.value},},
  {field: 'orderSort', label: '显示顺序', filedType: 'number', isSearch: false, required: true},
  {field: 'icon', label: '菜单icon', filedType: 'string', isSearch: false, required: false, labelMessage: "icones中的icon名称"},
  {field: 'visible', label: '是否显示', filedType: 'switch', isSearch: true, required: true},
] as BaseFormItemProps[])


const columns: Array<ITableColumn> = [
  {field: 'id', title: 'id', type: 'string', labelWidth: 120},
  // {field: 'rootId', title: '上级ID', type: 'string',labelWidth:120},
  {field: 'name', title: '菜单名称', type: 'string'},
  {field: 'orderSort', title: '显示顺序', type: 'string'},
  {field: 'url', title: '请求URL', type: 'string'},
  {field: 'icon', title: '菜单icon', type: 'string'},
  {field: 'visible', title: '是否显示', type: 'switch'},
  {field: 'permission', title: '权限标识', type: 'string'},
  {field: 'isBlank', title: '是否新开页面', type: 'switch'},
  {field: 'type', title: '菜单类型', type: 'string'},
  {field: 'remark', title: '备注', type: 'string'},
]

const rowAction = (row: any, rowIndex: number) => {
  return (
      <NButton type={'success'} secondary onClick={() => {
        pageState.drawerVisible = true
        pageState.isUpdate = false
        pageState.editData = {
          rootId: row.id,
          orderSort: row.orderSort,
          visible: row.visible,
          permission: row.permission,
        }
        if (row.type === "TABLE") {
          pageState.editData.type = "MENU"
        } else if (row.type === "MENU") {
          pageState.editData.type = "BUTTON"
        }
      }}>添加</NButton>
  )
}

function onAdd() {
  pageState.drawerVisible = true
}

function onEdit(row: any) {
  pageState.drawerVisible = true
  pageState.isUpdate = true
  pageState.editData = row
}

async function onDelete(row: any) {
  const {error} = await deleteSystemMenu(row.id)
  if (!error) {
    await onGetData()
  }
}

async function onGetData(params?: any) {
  pageState.loading = true
  const {data, error} = await queryAllSystemMenu(params)
  if (!error) {
    tableData.value = data
    tableData.value = groupMenuItems(data)
  }
  pageState.loading = false
}




</script>

<style scoped>

</style>