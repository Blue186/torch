<script>
	import login from './utils/login.js'
	export default {
		onLaunch: function() {
			console.log('App Launch')
			login()
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	*{
		margin: 0;
		padding: 0;
	}
</style>
