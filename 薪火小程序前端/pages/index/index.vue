<template>
	<view class="indexPage">
		<slide-show></slide-show>
		<view class="recruitmentInformation" @click="apiTest">
			志愿招募信息
		</view>
		<view class="activities">
			<activity></activity>
		</view>
		
	</view> 
</template>

<script>
	import slideShow from '../../compoments/slideShow.vue'
	import activity from '../../compoments/activity.vue'
	import {login} from '../../models/index.js'
	export default {
		components: {
			slideShow,
			activity
		},
		data() {
			return {

			}
		},
		onLoad() {
			this.login()
		},
		methods: {
			login(){
				uni.login({
				  provider: 'weixin',
				  success: res=> {
					  console.log(res) 
				  }
				});
			},
			getUserProfile() {
				console.log("调用成功")
			    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
			    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
			    wx.getUserProfile({
			      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
			      success: (res) => {
					  console.log(res)
			        // this.setData({
			        //   userInfo: res.userInfo,
			        //   hasUserInfo: true
			        // })
			      }
			    })
				// this.apiTest()  
			  },
			  apiTest(){
				  login("123").then(res=>{
					  console.log(res)
				  }).catch(err=>{
					  console.log(err)
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
		.activities {
			margin-top: 40rpx;
		}
	}
	.recruitmentInformation {
		width: 80%;
		background-color: pink;
		margin-top: 20rpx;
		
	}
</style>
