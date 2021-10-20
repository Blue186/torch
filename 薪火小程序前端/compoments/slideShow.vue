<template>
	<view>

		<swiper class="swiper" :indicator-dots="true" :autoplay="true" :interval="3000" :duration="1000"
			indicator-color="#eee" indicator-active-color="#fff" circular=true>
			<swiper-item>
				<view class="swiper-item">薪火真棒</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">薪火YYDS</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">这是第三章图</view>
			</swiper-item>
		</swiper>

	</view>
</template>

<script>
</script>

<style lang="scss">
	.swiper {
		margin-top: 20rpx;
		
		border-radius: 20rpx;
		width: 90vw;
		height: 25vh;
		background-color: red;

		.swiper-item {}

	}
</style>
