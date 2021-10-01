<template>
	<view class="content">
    <view class="bacf">
    <title></title>
    <brief></brief>
    </view>
    <nowVolunter></nowVolunter>
    <timeAxis></timeAxis>
	</view>
</template>

<script>
	import title from "../../component/title.vue"
	import brief from"../../component/brief.vue"
	import nowVolunter from"../../component/nowVolunter.vue"
	import timeAxis from '../../component/timeAxis.vue'
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {

		},
		components: {
		            // 注册
		            title,
					brief,
					nowVolunter,
					timeAxis
		}
	}
</script>

<style>
.header{
  width: 90%;
  margin-left: 5%;
  height: 80px;
  display: flex;
}
.title{
  flex: 2;
}
.message{
  flex: 3;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.messageHead{
  display: flex;
  justify-content: space-between;
}
.self{
  flex: 3;
}
.limit{
  background-color: #5c69bb;
  padding: 5rpx 25rpx;
  border-radius: 30rpx;
  color: #fff;
  font-size: 30rpx;
}
.img{
  font-weight: 800;
}
.wxId{
  font-size: 32rpx;
  /* height: 100%; */
  width: 100%;
}
.imgs{
  height: 100%;
  width: 20%;
  display: flex;
}
.time{
  font-size: 32rpx;
  display: flex;
  justify-content: space-around;
}
.self{
  display: flex;
  align-items: center;
  font-size: 30rpx;
  justify-content: center;
  color: #dddddd;
}
/* header */
.brief{
  width: 90%;
  margin-left: 5%;
  height: 180rpx;
  background-color: #E8EDFF;
  border: 4rpx solid #000000;
  border-radius: 30rpx;
  display: flex;
}
.selfMessage{
  flex: 4;
}
.edit{
  flex: 1;
  /* background-color: pink; */
  display: flex;
  align-items: center;
  justify-content: center;
}
/*  */
.nowVolunter{
  width: 90%;
  margin-left: 5%;
  height: 220rpx;
  display: flex;
  padding: 20rpx 0;
}
.VolunterTime{
  flex: 2;
  position: relative;
  /* background-color: black; */
}
.nowThing{
  flex: 5;
}
.times{
  position: absolute;
  top: 80rpx;
  left: 40rpx;
  color:#5566CD ;
  font-size: 45rpx;
}
.elseVolunter{
  height: 140rpx;
  background-color: #FFDC88;
  border: 4rpx solid #333333;
  box-sizing: border-box;
  border-radius: 20rpx;
  width: 100%
}
.text{
  font-size: 30rpx;
  font-weight: 700;
}
.left{
  margin-left: 40rpx;
}
.bac{
  background-color: #FFFBDD;
  color: #000000;
  border: 4rpx solid #000000;
  border-radius: 20rpx!important;
  padding-right: 0!important;
  padding-left: 0!important;
  padding-top: 0!important;
  padding-bottom: 0!important;
  /* display: flex; */
  /* flex-direction: column; */
}
.bacRecord{
  flex: 3;
  /* background-color: pink; */
  /* width: 60%; */
}
.images{
  flex: 1;
  border-left: 4rpx solid #000000;
  /* width: 40%; */
}
.flex{
  display: flex;
}
.recordTime{
  padding: 0rpx 15rpx;
  font-size: 25rpx;
  background-color: #FFDC88;
  border-top-right-radius: 20rpx;
  border-bottom-right-radius: 20rpx;
  text-align: left!important;
}
.recordText{
  font-size: 32rpx;
  margin-left: 10rpx;
  display: flex;
  justify-content: space-between;
}
.middle{
  display: flex;
  justify-content: center;
  align-items: center;
}
.imgBook{
  display: flex;
  flex-direction: column;
}
.imgText{
  font-size: 22rpx;
  color: #666666;
}
.marginTop{
  margin-top:20rpx ;
}
.paddingLeft{
  padding-left: 20rpx;
}
.padding{
  padding: 20rpx 0;
}
.bacf{
  background-color: #fff;
  padding-bottom: 20rpx;
}
</style>
