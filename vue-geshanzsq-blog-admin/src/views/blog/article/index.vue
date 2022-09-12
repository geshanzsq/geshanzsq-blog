<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="标题描述" prop="articleTitleDescription">
        <el-input
          v-model="queryParams.articleTitleDescription"
          placeholder="请输入标题或描述"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="categoryId">
        <el-select
          style="width: 240px"
          v-model="queryParams.categoryId"
          placeholder="所属分类"
          @change="handleQuery"
          clearable
        >
          <el-option
            v-for="(category, index) in categoryList"
            :key="index"
            :label="category.categoryName"
            :value="category.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="标签" prop="tagIds">
        <el-select
          style="width: 240px"
          v-model="queryParams.tagIds"
          placeholder="所属标签"
          @change="handleQuery"
          multiple
          clearable
        >
          <el-option
            v-for="(tag, index) in tagList"
            :key="index"
            :label="tag.tagName"
            :value="tag.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          style="width: 240px"
          v-model="queryParams.status"
          placeholder="文章状态"
          @change="handleQuery"
          clearable
        >
          <dictionary-option code="blogArticleStatus" /> </el-select
      ></el-form-item>
      <el-form-item label="标识" prop="flag">
        <el-select
          style="width: 240px"
          v-model="queryParams.flag"
          placeholder="文章标识"
          @change="handleQuery"
          clearable
        >
          <dictionary-option code="blogArticleFlag" /> </el-select
      ></el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <router-link to="/blog/article/write">
          <el-button
            type="primary"
            icon="Plus"
            v-permission="['blog:article:add']"
            >写文章</el-button
          >
        </router-link>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-col>
    </el-row>

    <div v-loading="loading">
      <el-row :gutter="24">
        <el-col
          :xs="24"
          :sm="12"
          :md="12"
          :lg="6"
          :xl="4"
          class="article-col"
          v-for="article in dataList"
          :key="article.id"
        >
          <article-card :article="article">
            <template #card-footer>
              <div class="article-card-operate">
                <router-link :to="'/blog/article/edit/' + article.id">
                  <el-button
                    class="article-card-btn"
                    type="primary"
                    icon="Edit"
                    v-permission="['blog:article:update']"
                    >编 辑</el-button
                  >
                </router-link>
                <el-button
                  class="article-card-btn"
                  type="danger"
                  icon="Delete"
                  @click="handleDelete(article)"
                  v-permission="['blog:article:delete']"
                  >删 除</el-button
                >
              </div>
            </template>
          </article-card>
        </el-col>
      </el-row>
    </div>

    <div class="no-data" v-if="dataList.length === 0">暂无文章数据</div>

    <pagination
      v-show="totalCount > 0"
      :total="totalCount"
      :page-sizes="[8, 16, 24, 32]"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getPage"
    />

    <!-- 新增和编辑 -->
    <!-- <edit @fetch-data="handleQuery" ref="editRef"></edit> -->
  </div>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'

import { page, remove } from '@/api/blog/article'
import { list as listCategory } from '@/api/blog/category'
import { list as listTag } from '@/api/blog/tag'

import ArticleCard from '@/components/ArticleCard'

const { proxy } = getCurrentInstance()

const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
const categoryList = ref([])
const tagList = ref([])

const queryParams = ref({
  pageNum: 1,
  pageSize: 8,
  articleTitleDescription: undefined,
  tagIds: undefined,
  categoryId: undefined,
  status: undefined,
  flag: undefined
})

/**
 * 获取数据
 */
async function getPage() {
  loading.value = true
  const {
    data: { total, list }
  } = await page(queryParams.value)
  totalCount.value = parseInt(total)
  dataList.value = list
  loading.value = false
}

/**
 * 获取分类列表
 */
async function getCategoryList() {
  const { data } = await listCategory()
  categoryList.value = data
}

/**
 * 获取标签列表
 */
async function getTagList() {
  const { data } = await listTag()
  tagList.value = data
}

/**
 * 搜索
 */
function handleQuery() {
  getPage()
}

/**
 * 删除
 */
function handleDelete(article) {
  proxy.$modal
    .confirm('是否确认删除标题为【' + article.articleTitle + '】的文章数据?')
    .then(async function () {
      await remove(article.id)
      proxy.$modal.msgSuccess('删除文章成功')
      handleQuery()
    })
}

/**
 * 初始化
 */
getPage()
getCategoryList()
getTagList()
</script>
<style lang="scss" scoped>
.article-col {
  padding: 10px 0px;

  .article-card-operate {
    padding: 10px 10px;

    .article-card-btn {
      margin-right: 10px;
    }
  }
}
</style>
