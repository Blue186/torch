<template>
	<view>
		<view class="header">
			<view class="association" @click="changePage('association')" :class="currentPage === 'association'? 'active' : ''">
				<text>协会</text>
				<view class="underline" v-if="currentPage == 'association'">
				</view>
			</view>
			<view class="message" @click="changePage('message')" :class="currentPage === 'message'? 'active' : ''">
				<text>消息</text>
				<view class="underline" v-if="currentPage == 'message'">
				</view>
			</view>
		</view>
		<view class="page">
			<association v-if="currentPage === 'association'"></association>
			<message v-if="currentPage === 'message'"></message>
		</view>
	</view>
</template>

<script>
	import message from '../../compoments/message.vue'
	import association from '../../compoments/association.vue'
	export default {
		components:{
			association,
			message
		},
		data() {
			return {
				currentPage:'association'
			}
		},
		methods: {
			changePage(page){
					this.currentPage = page
			}
		}
	}
</script>

<style lang="scss">
	.active {
		color: #51579f;
		font-size: 40rpx;
	}
	.header {
		width: 70%;
		display: flex;
		color: #cfcfea;
		font-size: 40rpx;
		justify-content: space-around;
		margin: 40rpx auto;
		.underline {
			margin-top: 6rpx;
			border: 3.8rpx solid #4d5e9f;
			background-color: #4d5e9f;
		}
	}
</style>
