import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: (resolve) => require(['@/views/LoginAndRegister.vue'], resolve)
    },
    {
      path: '/index',
      name: 'Index',
      component: (resolve) => require(['@/views/Index.vue'], resolve),
      children:[
        {
          path: '/home',
          name: 'Home',
          component: (resolve) => require(['@/views/Home.vue'], resolve)
        },
        {
          path: '/user',
          name: 'User',
          component: (resolve) => require(['@/views/User.vue'], resolve)
        },
        {
          path: '/book-list',
          name: 'BookList',
          component: (resolve) => require(['@/views/BookList.vue'], resolve)
        },
        {
          path: '/user-info',
          name: 'UserInfo',
          component: (resolve) => require(['@/views/UserInfo.vue'], resolve)
        },
      ]
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
