<template>
	<view class="associationMessage">
		<view class="identity">
			<view class="avatar">
				<image :src="message.avatarUrl" mode="aspectFit"></image>
			</view>
			<view class="nameAndTime">
				<view class="poster">
					{{message.poster}}
				</view>
				<view class="postTime">
					{{message.postTime}}
				</view>

			</view>
		</view>
		<view class="content">
			{{message.content}}
		</view>
	</view>
</template>

<script>
	export default {
		props: ['message'],
		data() {
			return {

			}
		},

	}
</script>

<style lang='scss' scoped>
	.associationMessage {
		border-bottom: 5px solid #eee;
		height: 300rpx;
		overflow: hidden;
		width: 700rpx;
		margin-top: 40rpx;
		.identity {
			display: flex;
			align-items: center;
			.avatar {
				image {
					width: 100rpx;
					height: 100rpx;
				}
			}
			.nameAndTime {
				margin-left: 30rpx;
				
				.postTime {
					font-size: 25rpx;
					color: #b0b0b0;
				}
			}
		}

		.content {
			float: right;
			border: 1px solid #000;
			height: 150rpx;
			width: 570rpx;
			border-radius: 20rpx;
			text-indent: 2em;
		}
	}
</style>
