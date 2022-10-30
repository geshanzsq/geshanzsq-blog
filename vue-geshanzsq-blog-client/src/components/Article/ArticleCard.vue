<template>
  <article-card-mobile v-if="isMobile" :show-sticky="showSticky" />
  <article-card-pc v-else :show-sticky="showSticky" />
</template>
<script setup>
import { computed } from 'vue'
import { useStore } from 'vuex'
import ArticleCardMobile from './ArticleCardMobile'
import ArticleCardPc from './ArticleCardPc'

defineProps({
  showSticky: {
    type: Boolean,
    default: true
  }
})

const store = useStore()

const isMobile = computed(() => {
  return store.getters['app/isMobile']
})
</script>
