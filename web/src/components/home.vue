<template>
  <div class="page">
    <a-layout class="layout">
      <a-layout-header class="layout-header">
        <div class="header-box">
          <a-space>
            <a-select v-model="currentDataBase" placeholder="选择数据库" style="width: 180px;" @change="getTables">
              <a-option v-for="(item, index) in databaseList" :key="index">{{ item }}</a-option>
            </a-select>
            <a-button @click="dataSourceDrawerVisible = true" type="primary">数据源</a-button>
          </a-space>
        </div>
      </a-layout-header>
      <a-layout-content>
        <a-row style="width: 100%; height: 100%;" :gutter="10">
          <a-col :span="7" style="width: 100%; height: 100%;">
            <div class="left-box">
              <div class="item" v-for="(table, index) in tableList" :key="index" :class="{ 'active': table === currentTable }"
                   @click="onTableChange(table)">{{ table }}
              </div>
            </div>
          </a-col>
          <a-col :span="17" style="width: 100%; height: 100%;">
            <div class="main-box">
              <a-tabs default-active-key="1" v-if="currentTable">
                <a-tab-pane key="1" title="Config">
                  <a-form :model="generateConfig" style="width: 100%;" direction="vertical">

                    <a-form-item field="author" label="作者">
                      <a-input v-model="generateConfig.author" style="width: 300px"></a-input>
                    </a-form-item>
                    <a-form-item field="classTopMetadata" label="类顶部描述信息">
                      <a-textarea v-model="generateConfig.classTopMetadata" style="width: 300px"></a-textarea>
                    </a-form-item>
                    <a-form-item field="projectName" label="项目名">
                      <a-input v-model="generateConfig.projectName" style="width: 300px"></a-input>
                    </a-form-item>

                    <a-divider orientation="left">实体类配置</a-divider>
                    <a-form-item field="entityClassName" label="Java实体类名">
                      <a-input v-model="generateConfig.entityClassName" style="width: 300px"></a-input>
                    </a-form-item>
                    <a-form-item field="tableName" label="表名">
                      <a-input v-model="generateConfig.tableName" style="width: 300px" readonly></a-input>
                    </a-form-item>
                    <a-form-item field="entityAttributeNameType" label="属性名格式">
                      <a-radio-group v-model="generateConfig.entityAttributeNameType">
                        <a-radio value="tfs">驼峰式</a-radio>
                        <a-radio value="ym">原名</a-radio>
                      </a-radio-group>
                    </a-form-item>
                    <a-form-item field="entityClassDoc" label="实体类JavaDoc">
                      <a-input v-model="generateConfig.entityClassDoc" style="width: 300px"></a-input>
                    </a-form-item>
                    <a-form-item field="entityPackage" label="实体类包名">
                      <a-input v-model="generateConfig.entityPackage" style="width: 300px"></a-input>
                    </a-form-item>
                    <a-divider/>
                    <a-form-item>
                      <a-button type="primary" @click="onGenerateCode">生成代码</a-button>
                    </a-form-item>
                  </a-form>
                </a-tab-pane>
                <a-tab-pane key="2" title="Entity">
                  <div class="code-box">
                    <a-textarea readonly v-model="codeData.entity" style="width: 100%; height: 100%"></a-textarea>
                  </div>
                </a-tab-pane>
                <a-tab-pane key="3" title="Mapper">
                  Content of Tab Panel 3
                </a-tab-pane>
                <a-tab-pane key="4" title="Service">
                  Content of Tab Panel 3
                </a-tab-pane>
                <a-tab-pane key="5" title="ServiceImpl">
                  Content of Tab Panel 3
                </a-tab-pane>
                <a-tab-pane key="6" title="Controller">
                  Content of Tab Panel 3
                </a-tab-pane>
              </a-tabs>
            </div>
          </a-col>
        </a-row>
      </a-layout-content>
    </a-layout>
  </div>

  <DatasourceDrawer/>

</template>

<script setup>
import {IconList} from '@arco-design/web-vue/es/icon';
import MonacoEditor from 'monaco-editor-vue3';
import DatasourceDrawer from './datasource.vue';
import {onMounted, ref, nextTick, provide, reactive} from "vue";
import {get, post} from '../common/request';
import {Message} from "@arco-design/web-vue";

// 当前表和当前库
const currentTable = ref('');
const currentDataBase = ref('');

const generateConfig = reactive({
  // 作者
  author: 'riyan6',
  // 类顶部描述信息
  classTopMetadata: '',
  // 项目名称
  projectName: '',
  // Java类名
  entityClassName: '',
  // 表名
  tableName: '',
  // 实体类属性名格式
  entityAttributeNameType: 'ym',
  // 实体类Javadoc
  entityClassDoc: '',
  // 实体类包名
  entityPackage: ''
})

// 控制数据源抽屉显示
const dataSourceDrawerVisible = ref(false);
provide('dataSourceDrawerVisible', dataSourceDrawerVisible)

// 数据库列表
const databaseList = ref([]);
const tableList = ref([]);
const codeData = ref({
  entity: ''
})

onMounted(() => {
  nextTick(async () => {
    try {
      // 请求数据库
      databaseList.value = await get('/api/databases', {})
      if (databaseList.value.length === 0) return
      currentDataBase.value = databaseList.value[0];
      // 请求表格
      getTables();
    } catch (e) {
      console.error(e)
    }
  })
})

// 请求表格数据
const getTables = async () => {
  currentTable.value = '';
  tableList.value = await get(`/api/databases/${currentDataBase.value}/tables`, {});
}

// 生成代码
const onGenerateCode = async () => {
  try {
    const res = await post('/api/code/gen', generateConfig)
    codeData.value = res;
    Message.success('生成代码成功！')
  } catch (e) {
    Message.error('处理失败')
  }
}

// 数据库发生改变
const onTableChange = (table) => {
  currentTable.value = table;
  generateConfig.tableName = `${currentDataBase.value}.${table}`;
  generateConfig.entityClassName = table;
}

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

      .header-box {
        position: relative;
        width: 100%;
        height: 50px;
        display: flex;
        align-items: center;
        justify-content: center;
      }
    }

    .left-box {
      position: relative;
      width: 100%;
      height: 100%;
      overflow-y: auto;
      //background-color: #f2f3f5;
      //border-right: 1px dotted rgb(var(--primary-6));

      .item {
        position: relative;
        width: 92%;
        padding-top: 5px;
        padding-bottom: 5px;
        margin-left: 4%;
        border-radius: 4px;
        color: black;
      }

      .active {
        background-color: rgb(var(--primary-6));
        color: white;
      }
    }

    .main-box {
      position: relative;
      width: 100%;
      height: 100%;
      //background-color: #f2f3f5;

      .code-box {
        position: relative;
        width: 100%;
        height: calc(100vh - 50px - 40px - 16px - 16px);
      }
    }
  }
}
</style>