Page({

  /**
   * 页面的初始数据
   */
  data: {
    statusImage:['/images/形状 3 拷贝.png','hjk'],
    status:['已结束'],
    title:'解放碑街道清理',
    detailLocation:'志愿者服务队xxx（解放碑）',
    peopleNum:5,
    time:['6月1日 16:00-18:00','6月1日 16:00-18:00','6月1日 16:00-18:00'],
    note:['1.xxxxx','2.xxxx','3.xxx']
  },
// 报名弹窗
  signUp: function(){
    wx.showModal({
      cancelColor: 'cancelColor',
      title:'报名',
      content:'确定报名吗？',
      cancelColor:'#000000',
      confirmColor:'#000000',
      success(res){
        if(res.confirm){
          // 可以添加函数
          console.log("点击确认")
        }else if(res.cancel){
          // 可以添加函数
          console.log("点击取消")
        }
      }
    })
  },
  // 去掉返回
  cancel: function(){
    wx.navigateBack({
      delta: 1,//返回上一页面
    })
  },
  // 分享
  share: function(){
    
    console.log("转发成功")
  },
  // 我的
  mine: function(){

    console.log("我的，跳转？")
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})