<template>
	<view class="page">
		<view class="nonePage" v-if="none">


			<view class="img">
				<image src="/package3/static/images/post.png" mode=""></image>
			</view>
			<view class="content">
				<text>你还没有志愿记录喔~</text>
				<text>赶紧前往首页报名吧</text>
			</view>
		</view>
		<view class="have" v-else>
			<activity></activity>

			<text>--end--</text>

		</view>
	</view>
</template>

<script>
	import activity from '../../../compoments/activity.vue'
	export default {
		components: {
			activity
		},
		data() {
			return {
				none: false
			};
		}
	}
</script>

<style lang="scss">
	// @import url('../../static/test.scss');

	.page {

		min-height: 100vh;
		background-color: rgb(248, 248, 248);
		color: #bab7bb;
		justify-content: flex-start;
		padding-top: 30rpx;
		.have {
			display: flex;
			flex-direction: column;
			align-items: center;
			text {
				margin-top: 30rpx;
			}
		}

		.img {
			margin-top: 300rpx;
			margin-bottom: 30rpx;

			img {
				width: 100rpx;
				height: 100rpx;
			}

		}

		.content {
			display: flex;
			flex-direction: column;

		}
	}
</style>
