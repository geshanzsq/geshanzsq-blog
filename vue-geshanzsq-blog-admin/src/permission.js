import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import router from '@/router'
import store from '@/store'
import { whileList } from '@/config/setting.config'
import { isHttp } from '@/utils/validate'

NProgress.configure({ showSpinner: false })

router.beforeEach(async (to, from, next) => {
  NProgress.start()
  const token = store.getters['user/token']
  // 已登录
  if (token) {
    // 如果已登录又访问登录页，直接跳转到首页
    if (to.path === '/login') {
      next('/')
    } else {
      if (store.getters['user/username'].length === 0) {
        try {
          // 判断当前用户是否已拉取完用户信息
          await store.dispatch('user/getUserInfo')
          // 获取字典数据
          await store.dispatch('dictionary/getAllDictionaryInfo')
          // 根据 roles 权限生成可访问的路由表
          await store.dispatch('permission/generateRoutes')
          // 根据roles权限生成可访问的路由表
          const accessRoutes = store.getters['permission/addRoutes']
          accessRoutes.forEach((route) => {
            // 动态添加可访问路由表
            if (!isHttp(route.path)) {
              router.addRoute(route)
            }
          })
          // hack方法 确保addRoutes已完成
          next({ ...to, replace: true })
        } catch (error) {
          console.log(error)
          NProgress.done()
          await store.dispatch('user/logout')
          next(`/login?redirect=${to.fullPath}`)
        }
      } else {
        next()
      }
    }
  } else {
    // 未登录
    if (whileList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      // 否则全部重定向到登录页，判断是否为首页，如果是首页，去除 redirect 参数
      if (to.path === '/') {
        next('/login')
      } else {
        next(`/login?redirect=${to.fullPath}`)
      }
    }
  }
  NProgress.done()
})

router.afterEach(() => {
  NProgress.done()
})
