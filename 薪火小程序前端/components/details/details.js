// components/details/details.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    statusImage: ['/images/形状 3 拷贝.png',],
    status:"招募中",
    backgroundImage:[],
    missionDetails:"解放碑街道清理",
    locationText:"解放碑",
    time:"2月30号 13:00-15:00",
    peopleNum: 5,
  },

  /**
   * 组件的方法列表，方法要写在methods里面哦
   */
  methods: {
    butDetails: function () {
      wx.navigateTo({
        url: '/pages/innerDetails/innerDetails',
      })
    }
  },
})
