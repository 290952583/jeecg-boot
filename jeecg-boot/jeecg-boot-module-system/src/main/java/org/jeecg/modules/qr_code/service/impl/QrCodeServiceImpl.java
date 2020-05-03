package org.jeecg.modules.qr_code.service.impl;

import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;
import org.jeecg.modules.qr_code.entity.QrCode;
import org.jeecg.modules.qr_code.mapper.QrCodeMapper;
import org.jeecg.modules.qr_code.service.IQrCodeService;
import org.springframework.stereotype.Service;

/**
 * @Description: 二维码表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Service
public class QrCodeServiceImpl extends JeecgServiceImpl<QrCodeMapper, QrCode> implements IQrCodeService {

}
