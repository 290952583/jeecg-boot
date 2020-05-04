package org.jeecg.modules.hotel.service.impl;

import org.jeecg.modules.hotel.entity.HotelAttachment;
import org.jeecg.modules.hotel.mapper.HotelAttachmentMapper;
import org.jeecg.modules.hotel.service.IHotelAttachmentService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 酒店上传的附件(图片)信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Service
public class HotelAttachmentServiceImpl extends ServiceImpl<HotelAttachmentMapper, HotelAttachment> implements IHotelAttachmentService {
	
	@Autowired
	private HotelAttachmentMapper hotelAttachmentMapper;
	
	@Override
	public List<HotelAttachment> selectByMainId(String mainId) {
		return hotelAttachmentMapper.selectByMainId(mainId);
	}
}
