<template>
  <app-container class="p-12px">

    <n-grid :item-responsive="true" :x-gap="16" :y-gap="16">
      <n-grid-item span=":24 640:24 1024:24">
        <n-card class="" title="本地SVG Icon使用">
          <n-space vertical>
            <p>本地svg使用vite插件进行自动导入</p>
            <p>目录在env配置中：VITE_ICON_LOCAL_PATH</p>
            <p>使用方式：标签eg-icon local-icon="logo-fill" 或者 标签icon-local-* </p>
            <p>示例：</p>
            <n-space>
              <eg-icon local-icon="logo-fill" class="text-24px"/>
              <eg-icon local-icon="logo" class="text-24px text-primary"/>
            </n-space>
          </n-space>
        </n-card>
      </n-grid-item>
      <n-grid-item span=":24 640:24 1024:24">
        <n-card class="" title="iconify使用">
          <n-space vertical>
            <p>可以说很好用的icon，你完全不怕找不到icon了</p>
            <p>iconify地址：<a href="https://icones.js.org/" class="text-primary">https://icones.js.org/</a></p>
            <p>进入网址复制需要的icon name然后使用标签icon-name即可、或者使用组件eg-icon
              icon='name'(推荐)。注name是你复制的name！</p>
            <p>tips：找logo在网站中搜索logos:*</p>
            <p>示例：</p>
            <n-space>
              <eg-icon icon="logos:github-icon" class="text-24px"/>
              <eg-icon icon="logos:google-icon" class="text-24px text-primary"/>
              <eg-icon icon="line-md:watch-loop" class="text-24px text-primary"></eg-icon>
              <icon-line-md:reddit-loop class="text-24px text-primary"/>
            </n-space>
          </n-space>
        </n-card>
      </n-grid-item>
    </n-grid>
  </app-container>
</template>
<script setup lang="ts">

</script>

<style scoped>

</style>