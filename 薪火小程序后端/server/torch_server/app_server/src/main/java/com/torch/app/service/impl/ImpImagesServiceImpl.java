package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ImpImages;
import com.torch.app.mapper.ImpImagesMapper;
import com.torch.app.service.ImpImagesService;
import org.springframework.stereotype.Service;

@Service
public class ImpImagesServiceImpl extends ServiceImpl<ImpImagesMapper, ImpImages> implements ImpImagesService {

}
