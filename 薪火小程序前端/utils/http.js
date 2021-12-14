import {
	config
} from './config.js'
import login from './login.js'
export const apiResquest = (prams) => { //prams 为我们需要调用的接口API的参数 下面会贴具体代码

	// 判断请求类型
	let headerData = {
		'content-type': 'application/json',
		'c': uni.getStorageSync('cookie').cookie
	}

	let dataObj = null
	//因为我们的GET和POST请求结构不同这里我们做处理，大家根据自己后台接口所需结构灵活做调整吧
	if (prams.method === "GET") {
		headerData = {
			'content-type': 'application/json',
			'c': uni.getStorageSync('cookie').cookie
		}
	} else {
		dataObj = prams.query
	}
	return new Promise((resolve, reject) => {
		let url = config.base_url + prams.url; //请求的网络地址和局地的api地址组合
		uni.showLoading({
			title: '加载中',
			mask: true
		})
		if(prams.method === ''){
		
		}else{
			return uni.request({
				url: url,
				data: dataObj,
				method: prams.method,
				header: headerData,
				success: (res) => {
					uni.hideLoading()
					console.log("调用成功")
					if (res.data.code === -100) {
						console.log("登陆过期")
						uni.showLoading({
							title:"正在重新登陆",
							mask:true,
							success() {
								login()
							}
							
						})
						
					}
			
					resolve(res.data);
				},
				fail: (err) => {
					reject(err);
					console.log(err)
					console.log("调用失败")
					uni.hideLoading()
				},
				complete: () => {
					// console.log('请求完成')
					uni.hideLoading()
				}
			});
		}
		
	})
}




