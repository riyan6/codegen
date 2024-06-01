// http.js
import axios from 'axios';
import {reactive, readonly} from 'vue';
import {Message} from '@arco-design/web-vue';

// 创建一个响应式状态对象
export const state = reactive({
    loading: false,
});

// 创建 Axios 实例
const instance = axios.create({
    // 配置项，可根据实际需要修改
    baseURL: 'http://localhost:3000',
    // baseURL: '/',
    timeout: 5000,
    headers: {
        'x-datasource-host': sessionStorage.getItem('dataSourceHost'),
        'x-datasource-port': sessionStorage.getItem('dataSourcePort'),
        'x-datasource-username': sessionStorage.getItem('dataSourceUsername'),
        'x-datasource-password': sessionStorage.getItem('dataSourcePassword'),
    }
});

// 请求拦截器
instance.interceptors.request.use(
    config => {
        state.loading = true;
        return config;
    },
    error => {
        state.loading = false;
        // @ts-ignore
        return Promise.reject(error);
    }
);

// 响应拦截器
instance.interceptors.response.use(
    response => {
        state.loading = false;
        return response.data;
    },
    error => {
        console.log(error)
        if (error.name === "AxiosError" && error.message.indexOf('timeout') !== -1) {
            Message.error('请求超时，请刷新重试')
            return error
        }
        const res = error.response
        if (res?.status === 500) {
            const message = res.data.message;
            Message.error(`服务器异常：${message}`)
        }

        state.loading = false;
        // @ts-ignore
        return res.data;
    }
);

// 封装 get 请求
export const get = (url, params = {}) => {
    return instance.get(url, {params});
}

// 封装 post 请求
export const post = (url, data = {}) => {
    return instance.post(url, data);
}

// 封装 put 请求
export const put = (url, data = {}) => {
    return instance.put(url, data);
}

// 封装 delete 请求
export const del = (url) => {
    return instance.delete(url);
}