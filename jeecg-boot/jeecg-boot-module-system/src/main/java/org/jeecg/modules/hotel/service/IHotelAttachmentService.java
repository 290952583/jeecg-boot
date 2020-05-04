package org.jeecg.modules.hotel.service;

import org.jeecg.modules.hotel.entity.HotelAttachment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 酒店上传的附件(图片)信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
public interface IHotelAttachmentService extends IService<HotelAttachment> {

	public List<HotelAttachment> selectByMainId(String mainId);
}
