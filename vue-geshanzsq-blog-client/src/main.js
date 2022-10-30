import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 中文语言
import locale from 'element-plus/es/locale/lang/zh-cn'

// 全局样式
import '@/assets/styles/index.scss'

// 插件
import plugins from './plugins'

// 分页组件
import Pagination from '@/components/Pagination'

import { getPictureShowUrl, parseTime, formatTime } from '@/utils/geshanzsq'

// 导入 svgIcon
import svgIcon from '@/plugins/svg-icon'
import elementIcons from '@/components/SvgIcon/svgicon'

// 代码高亮文件引入
import hljs from 'highlight.js'
// 样式文件,这里我选的是sublime样式，文件里面还有其他样式可供选择
import 'highlight.js/styles/monokai-sublime.css'

const app = createApp(App)

// 自定义一个代码高亮指令
app.directive('highlight', function (el) {
  const blocks = el.querySelectorAll('pre code')
  blocks.forEach((block) => {
    hljs.highlightBlock(block)
  })
})

// 全局方法挂载
app.component('Pagination', Pagination)
app.config.globalProperties.getPictureShowUrl = getPictureShowUrl
app.config.globalProperties.parseTime = parseTime
app.config.globalProperties.formatTime = formatTime

app.use(ElementPlus, {
  locale: locale
})
app.use(plugins)
app.use(svgIcon)
app.use(elementIcons)

app.use(store).use(router).mount('#app')
