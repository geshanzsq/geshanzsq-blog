<template>
  <div class="search">
    <div class="search-input">
      <el-input
        v-show="showSearch"
        size="large"
        v-model="searchContent"
        placeholder="想搜什么呢"
        @keyup.enter="handleSearch"
      />
    </div>
    <div class="search-icon">
      <el-icon @click="handleSearch"><Search /></el-icon>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 是否显示搜索框
const showSearch = ref(false)
// 搜索内容
const searchContent = ref('')

/**
 * 搜索框点击事件
 */
function handleSearch() {
  if (!searchContent.value) {
    showSearch.value = !showSearch.value
    return
  }
  router.push('/search/' + searchContent.value)
}
</script>
<style lang="scss" scoped>
.search {
  cursor: pointer;

  .search-input {
    float: left;
    padding-right: 15px;
  }
  .search-icon {
    float: right;
    font-size: 20px;
    color: #bdbdbd;
    padding-top: 5px;
  }
}
</style>
