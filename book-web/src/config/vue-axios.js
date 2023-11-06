import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { Notification } from 'element-ui';
Vue.use(VueAxios, axios)
// import store from '@/store'  // 我此项目没有用到vuex，所以vuex代码的都注释了，需要的自己打开使用

// import {ACCESS_TOKEN} from '@/store/mutation-types'

// 创建 axios 实例
const requests = axios.create({
    // 基础url,如果是多环境配置这样写
    // baseURL: process.env.VUE_APP_API,
    baseURL: 'http://127.0.0.1:8888',
    timeout: 6000 // 请求超时时间
})

// 错误处理函数
const err = (error) => {
    return Promise.reject(error)
}

// request interceptor(请求拦截器)
requests.interceptors.request.use(config => {
    //   const token = Vue.ls.get(ACCESS_TOKEN)
    const token = localStorage.getItem('token')
    if (token) {
        config.headers['Token'] = token
    }
    return config
}, err)

// response interceptor（响应拦截器）
requests.interceptors.response.use((response) => {
    const res = response.data
    if (res.code === 20004) {
        localStorage.removeItem('token');
        window.location.href = '/'
    }
    let mes = res.message;
    if (mes) {
        Notification({
            title: mes,
            type: res.success ? "success" : "warning",
            position: 'bottom-right'
        });
    }
    return res
}, err)

export default requests
