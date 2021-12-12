<template>
	
		<!-- <input type="text" value="name" disabled="true"/> -->
		<button class="btn" type="default" :style="{backgroundColor: backgroundColor}" >{{name}}</button>
	
</template>	

<script>
	export default {
		props:['name','backgroundColor'],
		data(){
			return {
				
			}
		}
	}
</script>

<style lang="scss"  scoped>
	
		.btn {
			display: inline-block;
			padding: 0 20rpx;
			color: #fff;
			// background-color: #ffaa7f;
			font-size: 30rpx;
		}
		// input {
		// 	display: inline-block;
		// 	padding: 0 30rpx;
		// 	color: #ff00ff;
		// 	// max-width: 200rpx;
		// 	background-color: #ffaa7f;
		// }
	
</style>
