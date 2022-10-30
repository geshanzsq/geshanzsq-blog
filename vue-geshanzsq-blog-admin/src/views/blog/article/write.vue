<template>
  <div class="app-container" v-loading="loading">
    <el-form :model="editForm" :rules="rules" ref="editFormRef">
      <el-form-item prop="articleTitle">
        <el-input placeholder="请输入标题" v-model="editForm.articleTitle">
          <template #prepend>标题</template>
        </el-input>
      </el-form-item>

      <div class="editor-content">
        <div>
          <el-button size="mini" type="info" plain @click="handleEditor()">
            <span v-if="!isMarkdownEditor">Markdown</span>
            <span v-else>富文本编辑器</span>
          </el-button>
          <el-button
            type="primary"
            class="picture-library-btn"
            @click="handleEditorPicture"
            >图片库</el-button
          >
        </div>
        <el-form-item prop="articleContent">
          <markdown-editor
            v-if="isMarkdownEditor"
            ref="markdownEditorRef"
            v-model="editForm.articleContent"
          />
          <wang-editor
            v-else
            ref="editorRef"
            v-model="editForm.articleContent"
          />
        </el-form-item>
      </div>

      <el-row>
        <el-col :span="6">
          <el-form-item label="标识" prop="flag"
            ><el-select v-model="editForm.flag" placeholder="请选择标识">
              <dictionary-option code="blogArticleFlag" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="分类" prop="categoryId">
            <el-select
              v-model="editForm.categoryId"
              filterable
              placeholder="请选择分类"
            >
              <el-option
                v-for="category in categoryList"
                :key="category.id"
                :label="category.categoryName"
                :value="category.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="标签" prop="tagIds">
            <el-select
              v-model="editForm.tagIds"
              multiple
              filterable
              placeholder="请选择标签"
            >
              <el-option
                v-for="tag in tagList"
                :key="tag.id"
                :label="tag.tagName"
                :value="tag.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="推荐等级" prop="recommendLevels"
            ><el-select
              v-model="editForm.recommendLevels"
              placeholder="请选择推荐等级"
              multiple
              filterable
            >
              <dictionary-option code="blogArticleRecommendLevel" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="2">
          <el-form-item label="评论" prop="hasComment">
            <el-switch
              v-model="editForm.hasComment"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="1"
              inactive-value="2"
            ></el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item label="赞赏" prop="hasAppreciation">
            <el-switch
              v-model="editForm.hasAppreciation"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="1"
              inactive-value="2"
            ></el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item label="转载声明" prop="hasReprintStatement">
            <el-switch
              v-model="editForm.hasReprintStatement"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="1"
              inactive-value="2"
            ></el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item label="置顶" prop="hasSticky">
            <el-switch
              v-model="editForm.hasSticky"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="1"
              inactive-value="2"
            ></el-switch>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="文章描述" prop="articleDescription">
            <el-input
              placeholder="请输入文章描述......"
              type="textarea"
              :rows="8"
              v-model="editForm.articleDescription"
            />
          </el-form-item>
        </el-col>

        <el-col :span="8" class="cover-picture-col">
          <el-form-item label="封面图片" prop="coverUrl">
            <el-image
              class="cover-picture-image"
              fit="cover"
              v-if="editForm.coverUrl"
              :src="getPictureShowUrl(editForm.coverUrl)"
              @click="handleCover()"
            >
              <template #error>
                <el-button class="cover-picture-icon" @click="handleCover()"
                  >图片加载失败，请重新选择</el-button
                >
              </template>
            </el-image>
            <el-button v-else class="cover-picture-icon" @click="handleCover()"
              ><el-icon><Plus /></el-icon>请选择封面图片</el-button
            >
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item class="operator-footer">
        <el-button type="warning" @click="handleSave()">保 存</el-button>
        <el-button type="success" @click="handlePublish()">发 布</el-button>
      </el-form-item>
    </el-form>

    <!-- 编辑器选择图片库 -->
    <picture-library
      title="选择图片"
      @change="handleChangeEditorPicture"
      ref="pictureLibrayEditorRef"
    />

    <!--封面选择-->
    <picture-library
      :path="editForm.coverUrl"
      title="选择封面"
      @change="handleChangeCover"
      ref="pictureLibrayRef"
    />
  </div>
</template>
<script setup>
import { computed, getCurrentInstance, ref } from 'vue'

import MarkdownEditor from '@/components/MarkdownEditor'
import WangEditor from '@/components/WangEditor'
import PictureLibrary from '@/components/PictureLibrary'

import { list as listCategory } from '@/api/blog/category'
import { list as listTag } from '@/api/blog/tag'
import { getById, add, update } from '@/api/blog/article'

const { proxy } = getCurrentInstance()

const props = defineProps({
  id: {
    type: [Number, String],
    default: undefined
  }
})

const editForm = ref({
  id: props.id,
  editorType: 1,
  categoryId: undefined,
  tagIds: [],
  hasComment: '1',
  hasAppreciation: '1',
  hasReprintStatement: '1',
  hasSticky: '2',
  coverUrl: ''
})

const categoryList = ref([])
const tagList = ref([])

const rules = ref({
  articleTitle: [{ required: true, message: '标题不能为空', trigger: 'blur' }],
  flag: [{ required: true, message: '请选择标识', trigger: 'blur' }],
  categoryId: [{ required: true, message: '请选择分类', trigger: 'blur' }],
  tagIds: [
    { type: 'array', required: true, message: '请选择标签', trigger: 'blur' }
  ],
  articleDescription: [
    { required: true, message: '文章描述不能为空', trigger: 'blur' },
    { max: '300', message: '文章描述不能超过 300 个字' }
  ],
  coverUrl: [{ required: true, message: '请选择封面图片', trigger: 'blur' }]
})

const isMarkdownEditor = computed(() => {
  return editForm.value.editorType === 1
})

/**
 * 获取文章数据
 */
async function getArticle() {
  if (editForm.value.id) {
    const { data } = await getById(editForm.value.id)
    editForm.value = data
    editForm.value.flag = editForm.value.flag + ''
    editForm.value.hasAppreciation = editForm.value.hasAppreciation + ''
    editForm.value.hasComment = editForm.value.hasComment + ''
    editForm.value.hasReprintStatement = editForm.value.hasReprintStatement + ''
    editForm.value.hasSticky = editForm.value.hasSticky + ''
    // 标签
    editForm.value.tagIds = data.tags.map((t) => t.id)
    // 推荐等级
    editForm.value.recommendLevels = data.recommendLevels.map((t) => t + '')
  }
}

/**
 * 切换编辑器点击事件
 */
function handleEditor() {
  if (editForm.value.editorType === 1) {
    editForm.value.editorType = 2
  } else {
    editForm.value.editorType = 1
  }
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
 * 封面选择
 */
function handleCover() {
  proxy.$refs.pictureLibrayRef.show(editForm.value.coverUrl)
}

/**
 * 封面选择回调事件
 */
function handleChangeCover(url) {
  if (url) {
    editForm.value.coverUrl = url
  }
}

/**
 * 编辑器图片选择
 */
function handleEditorPicture() {
  proxy.$refs.pictureLibrayEditorRef.show()
}

/**
 * 编辑器图片选择回调事件
 */
function handleChangeEditorPicture(url) {
  if (!url) {
    return
  }
  if (isMarkdownEditor.value) {
    proxy.$refs.markdownEditorRef.insertPicture(url)
  } else {
    proxy.$refs.editorRef.insertPicture(url)
  }
}

/**
 * 保存
 */
function handleSave() {
  editForm.value.status = 1
  submitForm()
}

/**
 * 发布
 */
function handlePublish() {
  editForm.value.status = 2
  submitForm()
}

/**
 * 提交
 */
async function submitForm() {
  proxy.$refs.editFormRef.validate(async (valid) => {
    if (valid) {
      if (editForm.value.id) {
        await update(editForm.value)
        proxy.$modal.msgSuccess('操作成功')
      } else {
        const {
          data: { id }
        } = await add(editForm.value)
        editForm.value.id = id
        proxy.$modal.msgSuccess('操作成功')
      }
    }
  })
}

getArticle()
getCategoryList()
getTagList()
</script>
<style lang="scss" scoped>
.editor-content {
  .picture-library-btn {
    float: right;
  }
}

.cover-picture-col {
  margin-left: 20px;
  width: 300px;
  height: 220px;

  .cover-picture-image {
    width: 300px;
    height: 180px;
    border-style: solid;
    border-width: 1px;
    border-color: rgba(148, 148, 148, 0.47);
  }

  .cover-picture-icon {
    color: #8c939d;
    width: 300px;
    height: 180px;
    line-height: 180px;
    text-align: center;
    border-style: solid;
    border-width: 1px;
    border-color: rgba(148, 148, 148, 0.47);
  }
}

.operator-footer {
  position: fixed;
  bottom: 0px;
  right: 10px;
  z-index: 99999;
}
</style>
