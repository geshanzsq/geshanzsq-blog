<template>
  <div class="app-container">
    <aside>
      首先在博客管理的文章管理中，设置推荐等级。然后通过拖动列表，调节推荐博客的显示的优先级<br />
      一级推荐：轮播图<br />
      二级推荐：轮播图右侧<br />
      三级推荐：右侧好文推荐<br />
      四级推荐：目前没什么用，可用于后续扩展
    </aside>
    <el-row :gutter="6">
      <el-col :span="6">
        <draggable
          header-title="一级推荐"
          header-background-color="#409eff"
          :data-list="oneRecommendList"
          v-loading="oneLoading"
          @end="handleSort"
        />
      </el-col>
      <el-col :span="6">
        <draggable
          header-title="二级推荐"
          header-background-color="#E6A23C"
          :data-list="twoRecommendList"
          v-loading="twoLoading"
          @end="handleSort"
        />
      </el-col>
      <el-col :span="6">
        <draggable
          header-title="三级推荐"
          header-background-color="#F56C6C"
          :data-list="thirdRecommendList"
          v-loading="thirdLoading"
          @end="handleSort"
        />
      </el-col>
      <el-col :span="6">
        <draggable
          header-title="四级推荐"
          header-background-color="#67C23A"
          :data-list="fourthRecommendList"
          v-loading="fourthLoading"
          @end="handleSort"
        />
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'
import Draggable from './components/RecommendDraggable'

import { list, updateSort } from '@/api/blog/recommend'

const { proxy } = getCurrentInstance()

const oneRecommendList = ref([])
const oneLoading = ref(true)
const twoRecommendList = ref([])
const twoLoading = ref(true)
const thirdRecommendList = ref([])
const thirdLoading = ref(true)
const fourthRecommendList = ref([])
const fourthLoading = ref(true)

/**
 * 获取一级推荐数据
 */
async function getOneRecommendList() {
  oneLoading.value = true
  const { data } = await list({
    recommendLevel: 1
  })
  oneRecommendList.value = data
  oneLoading.value = false
}

/**
 * 获取二级推荐数据
 */
async function getTwoRecommendList() {
  twoLoading.value = true
  const { data } = await list({
    recommendLevel: 2
  })
  twoRecommendList.value = data
  twoLoading.value = false
}

/**
 * 获取三级推荐数据
 */
async function getThirdRecommendList() {
  thirdLoading.value = true
  const { data } = await list({
    recommendLevel: 3
  })
  thirdRecommendList.value = data
  thirdLoading.value = false
}

/**
 * 获取四级推荐数据
 */
async function getFourthRecommendList() {
  fourthLoading.value = true
  const { data } = await list({
    recommendLevel: 4
  })
  fourthRecommendList.value = data
  fourthLoading.value = false
}

/**
 * 完成拖动排序
 */
async function handleSort(dataList) {
  const data = []
  for (let s = 0; s < dataList.length; s++) {
    const recommend = dataList[s]
    data.push({
      id: recommend.id,
      sort: s + 1
    })
  }
  if (data.length === 0) {
    return
  }
  await updateSort(data)
  proxy.$modal.msgSuccess('拖动排序成功')
}

getOneRecommendList()
getTwoRecommendList()
getThirdRecommendList()
getFourthRecommendList()
</script>
<style lang="scss" scoped></style>
