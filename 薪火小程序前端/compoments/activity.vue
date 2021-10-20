<template>
	<view class="activity">
		<view class="pic">
		</view>
		<view class="activityInformation">
			<view class="nameAndLocation">
				<text class="activityName">解放碑街道清理</text>
				<view class="location">
					<image src="../static/首页_slices/mipmap-xhdpi/dingwei%20拷贝%202.png" mode=""></image>
					解放碑
				</view>
			</view>

			<text class="activityTime">全天</text>
			<text class="activityNumber">招募人数：2/5人</text>
		</view>
		<!-- <view class="tag">
			招募中
		</view> -->
		<tag class="tag" name="招募中"></tag>
		
		<button type="default" class="btn" @click="goDetail">详情</button>

	</view>
</template>

<script>
	import tag from "./tag.vue"
	export default {
		components:{
			tag
		},
		data() {
			return {
				
			}
		},
		methods: {
			goDetail(){
				console.log("点到我了")
				uni.navigateTo({
					url:'/pages/activityDetail/activityDetail'
				})
			}
		}
	}
</script>

<style lang="scss">
	.activity {
		position: relative;
		border: 1rpx solid #808080;
		width: 90vw;
		height: 400rpx;
		border-radius: 20rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		.btn {
			position: absolute;
			bottom: 20rpx;
			right: 20rpx;
			height: 70rpx;
			font-size: 35rpx;
			line-height: 70rpx;
			background-color: rgb(85,102,205);
			color: white;
			
		}
		.tag {
			position: absolute;
			right: 40rpx;
			width: 55rpx;
		}

		.pic {
			margin-top: 10rpx;
			width: 95%;
			height: 60%;
			background-color: #4CD964;

		}

		.activityInformation {
			width: 95%;
			display: flex;
			flex-direction: column;
			align-items: flex-start;
			.activityTime{
				font-size: 28rpx;
				color: rgb(85,102,205);
			}
			.activityNumber {
				font-size: 32rpx;
			}
			.nameAndLocation {
				display: flex;
				align-items: center;
				.activityName {
					font-weight: bold;
				}
				.location {
					display: flex;
					align-items: center;
					font-size: 30rpx;

					image {
						width: 35rpx;
						height: 35rpx;
					}

					margin-left: 20rpx;
				}
			}
		}
	}
</style>
