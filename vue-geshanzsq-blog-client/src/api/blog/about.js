import request from '@/utils/request'

export function getContent() {
  return request({
    url: '/about/getContent',
    method: 'get'
  })
}
