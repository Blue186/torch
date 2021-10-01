<template name="title">
	<view class="header marginTop">
	    <view class="title"><open-data type="userAvatarUrl"></open-data></view>
	    <view class="message   paddingLeft">
	    <view class="messageHead"><open-data type="userNickName" class="img"></open-data>
	    <text class="limit">管理员</text>
	    </view>
	    <view class="wxId">
	    <view>5555555</view>
	   <!-- <view class="imgs"> <image src="../../images/复制.png"
	        mode="widthFix"></image></view> -->
	    </view>
	    <view class="time">
	  <text>女</text>
	  <text>2021/22/22</text>
	    </view>
	    </view>
	    <view style="flex:1"></view>
	    <view class="self">
	    <view>编辑资料</view>
	    <view style="width:40rpx"  class="middle">
	    <image src="https://636c-cloud1-1gj1ixwl4cd205b1-1306839659.tcb.qcloud.la/right.png?sign=45d8c6cd8a6f714888554b0d130366f6&t=1632906834" alt="" mode="widthFix" style="width:100%"></image></view>
	    </view>
	</view>
</template>

<script>
    export default {
        name: "title",
        data() {
            return {
                
            };
        },
        // 此处定义传入的数据
        props: {
            movie: {
                type: Object,
                value: null
            }
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
}
.middle{
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
