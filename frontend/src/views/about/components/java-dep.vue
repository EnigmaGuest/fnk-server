<template>
  <n-card title="JAVA开发依赖" :bordered="false" size="small" class="rounded-8px shadow-sm">
    <n-descriptions label-placement="left" bordered size="small">
      <n-descriptions-item v-for="item in javaDevDependencies" :key="item.name" :label="item.name">
        {{ item.version }}
      </n-descriptions-item>
    </n-descriptions>
  </n-card>
</template>
<script setup lang="ts">

const javaDevDependencies = [
  {
    name: 'spring-boot-starter',
    version: '3.2.0',
  },
  {
    name: 'java',
    version: '17',
  },
  {
    name: 'mybatis-plus',
    version: '3.5.4.1'
  },
  {
    name: 'sa-token',
    version: '1.34.0'
  },
  {
    name: 'hutool',
    version: '5.8.20'
  },
  {
    name: 'lombok',
    version: '1.18.22'
  },
  {
    name: 'mysql-connector',
    version: '8.0.33'
  },
  {
    name: 'swagger',
    version: '3.0.0'
  },
  {
    name: 'mybatis-plus-generate',
    version:'3.5.4.1'
  },
  {
    name: 'maven-dependency-plugin',
    version: '3.3.0'
  },
  {
    name: 'com.google.zxing',
    version: '3.3.3'
  },
  {
    name: 'knife4j',
    version: '4.4.0'
  },
  {
    name: 'jackson',
    version: '2.13.2'
  },
  {
    name: 'fnk-service',
    version: '1.0.0'
  },
  {
    name: 'springdoc',
    version: '2.3.0'
  },
  {
    name: 'mysql',
    version: '5.7'
  }

]

</script>

<style scoped>

</style>