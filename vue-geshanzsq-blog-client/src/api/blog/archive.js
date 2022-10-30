import request from '@/utils/request'

export function getGmtPublishLine() {
  return request({
    url: '/archive/getGmtPublishLine',
    method: 'get'
  })
}
