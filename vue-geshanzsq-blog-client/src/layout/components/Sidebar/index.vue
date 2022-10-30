<template>
  <div>
    <!-- 公众号 -->
    <wx-public />
    <!-- 标签 -->
    <tag />
    <!-- 广告 -->
    <advertisement />
    <!-- 好文推荐 -->
    <recommend title="好文推荐" :data="goodArticleList" />
    <!-- 点击量排行 -->
    <recommend title="点击量排行" :data="clickArticleList" />
    <!-- 分类 -->
    <category />
    <!-- 友情链接 -->
    <friendship-link />
  </div>
</template>
<script setup>
import { ref } from 'vue'

import WxPublic from './components/WxPublic'
import Tag from './components/Tag'
import Advertisement from './components/Advertisement'
import Recommend from './components/Recommend'
import Category from './components/Category'
import FriendshipLink from './components/FriendshipLink'

import { getRecommend, listClickRanking } from '@/api/blog/article'

// 好文推荐
const goodArticleList = ref([])
// 点击量排行
const clickArticleList = ref([])

async function getGoodArticleList() {
  const { data } = await getRecommend(3)
  goodArticleList.value = data
}

async function getClickArticleList() {
  const { data } = await listClickRanking()
  clickArticleList.value = data
}

getGoodArticleList()
getClickArticleList()
</script>
