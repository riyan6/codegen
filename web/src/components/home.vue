<template>
  <div class="page">
    <a-layout class="layout">
      <a-layout-header class="layout-header">
        <a-page-header
            title="CodeGen"
        >
          <template #back-icon>
            <a-button shape="circle">
              <IconCode :size="24"/>
            </a-button>
          </template>
          <template #extra>
            <a-button-group>
              <a-button @click="dataSourceDrawerVisible = true" type="primary">数据源</a-button>
            </a-button-group>
          </template>
        </a-page-header>
      </a-layout-header>
      <a-layout-content>
        <div style="width: 100%; height: 100%; display: flex">
          <div style="width: 400px; height: 100%;">
          </div>
          <div style="flex: 1; height: 100%;">
            <MonacoEditor
                theme="vs"
                :options="{colorDecorators: true,lineHeight: 24,tabSize: 2, language: 'java', theme: 'vs-dark'}"
                language="javascript"
                width="100%"
                height="100%"
                v-model:value="codeContent"
            ></MonacoEditor>
          </div>
        </div>
      </a-layout-content>
    </a-layout>
  </div>

  <DatasourceDrawer />

</template>

<script setup>
import {IconCode} from '@arco-design/web-vue/es/icon';
import MonacoEditor from 'monaco-editor-vue3';
import DatasourceDrawer from './datasource.vue';
import {onMounted, ref, nextTick, provide} from "vue";
import { get } from '../common/request';

const contentSize = ref(0.3);
const codeContent = ref('// 等待生成代码...');


const dataSourceDrawerVisible = ref(false);
provide('dataSourceDrawerVisible', dataSourceDrawerVisible)

onMounted(() => {
    nextTick(async() => {
      try {
        const res = await get('/api/databases', {})
        console.log(res)
      } catch (e) {
        console.log(e)
      }
    })
})

</script>

<style scoped>
.page {
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden;

  .layout {
    width: 100vw;
    height: 100vh;

    .layout-header {
      position: relative;
      width: 100vw;
      background-color: white;
      border-bottom: 4px solid #e5e6ea;
    }

    .grid {
      width: 100%;
      height: 100%;
    }
  }
}
</style>