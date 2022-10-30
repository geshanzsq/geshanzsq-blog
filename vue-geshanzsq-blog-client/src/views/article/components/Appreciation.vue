<template>
  <el-dialog
    :width="isMobile ? '100%' : '50%'"
    v-model="visible"
    title="感谢你的有心赞赏，关注我的公众号就好了"
    append-to-body
    :before-close="close"
  >
    <el-image
      class="image"
      fit="fill"
      :src="require('@/assets/images/public-code/weixin-geshanzsq.jpg')"
    />
    <el-image
      class="image"
      fit="fill"
      :src="require('@/assets/images/public-code/weixin-xgzskills.jpg')"
    />
  </el-dialog>
</template>
<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const visible = ref(false)

const isMobile = computed(() => {
  return store.getters['app/isMobile']
})

/**
 * 显示弹窗
 */
function show() {
  visible.value = true
}

/**
 * 关闭
 */
function close() {
  visible.value = false
}

defineExpose({
  show
})
</script>
<style lang="scss" scoped>
.image {
  width: 50%;
}
</style>
