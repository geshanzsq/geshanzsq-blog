import request from '@/utils/request'

export function list() {
  return request({
    url: '/friendship/list',
    method: 'get'
  })
}
