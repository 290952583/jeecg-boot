package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.common.system.vo.SysAgentModel;
import org.jeecg.modules.system.entity.SysAgent;
import org.jeecg.modules.system.model.SysAgentTreeModel;

/**
 * @Description: 代理商
 * @Author: jeecg-boot
 * @Date:   2020-04-24
 * @Version: V1.0
 */
public interface SysAgentMapper extends BaseMapper<SysAgent> {

    List<SysAgentModel> queryAgentList(@Param("agentIds") List<String> agentIds, @Param("keyword") String keyword);

    SysAgentModel getByAreaIdAndAgentName(@Param("areaId") Integer areaId, @Param("agentName") String agentName);

    SysAgentModel findById(@Param("id") String id);

    List<SysAgent> selectAgentByHotelId(String hotelId);
}
