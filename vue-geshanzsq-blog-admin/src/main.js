import { createApp } from 'vue'

import Cookies from 'js-cookie'

import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 中文语言
import locale from 'element-plus/es/locale/lang/zh-cn'

// 导入 svgIcon
import svgIcon from '@/plugins/svg-icon'
import elementIcons from '@/components/SvgIcon/svgicon'

// 全局样式
import '@/assets/styles/index.scss'

// 插件
import plugins from './plugins'

// 指令
import directive from './directive'

// 导航守卫
import './permission'

import {
  parseTime,
  resetForm,
  addDateRange,
  handleTree,
  getDictionaryLabel,
  getPictureShowUrl
} from '@/utils/geshanzsq'

// 分页组件
import Pagination from '@/components/Pagination'

// 字典组件
import DictionaryTag from '@/components/DictionaryTag'
import DictionaryOption from '@/components/DictionaryOption'
import DictionaryRadio from '@/components/DictionaryRadio'

// Markdown
import VMdEditor from '@kangc/v-md-editor'
import '@kangc/v-md-editor/lib/style/base-editor.css'
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js'
import '@kangc/v-md-editor/lib/theme/style/github.css'

// highlightjs
import hljs from 'highlight.js'

VMdEditor.use(githubTheme, {
  Hljs: hljs
})

const app = createApp(App)

directive(app)

// 全局方法挂载
app.config.globalProperties.parseTime = parseTime
app.config.globalProperties.resetForm = resetForm
app.config.globalProperties.handleTree = handleTree
app.config.globalProperties.addDateRange = addDateRange
app.config.globalProperties.getDictionaryLabel = getDictionaryLabel
app.config.globalProperties.getPictureShowUrl = getPictureShowUrl

// 使用element-plus 并且设置全局的大小
app.use(ElementPlus, {
  locale: locale,
  // 支持 large、default、small
  size: Cookies.get('size') || 'default'
})

app.use(plugins)
app.use(VMdEditor)

// 全局组件挂载
app.component('Pagination', Pagination)
app.component('DictionaryTag', DictionaryTag)
app.component('DictionaryOption', DictionaryOption)
app.component('DictionaryRadio', DictionaryRadio)

app.use(svgIcon)
app.use(elementIcons)
app.use(store).use(router).mount('#app')
