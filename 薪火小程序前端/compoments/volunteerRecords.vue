<template>
	<view class="volunteerRecord">
		<view class="dateTag">
			2021/10/22
		</view>
		<view class="activity">
			<view class="activityName">
				爱心亭志愿活动
			</view>
			<view class="activityHours">
				3.0h
			</view>
		</view>
		<view class="writeFeeling" v-if="haveFeeling">
			<image src="../static/个人_slices/mipmap-xhdpi/书%20(1).png" mode=""></image>
			<view>我的心得</view>
		</view>
		<view class="myFeeling" v-if="!haveFeeling">
			<image src="../static/个人_slices/mipmap-xhdpi/pencil.png" mode=""></image>
			<view>填写心得</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				haveFeeling:1
			}
		},
		
	}
</script>

<style lang='scss' scoped>
	.volunteerRecord {
		margin-bottom: 20rpx;
		border: 1px solid #000000;
		width: 600rpx;
		height: 130rpx;
		background-color: #fffbdd;
		border-radius: 20rpx;
		position: relative;
		display: flex;
		.dateTag {
			border: 1px solid #000;
			border-radius: 0 20rpx 20rpx 0;
			display: inline-block;
			padding: 5rpx 10rpx;
			font-size: 20rpx;
			background-color: #ffdc88;
			position: absolute;
			top: 15rpx;
			
		}
		.activity {
			box-sizing: border-box;
			padding-top: 60rpx;
			display: flex;
			height: 100%;
			width: 80%;
			border-right: 1px solid #000;
			justify-content: space-around;
			.activityName {
				margin-left: 20rpx;
				
			}
			.activityHours {
				
			}
		}
		.writeFeeling {
			width: 20%;
			font-size: 20rpx;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			image {
				margin-bottom: 10rpx;
				width: 45rpx;
				height: 45rpx;
			}
		}
	}
</style>
