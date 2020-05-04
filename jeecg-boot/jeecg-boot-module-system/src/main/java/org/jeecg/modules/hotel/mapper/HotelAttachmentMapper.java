package org.jeecg.modules.hotel.mapper;

import java.util.List;
import org.jeecg.modules.hotel.entity.HotelAttachment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 酒店上传的附件(图片)信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface HotelAttachmentMapper extends BaseMapper<HotelAttachment> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<HotelAttachment> selectByMainId(@Param("mainId") String mainId);
}
