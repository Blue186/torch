import { apiResquest } from '../utils/http.js'
//POST 请求案例

export const login = (query) => {
	return apiResquest({
		url: '/appLogin',
		method: 'POST',
		query: {...query}
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