<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="图片名称" prop="pictureOriginalName">
        <el-input
          v-model="queryParams.pictureOriginalName"
          placeholder="请输入图片原名称"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片分类" prop="categoryId">
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

      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="Plus"
          @click="handleUpload"
          v-permission="['blog:picture:upload']"
          >上传</el-button
        >
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-permission="['blog:picture:delete']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" @click="handleChoose">{{
          chooseTitle
        }}</el-button>
      </el-col>
    </el-row>

    <div style="padding: 6px" v-loading="loading">
      <el-row :gutter="18">
        <el-col
          :xs="24"
          :sm="12"
          :md="12"
          :lg="6"
          :xl="4"
          v-for="picture in dataList"
          :key="picture.id"
          class="picture-col"
        >
          <el-card class="card" :body-style="{ padding: '0px' }">
            <el-image
              :src="getPictureShowUrl(picture.picturePath)"
              class="picture-show"
              :class="ids.indexOf(picture.id) >= 0 ? 'picture-show-over' : ''"
              @click="handlePicture(picture.id)"
              fit="cover"
              lazy
            />
            <div style="padding: 10px">
              <span class="picture-original-name">{{
                picture.pictureOriginalName
              }}</span>
            </div>

            <div style="padding: 5px">
              <el-button-group>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="复制图片地址"
                  placement="bottom-start"
                  style="margin-right: 2px"
                >
                  <el-button
                    size="mini"
                    icon="copy-document"
                    @click="copyPictureUrl(baseFileUrl + picture.picturePath)"
                  />
                </el-tooltip>

                <el-tooltip
                  class="item"
                  effect="dark"
                  content="复制 Markdown 格式图片地址"
                  placement="bottom-start"
                  style="margin-right: 2px"
                >
                  <el-button
                    type="primary"
                    size="mini"
                    icon="document-copy"
                    @click="
                      copyMarkdownUrl(
                        picture.pictureOriginalName,
                        baseFileUrl + picture.picturePath
                      )
                    "
                  >
                  </el-button>
                </el-tooltip>

                <el-tooltip
                  class="item"
                  effect="dark"
                  content="删除图片"
                  placement="bottom-start"
                  style="margin-right: 2px"
                  v-permission="['blog:picture:remove']"
                >
                  <el-button
                    type="danger"
                    size="mini"
                    icon="delete"
                    @click="handleDelete(picture)"
                  />
                </el-tooltip>
              </el-button-group>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <div class="no-data" v-if="dataList.length === 0">暂无上传图片</div>

    <pagination
      v-show="totalCount > 0"
      :total="totalCount"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      :page-sizes="[8, 16, 24, 32]"
      @pagination="getPage"
    />

    <!-- 新增和编辑 -->
    <upload-picture @fetch-data="handleQuery" ref="uploadRef"></upload-picture>
  </div>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'
import { useRouter } from 'vue-router'

import { list as listCategory } from '@/api/blog/picture/category'
import { page, remove } from '@/api/blog/picture/picture'

import { copyText } from '@/utils/geshanzsq'

import UploadPicture from './components/UploadPicture'

const router = useRouter()
const { proxy } = getCurrentInstance()

const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
const categoryList = ref([])
const ids = ref([])
const chooseTitle = ref('全选')
// 是否全选
const isCheckedAll = ref(false)
// 图片预览前缀
const baseFileUrl = ref(process.env.VUE_APP_BASE_FILE)
const queryParams = ref({
  pageNum: 1,
  pageSize: 8,
  categoryId: router.currentRoute.value.params.id,
  pictureOriginalName: undefined
})

function handleQuery() {
  getPage()
}

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
 * 单选图片
 */
function handlePicture(id) {
  const index = ids.value.indexOf(id)
  if (index >= 0) {
    // 已经选过，去除
    ids.value.splice(index, 1)
    chooseTitle.value = '全选'
  } else {
    ids.value.push(id)
    if (ids.value.length === dataList.value.length) {
      chooseTitle.value = '取消全选'
    }
  }
}

/**
 * 全选
 */
function handleChoose() {
  ids.value = []
  if (isCheckedAll.value) {
    isCheckedAll.value = false
    chooseTitle.value = '全选'
  } else {
    dataList.value.forEach((picture) => {
      ids.value.push(picture.id)
    })
    isCheckedAll.value = true
    chooseTitle.value = '取消全选'
  }
}

/**
 * 复制图片地址
 */
function copyPictureUrl(url) {
  copyText(url)
  proxy.$modal.msgSuccess('复制链接到剪切板成功')
}

/** 复制图片Markdown地址 */
function copyMarkdownUrl(name, url) {
  const text = '![' + name + '](' + url + ')'
  copyText(text)
  proxy.$modal.msgSuccess('复制Markdown格式链接到剪切板成功')
}

/**
 * 新增
 */
function handleUpload() {
  proxy.$refs.uploadRef.show(queryParams.value.categoryId)
}

/**
 * 删除按钮操作
 */
async function handleDelete(row) {
  const deleteIds = row.id || ids.value
  proxy.$modal.confirm('是否确认删除所选图片？').then(async function () {
    await remove(deleteIds)
    proxy.$modal.msgSuccess('删除图片成功')
    handleQuery()
  })
}

getPage()
getCategoryList()
</script>
<style scoped lang="scss">
.picture-col {
  padding: 10px 0px;

  .card {
    text-align: center;
    max-height: 400px;

    .picture-show {
      /* 出现抖动和走位问题 */
      border: 2px solid transparent;
      text-align: center;
      max-width: 100%;
      height: 200px;
    }
    .picture-show:hover {
      border: 2px solid #07c160;
    }
    .picture-show-over {
      border: 2px solid #07c160;
      opacity: 0.3;
      top: 0;
      left: 0;
      width: 100%;
      z-index: 1;
    }
    .picture-original-name {
      display: inline-block;
      white-space: nowrap;
      width: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  }
}
</style>
