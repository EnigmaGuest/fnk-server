<template>
  <!--  搜索  -->
  <div class="h-full  flex-col gap-12px">
    <n-card size="small" v-if="searchFormFields.length" :bordered="false">
      <n-collapse>
        <n-collapse-item title="搜索条件">
          <BaseForm
              :data="formData"
              :items="searchFormFields"
              inline
              ref="formDom"
              :label-align="'right'"
              :label-width="120"
              is-search
              submit-text="搜索"
              :grid-props="{ cols: props.searchCols }"
              @submit="onFormSubmit"
              @reset="onGetTableData"
          />
        </n-collapse-item>
      </n-collapse>
    </n-card>
    <n-card :bordered="false" :title="props.title" :content-style="{margin:0 }" class="flex-1 overflow-hidden">
      <template #header-extra>
        <n-flex>
          <n-button @click='emits("add")' size="small"  dashed type='primary' v-if="props.title && props.isAddAction">
            <icon-line-md:plus class='mr-4px text-20px'/>
            新增数据
          </n-button>
          <n-button size="small" @click='onGetTableData' type='primary'>
            <icon-line-md:rotate-270 class='mr-4px text-16px' :class="{ 'animate-spin': props.loading }"/>
            刷新表格
          </n-button>
        </n-flex>
      </template>
      <n-data-table :columns="tableColumns" :row-key="(rowData:any)=>rowData[props.rowKey]" :data="tableData"
                    :loading="props.loading" striped
                    class="h-full"
                    :pagination="pagination"
                    @update:page-size="onPageSizeChange"
                    @update:page="onPageChange"
                    flex-height :scroll-x="1500">
      </n-data-table>
    </n-card>
  </div>
</template>
<script setup lang="tsx">

import {computed, nextTick, onMounted, PropType, reactive, Ref, ref, unref, watch} from "vue";
import {ITableColumn} from "@/components/basic/table/index";
import {BaseFormItemProps} from "@/components/basic/form/index";
import {generateTableColumnRender} from "@/components/basic/table/render";
import BaseForm from "@/components/basic/form/index.vue"
import {DataTableColumn, NButton, NSpace, PaginationProps, NPopconfirm} from 'naive-ui';

defineOptions({name: 'BaseTable'})

const props = defineProps({
  title: {
    type: String,
    default: null
  },
  desc: {
    type: String,
    default: null
  },
  rowKey: {
    type: String,
    default: 'id'
  },
  data: {
    type: Object as PropType<any | any[]>
  },
  loading: {
    type: Boolean,
    default: false
  },
  columns: {
    type: Array as PropType<Array<ITableColumn>>,
    default: []
  },
  searchFormItems: {
    type: Array as PropType<Array<BaseFormItemProps>>,
    default: []
  },
  rowAction: Function as PropType<(row: any, rowIndex: number) => any>,
  getData: Function as PropType<({}) => any>,
  // 添加按钮
  isAddAction: {
    type: Boolean,
    default: true
  },
  // 列表数据取值key
  dataKey: {
    type: String,
    default: 'records'
  },
  // 默认操作按钮
  isAction: {
    type: Boolean,
    default: true
  },
  // 编辑按钮
  isEditAction: {
    type: Boolean,
    default: true
  },
  // 删除按钮
  isDeleteAction: {
    type: Boolean,
    default: true
  },
  // 类型 page：分页列表  list：列表
  type: {
    type: String as PropType<'page' | 'list'>,
    default: 'page'
  },
  // 搜索个数
  searchCols: {
    type: Number,
    default: 4
  },
  isSelect: {
    type: Boolean,
    default: false
  },
  // 子节点方法
  childrenAction: Function as PropType<(row: any) => any>,
})

const emits = defineEmits<{
  /**
   * 获取数据 返回data
   * @param e 事件名
   * @param data 数据
   */
  (e: 'getData', data: any): void;
  /**
   * 编辑
   * @param e 事件名
   * @param data 数据
   */
  (e: 'edit', data: any): void;
  /**
   * 删除
   * @param e 事件名
   * @param data 数据
   */
  (e: 'delete', data: any): void;
  /**
   * 新增
   * @param e 事件名
   */
  (e: 'add'): void;
}>()

const formData = reactive({});
const pagination = reactive({
  page: parseInt(props.data?.current ?? 1),
  pageSize: 10,
  pageSizes: [10, 20, 30, 40, 50],
  showSizePicker: true
});

const isLoaded = computed(() => {
  // 判断childrenAction 是否存在
  return !!props.childrenAction;
})
const tableColumns = computed(() => {

  const columns = props.columns.map((item: ITableColumn) => {
    return {
      key: item.field,
      title: item.title,
      align: 'center',
      titleAlign: 'center',
      tree: item.tree ?? true,
      render: item.render ?? generateTableColumnRender(item),
      width: item.labelWidth ?? 100,
      fixed: item.fixed ?? false,
      ...item
    } as any
  })
  if (props.isAction) {
    columns.push({
      title: defaultAction.title,
      key: defaultAction.field,
      align: 'center',
      titleAlign: 'center',
      render: defaultAction.render,
      width: defaultAction.labelWidth ?? 100,
      ...defaultAction
    } as any)
  }
  if (props.isSelect) {
    return [{
      type: 'selection',
    }, ...columns]
  }
  return columns
})
const defaultAction = reactive<ITableColumn>({
  field: 'actions',
  fixed: 'right',
  title: '操作',
  labelWidth: 140,
  render: (row: any, rowIndex: number) => {
    return (
        <NSpace size="small" justify="center">
          {props.rowAction?.(row, rowIndex)}
          {props.isEditAction && (
              <NButton

                  onClick={() => emits('edit', row)}
                  secondary
                  type="info"
              >
                编辑
              </NButton>
          )}
          {props.isDeleteAction && (
              <NPopconfirm
                  onPositiveClick={() => emits('delete', row)}
                  v-slots={{
                    trigger: () => {
                      return (
                          <NButton secondary type="error">
                            删除
                          </NButton>
                      );
                    },
                  }}
              >
                确认删除吗？
              </NPopconfirm>
          )}
        </NSpace>
    );
  }
});

const tableData = computed(() => {
      if (!props.data) {
        return []
      }
      if (props.data instanceof Array) {
        return props.data
      } else {
        return props.data[props.dataKey]
      }
    }
)

const searchFormFields = computed(() => {
  if (!props.searchFormItems) {
    return []
  }
  const items = []
  Object.assign(items, props.searchFormItems)
  return items.filter(item => item?.isSearch).map(item => {
    if (item?.filedOptions?.disabled) {
      item.filedOptions.disabled = false
    }
    return item;
  });
})

const formDom = ref()
// 获取dom的高度

function onPageChange(page: number) {
  pagination.page = page;
  onGetTableData();
}

function onPageSizeChange(pageSize: number) {
  pagination.pageSize = pageSize;
  onGetTableData();
}

function onFormSubmit(state: boolean) {
  if (state) {
    onGetTableData()
  }
}

function onGetTableData() {
  if (props.type === 'page') {
    emits('getData', {
      page: pagination.page,
      pageSize: pagination.pageSize,
      ...unref(formData)
    })
    props.getData?.({
      page: pagination.page,
      pageSize: pagination.pageSize,
      ...unref(formData)
    })
  } else {
    emits('getData', unref(formData))
    props.getData?.(unref(formData))
  }
}


onMounted(() => {
  // 获取当前屏幕高度
  onGetTableData()
})


</script>

<style scoped>

</style>