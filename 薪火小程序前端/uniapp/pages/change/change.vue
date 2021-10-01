<template>
<view class="change">
<view class="header paddingTop">
<view class="flex">
  <view style="width:80rpx"  class="middle">
    <image src="https://636c-cloud1-1gj1ixwl4cd205b1-1306839659.tcb.qcloud.la/people.png?sign=1f23a6f43aec271f3b2e754096ad52aa&t=1632907286" alt="" mode="widthFix" style="width:100%"></image></view>
    <text  class="Text">个人信息编辑</text>
</view>
</view>
<view class="inputHead">
  <view class="inputFlex marginLeft">
<text class="Text">姓名：</text>
<input type="text" class="input inputWidthLittle"/>
</view>
<view class="inputFlex marginLeft">
<text class="Text">性别：</text>
<input type="text" class="input inputWidthLittle"/>
</view>
</view>
<view class="inputHead">
<view class="inputFlex marginLeft">
<text class="Text">学校/单位：</text>
<input type="text" class="input inputWidthBig"/>
</view>
</view>
<view class="inputHead">
<view class="inputFlex marginLeft">
<text class="Text">QQ号码：</text>
<input type="text" class="input inputWidthBig"/>
</view>
</view>
<view class="inputHead">
<view class="inputFlex marginLeft">
<text class="Text">手机号码：</text>
<input type="text" class="input inputWidthBig"/>
</view>
</view>
<view class="inputHead">
<view class="inputFlex marginLeft">
<text class="Text">验证码(QQ邮箱)：</text>
<input type="text" class="input" style="width:50%"/>
</view>
</view>
<view class="inputHeads" style="margin-top: 40rpx;">
<view class="Text:">个人简介:</view>
<view style="margin-top: 20rpx;"><textarea name="" id="" cols="30" rows="10" class="input textarea"></textarea></view>
</view>
<view class="inputHead bottom">
<button>保存</button></view>
</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>
.change{
  width: 90%;
  margin-left: 5%;
  /* height: 100vh; */
  background-color: rgb(175, 219, 248);
}
.header{
  text-align: center;
}
.marginTop{
  margin-top:20rpx ;
}
.paddingTop{
  padding-top:20rpx ;
}
.middle{
  display: flex;
  justify-content: center;
  align-items: center;
}
.flex{
  display: flex;
  justify-content: center;
}
.Text{
  font-size: 35rpx;
  display: flex;
  align-items: center;
  color: #3D4E83 ;
}
.input{
  background-color: #fff;
  border-radius: 20rpx;
  height: 100rpx;
  font-size: 40rpx;
}
.inputFlex{
  display: flex;
  flex: 1;
}
.marginLeft{
  margin-left: 10rpx;
}
.inputHead{
  display: flex; 
  margin-top: 40rpx;
}
.inputWidthLittle{
  width: 60%;
}
.inputWidthBig{
  width: 70%;
}
.textarea{
  height: 300rpx;
  width: 90%;
  margin-left: 5%;
}
.bottom{
  padding-bottom: 50rpx;
}
</style>
