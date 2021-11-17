<template>
	<view>
		<!--index.wxml-->
		<view class="container">
		  <view class="icon">
		      <image src="https://www.torchcqs.cn:8080/lxj/organize-icon.jpg" alt="薪火志愿者协会" />
		  </view>
		<view class="header" @longpress="out"><text>薪火小程序</text></view>
		  
		  <view class="content"><text space="emsp">开发中... 敬请期待！</text></view>
		  <view class="code">
		  <image src="/package2/static/images/xiao.gif" alt="" />
		  </view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		onLoad(){
			uni.hideHomeButton()
		},
		onShow(){
			// uni.hideHomeButton()
		},
		methods: {
			out(){
				console.log("跳转")
				uni.switchTab({
					url:'/pages/index/index'
				})
			}
		}
	}
</script>

<style>
	.container {
		margin-top: 150rpx;
		display: flex;
		align-items: center;
		flex-direction: column;
	}
	.code image {
	  width: 250px;
	  height: 250px;
	}
	.icon image {
	  width: 300px;
	  height: 300px;
	}
	.header{
	  font-size: 30px;
	  color: orange;
	  font-style:normal;
	  margin-bottom: 30px;
	}
	.content {
	  color: orange;
	  margin-bottom: 10px;
	}

</style>
