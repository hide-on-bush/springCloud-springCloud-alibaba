/*
 * Copyright(C) 2019 FUYUN DATA SERVICES CO.;LTD. - All Rights Reserved
 * Unauthorized copying of this file; via any medium is strictly prohibited
 * Proprietary and confidential
 * 该源代码版权归属福韵数据服务有限公司所有
 * 未经授权，任何人不得复制、泄露、转载、使用，否则将视为侵权
 */
package com.atguigu.springcloud.service;

import com.atguigu.springcloud.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:夏世雄
 * @Date: 2020/12/4 14:39
 * @Version: 1.0
 * @E-mail: xiashixiong@fy-data.com
 * @Discription:
 **/
@Service
public class StorageServiceImpl  implements StorageService{

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public boolean updateStorage(Long productId, Integer productNum) {
        return storageMapper.updateStorage(productId,productNum) == 1;
    }
}
