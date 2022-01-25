package com.torch.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.torch.app.entity.Activity;
import com.torch.app.entity.ActivityChild;
import com.torch.app.entity.ActivityTimes;
import com.torch.app.entity.SignUp;
import com.torch.app.entity.vo.SignUpCon.Sign;
import com.torch.app.entity.vo.SignUpCon.SignUpInfo;
import com.torch.app.entity.vo.SignUpCon.SignUpInfoNot;
import com.torch.app.mapper.SignUpMapper;
import com.torch.app.service.ActivityChildService;
import com.torch.app.service.ActivityService;
import com.torch.app.service.ActivityTimesService;
import com.torch.app.service.SignUpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SignUpServiceImpl extends ServiceImpl<SignUpMapper, SignUp> implements SignUpService {

    @Resource
    private SignUpMapper signUpMapper;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityChildService activityChildService;
    @Resource
    private ActivityTimesService activityTimesService;

    @Override
    public List<?> getSignUpInfo(Integer uid,Integer done) {
        QueryWrapper<SignUp> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        wrapper.eq("is_over",done);
        wrapper.orderByDesc("create_time");
        List<SignUp> signUps = baseMapper.selectList(wrapper);
        if (done==1){//已完成志愿活动
            List<SignUpInfo> signUpInfoList = new ArrayList<>();
            for (SignUp signUp : signUps) {
                Activity activity = activityService.getBaseMapper().selectById(signUp.getActId());
                ActivityChild activityChild = activityChildService.getBaseMapper().selectById(signUp.getActChiId());
                ActivityTimes activityTimes = activityTimesService.getBaseMapper().selectById(signUp.getActTimesId());
                SignUpInfo signUpInfo = new SignUpInfo();
                signUpInfo.setId(signUp.getId());
                signUpInfo.setActId(signUp.getActId());
                signUpInfo.setActChiId(signUp.getActChiId());
                signUpInfo.setIsOver(signUp.getIsOver());
                signUpInfo.setUserId(signUp.getUserId());
                signUpInfo.setImpWrote(signUp.getImpWrote());
                signUpInfo.setCreateTime(signUp.getCreateTime());
                signUpInfo.setActTimesId(signUp.getActTimesId());
                signUpInfo.setName(activity.getName());
                signUpInfo.setVolTime(activityTimes.getVolTime());
                signUpInfo.setServicePeriod(activityChild.getServicePeriod());
                signUpInfoList.add(signUpInfo);
            }
            return signUpInfoList;
        }else {//未完成志愿活动
            List<SignUpInfoNot> signUpInfoNot = new ArrayList<>();
            for (SignUp signUp : signUps) {
                Activity activity = activityService.getBaseMapper().selectById(signUp.getActId());
                ActivityChild activityChild = activityChildService.getBaseMapper().selectById(signUp.getActChiId());
                ActivityTimes activityTimes = activityTimesService.getBaseMapper().selectById(signUp.getActTimesId());
                SignUpInfoNot infoNot = new SignUpInfoNot();
                infoNot.setVolTime(activityTimes.getVolTime());
                infoNot.setServicePeriod(activityChild.getServicePeriod());
                infoNot.setId(activity.getId());
                infoNot.setName(activity.getName());
                infoNot.setCreateTime(activity.getCreateTime());
                infoNot.setAddress(activity.getAddress());
                infoNot.setActImage(activity.getActImage());
                infoNot.setAttention(activity.getAttention());
                infoNot.setContent(activity.getContent());
                infoNot.setCreaterId(activity.getCreaterId());
                infoNot.setDeadline(activity.getDeadline());
                infoNot.setHeadcount(activity.getHeadcount());
                infoNot.setIdentifier(activity.getIdentifier());
                infoNot.setIsPass(activity.getIsPass());
                infoNot.setOrganizer(activity.getOrganizer());
                infoNot.setPassTime(activity.getPassTime());
                infoNot.setQqNumber(activity.getQqNumber());
                infoNot.setRemarks(activity.getRemarks());
                infoNot.setTotalNumber(activity.getTotalNumber());
                infoNot.setVolTimeDesc(activity.getVolTimeDesc());
                infoNot.setVolTimePeriod(activity.getVolTimePeriod());
                signUpInfoNot.add(infoNot);
            }
            return signUpInfoNot;
        }
    }

    @Override
    public Boolean satisfySign(Integer uid, Sign sign) {
        boolean flag = true;
        ActivityTimes signTime = activityTimesService.getBaseMapper().selectById(sign.getActTimesId());//报名时间
        Long startTime = signTime.getStartTime();//开始时间
        Long endTime = signTime.getEndTime();//结束时间
        QueryWrapper<SignUp> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        wrapper.eq("is_over",0);
        List<SignUp> signUps = baseMapper.selectList(wrapper);//那到报名信息（未完成的），然后再拿到时间段
        for (SignUp signUp : signUps) {
            ActivityTimes activityTimes = activityTimesService.getBaseMapper().selectById(signUp.getActTimesId());//每一个时间段
            if ((activityTimes.getStartTime()<endTime&&activityTimes.getEndTime()>endTime)||(activityTimes.getStartTime()<startTime&&activityTimes.getEndTime()>startTime)){
                flag = false;
            }
            if (signUp.getActTimesId().equals(sign.getActTimesId())){
                flag = false;
            }
        }
        return flag;
    }
}
