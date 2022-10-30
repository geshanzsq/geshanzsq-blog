<template>
  <article-search :tag-name="tagName" search-flag="标签" />
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

import ArticleSearch from '@/views/search/components/ArticleSearch'

const router = useRouter()

const tagName = ref(router.currentRoute.value.params.tagName)
</script>
<style lang="scss" scoped></style>
