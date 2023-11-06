// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

// element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// axios
import requests from '@/config/vue-axios'
Vue.prototype.$requests = requests;

Vue.config.productionTip = false


// 导航守卫
router.beforeEach((to, from, next) => {
  // 获取token
  const token = localStorage.getItem('token');
  if (!token && to.name !== 'Login') {
    // 当页面不在login和不存在token的情况下跳转
    next({ name: 'Login' })
  } else if (token && to.name === 'Login') {
    // 当页面在login和存在token的情况下跳转 (自动登录的效果)
    next({ name: 'Home' })
  } else {
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
