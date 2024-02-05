package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: chen
 * Time: 2024/1/28 23:06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
