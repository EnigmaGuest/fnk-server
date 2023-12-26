<template>
    <div>
        <BaseTable :columns="columns" :loading="pageState.loading" :data="pageState.tableData" :get-data="onGetData"  :search-form-items="formFiledList" is-add-action title="${table.comment!}" desc="${table.comment!}" @add="onAdd" @edit="onEdit" @delete="onDelete"/>
        <entity-drawer v-model:show="pageState.drawerVisible" :is-update="pageState.isUpdate" :data="pageState.editData"
                     @success="onGetData"
                     :form-items="formFiledList"></entity-drawer>
    </div>
</template>
<script lang="ts" setup>
    import BaseTable from "@/components/basic/table/index.vue";
    import {reactive,ref} from "vue";
    import {BaseFormItemProps} from "@/components/basic/form/index";
    import {ITableColumn} from "@/components/basic/table/index";
    import EntityDrawer from "./entity-drawer.vue";
    const pageInfo = reactive({
        path: "<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace("-","/")?replace("_","/")}<#else>${table.entityPath?replace("-","/")?replace("_","/")}</#if>",
        name: "${table.comment!}",
    });
    const pageState = reactive({
        drawerVisible: false,
        isUpdate: false,
        editData: null,
        loading: false,
        tableData: [],
    })
    const formRef = ref()
    const formData = reactive({})
    const formFiledList: Array<BaseFormItemProps> = [
        <#list table.fields as field>
            <#if field.propertyType == "Boolean">
                <#assign fieldType="switch"/>
            <#elseif field.propertyType == "Integer" || field.propertyType == "Long" || field.propertyType == "Float" || field.propertyType == "Double">
                <#assign fieldType="number"/>
            <#elseif field.propertyType == "Date">
                <#assign fieldType="date"/>
            <#else>
                <#assign fieldType="string"/>
            </#if>
            {field: '${field.propertyName}', label: '${field.comment}', filedType:'${fieldType}',isSearch: false,required:true},
        </#list>
    ]

    const columns: Array<ITableColumn> = [
        <#list table.fields as field>
            <#if field.propertyType == "Boolean">
                <#assign fieldType="switch"/>
            <#elseif field.propertyType == "Integer" || field.propertyType == "Long" || field.propertyType == "Float" || field.propertyType == "Double">
                <#assign fieldType="number"/>
            <#elseif field.propertyType == "Date">
                <#assign fieldType="datetime"/>
            <#else>
                <#assign fieldType="string"/>
            </#if>
            {field: '${field.propertyName}', title: '${field.comment}', type:'${fieldType}'},
        </#list>
    ]

    /**
     * 获取数据
     * @param params
     */
    async function onGetData(params?: any) {
        pageState.loading = true
        // 接口调用，将后台的数据赋值给tableData
        pageState.tableData =  []
        pageState.loading = false
    }

    /**
     * 添加
     */
    async function onAdd() {
        pageState.drawerVisible = true
        pageState.isUpdate = false
    }

    /**
     * 编辑
     * @param row
     */
    async function onEdit(row: any) {
        pageState.drawerVisible = true
        pageState.isUpdate = true
        pageState.editData = row
    }

    /**
     * 删除
     * @param row
     * @returns {Promise<void>}
     */
    async function onDelete(row: any) {
        // @ts-ignore
        window.$message.success("删除成功")
      await onGetData()
    }
</script>