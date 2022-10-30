import request from '@/utils/request'

export function list() {
  return request({
    url: '/tag/list',
    method: 'get'
  })
}
