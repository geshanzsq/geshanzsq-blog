import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layout'

const routes = [
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
        name: 'Index',
        component: () => import('@/views/index')
      },
      {
        path: '/category',
        name: 'Category',
        component: () => import('@/views/category')
      },
      {
        path: '/tag',
        name: 'Tag',
        component: () => import('@/views/tag')
      },
      {
        path: '/about',
        name: 'About',
        component: () => import('@/views/about')
      },
      {
        path: '/board',
        name: 'Board',
        component: () => import('@/views/board')
      },
      {
        path: '/archive',
        name: 'Archive',
        component: () => import('@/views/archive')
      },
      {
        path: '/search/:title',
        name: 'Search',
        component: () => import('@/views/search')
      },
      {
        path: '/category/:categoryName',
        name: 'SearchCategory',
        component: () => import('@/views/category/search')
      },
      {
        path: '/tag/:tagName',
        name: 'SearchTag',
        component: () => import('@/views/tag/search')
      },
      {
        path: '/author/:author',
        name: 'SearchAuthor',
        component: () => import('@/views/author')
      },
      {
        path: '/flag/:flag',
        name: 'SearchFlag',
        component: () => import('@/views/flag')
      },
      {
        path: '/article/:id',
        name: 'Article',
        component: () => import('@/views/article')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
