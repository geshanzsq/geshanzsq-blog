import request from '@/utils/request'

export function getOpenStatus() {
  return request({
    url: '/board/getOpenStatus',
    method: 'get'
  })
}
