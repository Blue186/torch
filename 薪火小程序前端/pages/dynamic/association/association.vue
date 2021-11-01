<template>
	<view class=" associationPage">
		<view class="top">
				<view class="notice ">
					<image src="../../../static/动态-协会_slices/mipmap-xhdpi/宣传推广.png" mode="aspectFit"></image>
					<text>{{noticeContent}}</text>
				</view>
				<view class="slideShow">
					<slide-show></slide-show>
				</view>
				<view class="us">
					<view class="tellUs">
						<image class="usImage" src="../../../static/动态-协会_slices/mipmap-hdpi/图层%201.png" mode="aspectFit"></image>
						<view class="name">
							告诉我们
						</view>
					</view>
					<view class="aboutUs">
						<image  class="usImage" src="../../../static/动态-协会_slices/mipmap-hdpi/关于我们.png" mode="aspectFit"></image>
						<view class="name">
							关于我们
						</view>
					</view>
				</view>
		</view>
		<view class="association">
			<associationMessage poster='小宣' postTime='14:00' content='大家好!我是小宣' v-for="(item,index) in 6"></associationMessage>
		</view>
	</view>
</template>

<script>
	import associationMessage from '../../../compoments/associationMessage.vue'
	import slideShow from '../../../compoments/slideShow.vue'
	export default {
		components: {
			slideShow,
			associationMessage
		},
		data() {
			return {
				noticeContent: "薪火志愿者协会两周年了！"


			};
		}
	}
</script>

<style lang="scss" scoped>
	// .wrap {
	// 	width: 95%;
	// 	margin: 0 auto;
	// 	display: flex;
	// 	flex-direction: column;
	// 	align-items: center;
	// }

	.associationPage {
		display: flex;
		flex-direction: column;
		align-items: center;
		background-color: #eee;
		

		.top {
			background-color: #FFF;
			width: 100%;
			.slideShow {
				width: 90%;
				margin: 0 auto;
			}
			.notice {
				height: 50rpx;

				text {
					font-size: 23rpx;
					margin-left: 20rpx;
					display: inline-block;
					height: 50rpx;
					line-height: 50rpx;
					color: #bd7171;
				}

				image {
					vertical-align: bottom;
					height: 50rpx;
					width: 50rpx;
				}
			}
			.us {
				margin: 40rpx 0;
				display: flex;
				justify-content: space-around;
				.usImage {
					padding: 10rpx;
					height: 50rpx;
					width: 50rpx;
				}
				.name {
					font-size: 20rpx;
				}
			}
		}
		.association {
			width: 100%;
			display: flex;
			flex-direction: column;
			align-items: center;
			margin-top: 20rpx;
			background-color: #fff;
		}
	}
</style>
