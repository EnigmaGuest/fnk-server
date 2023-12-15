<template>
    <model-curd v-model="pageModel" :info="pageInfo"></model-curd>
</template>
<script lang="ts" setup>
    import ModelCurd from "@/components/ModelCurd/index.vue";
    import {reactive} from "vue";
    import {PageTableSearchModel} from "@/services/base";

    const pageInfo = reactive({
        path: "<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace("-","/")?replace("_","/")}<#else>${table.entityPath?replace("-","/")?replace("_","/")}</#if>",
        name: "${table.comment!}",
    });

    const pageModel: Array<PageTableSearchModel> = [
        <#list table.fields as field>
            <#if field.propertyType == "Boolean">
                <#assign fieldType="boolean"/>
            <#elseif field.propertyType == "Integer" || field.propertyType == "Long" || field.propertyType == "Float" || field.propertyType == "Double">
                <#assign fieldType="number"/>
            <#elseif field.propertyType == "Date">
                <#assign fieldType="date"/>
            <#else>
                <#assign fieldType="string"/>
            </#if>
            {field: '${field.propertyName}', label: '${field.comment}', type:'${fieldType}'},
        </#list>
    ]
</script>