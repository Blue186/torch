<template>
	<view class="indexPage">
		<slide-show></slide-show>
		<view class="recruitmentInformation">
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
	export default {
		components: {
			slideShow,
			activity
		},
		data() {
			return {

			}
		},
		methods: {

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
