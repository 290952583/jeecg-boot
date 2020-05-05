package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.SysUserAgent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 用户代理人设置
 * @Author: jeecg-boot
 * @Date:  2019-04-17
 * @Version: V1.0
 */
public interface SysUserAgentMapper extends BaseMapper<SysUserAgent> {

    /**
     * 查询用户拥有的代理商
     * @param userId 用户id
     * @return
     */
    List<String> queryAgentIds(@Param("userId") String userId);

}
