package com.gym.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.dao.FcUserCollectionMapper;
import com.gym.entity.FcUserCard;
import com.gym.entity.FcUserCollection;
import com.gym.vo.FcUserBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserCollectionService extends ServiceImpl<FcUserCollectionMapper, FcUserCollection> {

    @Resource
    FcUserCollectionMapper userCollectionMapper;

    public void saveUserCollection(Integer userId, Integer gymId) {
        FcUserCollection fcUserCollection = new FcUserCollection();
        fcUserCollection.setUserId(userId);
        fcUserCollection.setGymId(gymId);
        userCollectionMapper.insert(fcUserCollection);
    }
}
