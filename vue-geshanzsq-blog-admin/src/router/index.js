import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layout'

/**
 * 公共路由
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login')
  },
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: '/',
        component: () => import('@/views/index'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },

  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/403',
    component: () => import('@/views/error/403'),
    hidden: true
  }
]

/**
 * 动态路由，基于用户权限动态去加载
 */
export const dynamicRoutes = [
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:role:authUser'],
    children: [
      {
        path: 'user/:id',
        component: () => import('@/views/system/role/authUser'),
        name: 'AuthUser',
        meta: { title: '分配用户角色', activeMenu: '/system/role' }
      }
    ]
  },
  {
    path: '/system/api/data/',
    component: Layout,
    hidden: true,
    permissions: ['system:api:page'],
    children: [
      {
        path: ':id',
        component: () => import('@/views/system/api'),
        name: 'APIData',
        meta: { title: 'API 接口数据', activeMenu: '/system/api' }
      }
    ]
  },
  {
    path: '/system/dictionary/data',
    component: Layout,
    hidden: true,
    permissions: ['system:dictionary:data:page'],
    children: [
      {
        path: ':id',
        component: () => import('@/views/system/dictionary/data'),
        name: 'DictionaryData',
        meta: { title: '字典数据', activeMenu: '/system/dictionary' }
      }
    ]
  },
  {
    path: '/blog/picture/data/',
    component: Layout,
    hidden: true,
    permissions: ['blog:picture:page'],
    children: [
      {
        path: ':id',
        component: () => import('@/views/blog/picture'),
        name: 'BlogPictureData',
        meta: { title: '图片管理', activeMenu: '/blog/picture/category' }
      }
    ]
  },
  {
    path: '/blog/article/edit/',
    component: Layout,
    hidden: true,
    permissions: ['blog:article:update'],
    children: [
      {
        path: ':id',
        component: () => import('@/views/blog/article/edit'),
        name: 'BlogArticleEdit',
        meta: { title: '编辑文章', activeMenu: '/blog/article' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: constantRoutes
})

export default router
