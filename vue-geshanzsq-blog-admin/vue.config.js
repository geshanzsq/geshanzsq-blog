const path = require('path')
function resolve(dir) {
  return path.join(__dirname, dir)
}

const { defineConfig } = require('@vue/cli-service')

const { devPort, devOpenBrowser } = require('./src/config/vue.cli.config')

const {
  siteTitle,
  siteDescription,
  siteKeywords,
  siteLoading
} = require('./src/config/setting.config')

process.env.VUE_APP_SITE_TITLE = siteTitle
process.env.VUE_APP_SITE_DESCRIPTION = siteDescription
process.env.VUE_APP_SITE_KEYWORDS = siteKeywords
process.env.VUE_APP_SITE_LOADING = siteLoading

module.exports = defineConfig({
  transpileDependencies: true,
  // webpack-dev-server 相关配置
  devServer: {
    port: devPort,
    open: devOpenBrowser,
    proxy: {
      [process.env.VUE_APP_BASE_API]: {
        target: 'http://localhost:8080/geshanzsq-blog-admin-api',
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    },
    client: {
      // 编译错误时，取消全屏覆盖（建议关掉）
      overlay: false
    }
  },
  // svg-sprite-loader 设置
  chainWebpack(config) {
    config.module.rule('svg').exclude.add(resolve('src/assets/icons')).end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/assets/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()
  }
})
