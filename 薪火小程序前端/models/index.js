import { apiResquest } from '../utils/http.js'
//POST 请求案例

// 用户登录注册
export const login = (query) => {
	return apiResquest({
		url: '/user/login',
		method: 'POST',
		query: query
	})
}

export const signUp = (query) => {
	return apiResquest({
		url: '/signUp',
		method: 'POST',
		query: query
	})
}

// 上传用户信息
export const postUserImformation = (query) => {
	return apiResquest({
		url: '/user',
		method: 'PUT',
		query: query
	})
}
// 获取用户信息
export const getUserImformation = (query) => {
	return apiResquest({
		url: '/user',
		method: 'GET',
		query: query
	})
}
// 传邮箱信息
export const postEmail = (query) => {
	return apiResquest({
		url: '/user/mailVerify',
		method: 'POST',
		query: query
	})
}

export const postEmailCode = (query) => {
	return apiResquest({
		url: '/user/codeCheck',
		method: 'POST',
		query: query
	})
}

//GET 请求案例可以传递参数也可以不传递
// export const validateCode  = (query) => {
// 	let str = query
// 	return apiResquest({
// 		url: `您的API地址 ?${str}`,
// 		method: 'GET'
// 	})
// }