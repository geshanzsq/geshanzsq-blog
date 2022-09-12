<template>
  <write :id="id" />
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import write from './write'

const router = useRouter()

const id = ref(router.currentRoute.value.params.id)
</script>
