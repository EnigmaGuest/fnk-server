<template>
<base-hover-tip text="github地址">
  <div class="flex text-24px cursor-pointer" @click="openGithub">
    <icon-logos:github-icon />
  </div>
</base-hover-tip>
</template>
<script setup lang="ts">

let githubUrl = "https://github.com/EnigmaGuest/fnk-server"

function openGithub() {
  window.open(githubUrl)
}
</script>

<style scoped>

</style>