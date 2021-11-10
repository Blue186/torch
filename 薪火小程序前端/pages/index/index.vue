<template>
	<view class="indexPage">
		<!-- 蒙层 -->
		<view class="cover" @click="getUserProfile" v-show="cover">
		</view>
		<!-- 轮播图 -->
		<slide-show :images='indexSlideImages'></slide-show>
		<!-- 招募信息 -->
		<view class="recruitmentImformation" >
			<view class="title" @longpress="goIng">志愿招募</view>
			<view class="activities">
				<activity class="activity" :info='activityInformations'></activity>
			</view>
			<view class="bottom">
				---别划了，到底了~---
			</view>
		</view>

	</view>
</template>

<script>
	import slideShow from '../../compoments/slideShow.vue'
	import activity from '../../compoments/activity.vue'
	import {
		login
	} from '../../models/index.js'
	import {
		postUserImformation
	} from '../../models/index.js'
	export default {
		components: {
			slideShow,
			activity
		},
		data() {
			return {
				code: null,
				cover: false,
				indexSlideImages: [{
					url: 'https://www.torchcqs.cn:8080/lxj/allMeeting.jpg',
					name: 'allMeeting'
				}, {
					url: 'https://www.torchcqs.cn:8080/lxj/together.jpg',
					name: 'together'
				}, {
					url: 'https://www.torchcqs.cn:8080/lxj/preach.jpg',
					name: 'preach'
				}],
				activityInformations:[{
					activityName:'解放碑街道清理',
					activityImage:'/static/images/dingwei@2x.png',
					activityTime:'全天',
					activityNumber:'5',
					
				}]
			}
		},
		onLoad() {
			// uni.navigateTo({
			// 	url:'/package1/pages/userInformationEdit/userInformationEdit'
			// })
			// this.login()
		},
		methods: {
			//进入开发中的页面（暂时）
			goIng() {
				uni.navigateTo({
					url: '/package2/pages/ing/ing'
				})
			},
			//登陆获得id
			login() {
				uni.login({
					provider: 'weixin',
					success: res => {
						console.log(res)
						this.code = res.code
						this.apiLogin()
					}
				});
			},
			// 获取用户信息
			getUserProfile() {
				console.log("调用成功")
				// 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
				// 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
				wx.getUserProfile({
					desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
					success: (res) => {
						console.log(res)
						this.postUserImformation(res.userInfo)
						// uni.setStorage({
						//     key: 'userInfo',
						//     data: {
						//   nickName: res.userInfo.nickName,
						//   avatarUrl: res.userInfo.avatarUrl
						//  },
						//     success: function () {
						//         console.log('存储用户数据成功');
						//     },
						//  fail(){
						//   console.log("存储用户数据失败")
						//  }
						// });
						// this.setData({
						//   userInfo: res.userInfo,
						//   hasUserInfo: true
						// })
					}
				})
				// this.apiTest()  
				this.cover = false
			},

			apiLogin() {

				login({
					code: this.code
				}).then(res => {
					console.log(res)
					this.cover = res.data.status
					console.log(res.data.c)
					uni.setStorage({
						key: 'cookie',
						data: {
							cookie: res.data.c
						},
						success: function() {
							if (this.cover) {
								console.log('注册成功');
							} else {
								console.log('登陆成功');
							}
							console.log(uni.getStorageSync("获取到的cookie", 'cookie'))
						},
						fail() {}
					});
				}).catch(err => {
					console.log('登陆失败', err)
				})
			},
			postUserImformation(userInfo) {

				postUserImformation({
					"avatarImage": "这是一张图",
					"grade": "",
					"name": "",
					"nickName": userInfo.nickName,
					"phone": "",
					"qq": "",
					"school": "",
					"volAccount": ""

				}).then(res => {
					console.log(res)
				})
			}
		}
	}
</script>

<style lang="scss">
	.indexPage {
		display: flex;
		flex-direction: column;
		align-items: center;


	}

	.recruitmentImformation {
		width: 95%;
		min-height: 70vh;
		margin-top: 50rpx;
		background-color: #eee;
		border-radius: 20rpx;
		display: flex;
		flex-direction: column;

		.title {
			padding: 20rpx 10rpx;
			background-color: rgb(85, 102, 205);
			border-radius: 20rpx 20rpx 0 0;
			color: white;
		}

		.activities {
			width: 100%;
			margin-top: 30rpx;

			.activity {
				display: flex;
				flex-direction: column;
				align-items: center;
			}
		}

		.bottom {
			margin: 100rpx auto;
		}
	}

	.cover {
		background-color: #eee;
		width: 100vw;
		height: 100vh;
		position: absolute;
		opacity: 0;
	}
</style>
