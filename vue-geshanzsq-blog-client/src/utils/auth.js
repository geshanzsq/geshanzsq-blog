import Cookies from 'js-cookie'
import { tokenConfig } from '@/config/network.config'
const TokenKey = tokenConfig.header
const loginRedirectKey = 'LoginRedirect'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token, { expires: tokenConfig.expireTime })
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

/**
 * 获取登录跳转地址
 */
export function getLoginRedirectPath() {
  return Cookies.get(loginRedirectKey)
}

/**
 * 设置登录跳转地址
 */
export function setLoginRedirectPath(path) {
  return Cookies.set(loginRedirectKey, path, {
    expires: 1
  })
}

/**
 * 移除登录跳转地址
 */
export function removeLoginRedirectPath() {
  return Cookies.remove(loginRedirectKey)
}
