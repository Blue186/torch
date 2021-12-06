import {postUserImformation} from '../models/indexModel.js'
import {loginApi} from '../models/indexModel.js'

// 登陆函数
export default  (prams)=>{
	// 获取code
	uni.login({
		provider: 'weixin',
		success: res => {
			// 获取cookie
			loginApi({
				code: res.code
			}).then(res => {
				var status = res.data.status
				if(status) {
					uni.getUserProfile({
						desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
						success: (res) => {
							console.log(res)
							//上传用户信息
								postUserImformation({
									"avatarImage": "这是一张图",
									"grade": "",
									"name": "",
									"nickName": res.userInfo.nickName,
									"phone": "",
									"qq": "",
									"school": "",
									"volAccount": ""
								}).then(res => {
									console.log(res)
								})
								
						}
					})
				}
				// 存储cookie
				uni.setStorage({
					key: 'cookie',
					data: {
						cookie: res.data.c
					},
					success: function() {
						if (status) {
							console.log('注册成功');
						} else {
							console.log('登陆成功');
						}
						console.log(uni.getStorageSync('cookie'))
					},
					fail() {}
				});
			}).catch(err => {
				console.log('登陆失败', err)
			})
		}
	});

}
