import request from '@/utils/request'

export function list() {
  return request({
    url: '/third/config/list',
    method: 'get'
  })
}
