<template>
  <article-search :title="title" search-flag="关键字" />
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import ArticleSearch from './components/ArticleSearch'

const router = useRouter()

const title = ref(router.currentRoute.value.params.title)
</script>
<style lang="scss" scoped></style>
