<template>
	<view class="messagePage">
		<view class="header">
			<view class="myDynamic">
				<view class="number">
					12
				</view>
				<view class="name">
					我的动态
				</view>
			</view>
		</view>
		<view class="associationMessage">
			<view class="aboutMe">
				与我相关>>
			</view>

			<view class="association">
				<associationMessage v-for="(message) in messages" :key='message.id' :message="message">
				</associationMessage>
			</view>
		</view>
	</view>
</template>

<script>
	import associationMessage from './associationMessage.vue'
	export default {
		components: {
			associationMessage
		},
		data() {
			return {
				messages: [{
					id:'000',
					poster: "小宣",
					postTime: '14:00',
					content: '大家好!我是小宣',
					avatarUrl:'/static/images/department.png'
				}]
			};
		}
	}
</script>

<style lang="scss" scoped>
	.messagePage {
		background-color: #eee;

		.header {
			background-color: #fff;
			width: 100%;
			display: flex;
			justify-content: flex-start;
			margin-bottom: 30rpx;
			padding-left: 80rpx;

			.myDynamic {
				display: flex;
				flex-direction: column;
				align-items: center;
				color: #4d5e9f;

				.number {
					font-size: 45rpx;
				}

				.name {
					color: #000;
					font-size: 20rpx;
					margin-top: 10rpx;
				}
			}
		}

		.associationMessage {
			background-color: #fff;
			display: flex;
			flex-direction: column;
			align-items: center;
			padding-top: 30rpx;

			.aboutMe {
				border: 1px solid #000;
				display: inline-block;
				padding: 10rpx;
				border-radius: 20rpx;
				background-color: #eee;
				font-size: 25rpx;
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
	}
</style>
