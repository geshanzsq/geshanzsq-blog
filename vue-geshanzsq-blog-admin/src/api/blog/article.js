import request from '@/utils/request'

export function page(data) {
  return request({
    url: '/blog/article/page',
    method: 'post',
    data
  })
}

export function getById(id) {
  return request({
    url: `/blog/article/getById/${id}`,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: '/blog/article/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/blog/article/',
    method: 'put',
    data
  })
}

export function remove(ids) {
  return request({
    url: `/blog/article/delete/${ids}`,
    method: 'delete'
  })
}
