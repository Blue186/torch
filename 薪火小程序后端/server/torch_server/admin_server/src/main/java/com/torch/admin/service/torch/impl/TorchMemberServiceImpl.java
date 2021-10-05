package com.torch.admin.service.torch.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchMember;
import com.torch.admin.mapper.torch.TorchMemberMapper;
import com.torch.admin.service.torch.TorchMemberService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class TorchMemberServiceImpl extends ServiceImpl<TorchMemberMapper, TorchMember> implements TorchMemberService {
    @Override
    public Integer getIdByAccountCodeAndPassword(String accountCode, String password) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            QueryWrapper<TorchMember> wrapper = new QueryWrapper<>();
            wrapper.eq("account_code", decoder.decode(accountCode));
            wrapper.eq("password", decoder.decode(password));
            TorchMember torchMember = baseMapper.selectOne(wrapper);
            if (torchMember != null) return torchMember.getId();
        } catch (Exception e) {
            return -2;  // 多半是 base64 解码出现问题
        }
        return -1;
    }
}
