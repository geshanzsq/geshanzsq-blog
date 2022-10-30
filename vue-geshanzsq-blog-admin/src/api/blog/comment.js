import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/blog/comment/page',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/blog/comment/getById/${id}`,
    method: 'get'
  })
}

export function audit(data) {
  return request({
    url: '/blog/comment/audit',
    method: 'put',
    data
  })
}

export function remove(ids) {
  return request({
    url: `/blog/comment/delete/${ids}`,
    method: 'delete'
  })
}
