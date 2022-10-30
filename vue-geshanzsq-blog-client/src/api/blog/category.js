import request from '@/utils/request'

export function list() {
  return request({
    url: '/category/list',
    method: 'get'
  })
}
