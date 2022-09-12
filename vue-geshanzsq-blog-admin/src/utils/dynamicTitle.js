import store from '@/store'
import { siteTitle } from '@/config/setting.config'

/**
 * 动态修改标题
 */
export function useDynamicTitle() {
  if (store.getters['settings/dynamicTitle']) {
    document.title = store.getters['settings/title'] + ' - ' + siteTitle
  } else {
    document.title = siteTitle
  }
}
