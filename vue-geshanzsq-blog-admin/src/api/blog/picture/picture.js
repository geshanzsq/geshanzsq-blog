import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/blog/picture/page',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/blog/picture/getById/${id}`,
    method: 'get'
  })
}

export function upload(params) {
  return request({
    url: '/blog/picture/upload',
    method: 'post',
    params
  })
}

export function remove(ids) {
  return request({
    url: `/blog/picture/delete/${ids}`,
    method: 'delete'
  })
}
