<template>
  <app-container height="100vh">
    <div class="login">
      <div class="absolute right-10 top-10">
        <dark-switch/>
      </div>
      <div class="login-item login-banner">
        <icon-local-login-bg class="text-500px text-primary  px-20px"/>
      </div>
      <div class="login-item px-4 w-360px ">
        <PageLogo/>
        <p class="text-20px my-4">登录</p>
        <n-tabs type="segment">
          <n-tab-pane name="chap1" tab="账号密码">
            <transition name="fade-slide" mode="out-in" appear>
              <LoginPassword/>
            </transition>
          </n-tab-pane>
          <n-tab-pane name="chap2" tab="扫码登录">
            <login-qrcode/>
          </n-tab-pane>
        </n-tabs>
      </div>
    </div>
  </app-container>
</template>
<script setup lang="ts">

import {useAuthStore} from "@/store";
import {useRouter} from "vue-router";
import {router as globalRouter} from "@/router";
import PageLogo from "@/layouts/components/logo/index.vue";
import DarkSwitch from "@/components/common/dark-switch.vue";
import LoginPassword from "@/views/login/components/login-password.vue";
import LoginQrcode from "@/views/login/components/login-qrcode.vue";

const ua = useAuthStore()

const router = useRouter()

</script>

<style scoped lang="scss">
.login {
  box-sizing: border-box;
  display: flex;
  flex-flow: row wrap;
  width: 100%;
  height: 100vh;
  align-items: center;
  justify-content: center;

  &-item {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  &--left {
    background: linear-gradient(140.02deg, #f9f9fa, #f7f8ff);
  }
}

// 当屏幕小于800px时，左侧隐藏
@media screen and (max-width: 1000px) {
  .login-banner{
    display: none;
  }
}
</style>