<template>
  <article-search :author="author" search-flag="作者" />
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import ArticleSearch from '@/views/search/components/ArticleSearch'

const router = useRouter()

const author = ref(router.currentRoute.value.params.author)
</script>
<style lang="scss" scoped></style>
