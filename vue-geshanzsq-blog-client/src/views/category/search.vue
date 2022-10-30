<template>
  <article-search :category-name="categoryName" search-flag="分类" />
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import ArticleSearch from '@/views/search/components/ArticleSearch'

const router = useRouter()

const categoryName = ref(router.currentRoute.value.params.categoryName)
</script>
<style lang="scss" scoped></style>
