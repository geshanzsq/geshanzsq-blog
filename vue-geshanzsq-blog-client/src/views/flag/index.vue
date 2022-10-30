<template>
  <article-search :flag="flag" search-flag="标识" />
</template>
<script setup>
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'

import ArticleSearch from '@/views/search/components/ArticleSearch'

const router = useRouter()

const flagName = ref(router.currentRoute.value.params.flag)

const flag = computed(() => {
  if (flagName.value === '原创') {
    return 1
  } else {
    return undefined
  }
})
</script>
<style lang="scss" scoped></style>
