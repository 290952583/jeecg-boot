package org.jeecg.modules.qr_code.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.qr_code.entity.QrCode;
import org.jeecg.modules.qr_code.service.IQrCodeService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 二维码表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Api(tags="二维码表")
@RestController
@RequestMapping("/qr_code/qrCode")
@Slf4j
public class QrCodeController extends JeecgController<QrCode, IQrCodeService> {
	@Autowired
	private IQrCodeService qrCodeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param qrCode
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "二维码表-分页列表查询")
	@ApiOperation(value="二维码表-分页列表查询", notes="二维码表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(QrCode qrCode,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<QrCode> queryWrapper = QueryGenerator.initQueryWrapper(qrCode, req.getParameterMap());
		Page<QrCode> page = new Page<QrCode>(pageNo, pageSize);
		IPage<QrCode> pageList = qrCodeService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param qrCode
	 * @return
	 */
	@AutoLog(value = "二维码表-添加")
	@ApiOperation(value="二维码表-添加", notes="二维码表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody QrCode qrCode) {
		qrCodeService.save(qrCode);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param qrCode
	 * @return
	 */
	@AutoLog(value = "二维码表-编辑")
	@ApiOperation(value="二维码表-编辑", notes="二维码表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody QrCode qrCode) {
		qrCodeService.updateById(qrCode);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "二维码表-通过id删除")
	@ApiOperation(value="二维码表-通过id删除", notes="二维码表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qrCodeService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "二维码表-批量删除")
	@ApiOperation(value="二维码表-批量删除", notes="二维码表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qrCodeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "二维码表-通过id查询")
	@ApiOperation(value="二维码表-通过id查询", notes="二维码表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		QrCode qrCode = qrCodeService.getById(id);
		if(qrCode==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(qrCode);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param qrCode
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, QrCode qrCode) {
        return super.exportXls(request, qrCode, QrCode.class, "二维码表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, QrCode.class);
    }

}
