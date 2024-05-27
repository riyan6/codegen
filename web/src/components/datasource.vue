<template>
  <!-- 数据源设置 -->
  <a-drawer :width="340" :visible="dataSourceDrawerVisible" @ok="onSubmit" @cancel="dataSourceDrawerVisible = false"
            unmountOnClose>
    <template #title>
      数据源配置
    </template>
    <div>
      <a-form :model="form" style="width: 100%;">
        <a-form-item field="host" label="主机">
          <a-input v-model="form.host" placeholder="ipv4地址"/>
        </a-form-item>
        <a-form-item field="port" label="端口号">
          <a-input v-model="form.port" placeholder="3306"/>
        </a-form-item>
        <a-form-item field="username" label="账号">
          <a-input v-model="form.username" placeholder="root"/>
        </a-form-item>
        <a-form-item field="password" label="密码">
          <a-input v-model="form.password" placeholder=""/>
        </a-form-item>
      </a-form>
    </div>
  </a-drawer>

</template>

<script setup>

import {reactive, inject, ref, onMounted} from "vue";

const dataSourceDrawerVisible = inject("dataSourceDrawerVisible", ref(false));
const form = reactive({
  host: '',
  port: '',
  username: '',
  password: ''
});

onMounted(() => {
  form.host = sessionStorage.getItem('dataSourceHost')
  form.port = sessionStorage.getItem('dataSourcePort')
  form.username = sessionStorage.getItem('dataSourceUsername')
  form.password = sessionStorage.getItem('dataSourcePassword')
})

const onSubmit = () => {
  sessionStorage.setItem('dataSourceHost', form.host)
  sessionStorage.setItem('dataSourcePort', form.port)
  sessionStorage.setItem('dataSourceUsername', form.username)
  sessionStorage.setItem('dataSourcePassword', form.password)
  dataSourceDrawerVisible.value = false;
}

</script>

<style scoped></style>