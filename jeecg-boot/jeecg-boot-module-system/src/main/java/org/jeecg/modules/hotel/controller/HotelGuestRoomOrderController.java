package org.jeecg.modules.hotel.controller;

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
import org.jeecg.modules.hotel.entity.HotelGuestRoomOrder;
import org.jeecg.modules.hotel.service.IHotelGuestRoomOrderService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 酒店客房订单表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Api(tags="酒店客房订单表")
@RestController
@RequestMapping("/hotel/hotelGuestRoomOrder")
@Slf4j
public class HotelGuestRoomOrderController extends JeecgController<HotelGuestRoomOrder, IHotelGuestRoomOrderService> {
	@Autowired
	private IHotelGuestRoomOrderService hotelGuestRoomOrderService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hotelGuestRoomOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "酒店客房订单表-分页列表查询")
	@ApiOperation(value="酒店客房订单表-分页列表查询", notes="酒店客房订单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HotelGuestRoomOrder hotelGuestRoomOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HotelGuestRoomOrder> queryWrapper = QueryGenerator.initQueryWrapper(hotelGuestRoomOrder, req.getParameterMap());
		Page<HotelGuestRoomOrder> page = new Page<HotelGuestRoomOrder>(pageNo, pageSize);
		IPage<HotelGuestRoomOrder> pageList = hotelGuestRoomOrderService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hotelGuestRoomOrder
	 * @return
	 */
	@AutoLog(value = "酒店客房订单表-添加")
	@ApiOperation(value="酒店客房订单表-添加", notes="酒店客房订单表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HotelGuestRoomOrder hotelGuestRoomOrder) {
		hotelGuestRoomOrderService.crateOrder(hotelGuestRoomOrder);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param hotelGuestRoomOrder
	 * @return
	 */
	@AutoLog(value = "酒店客房订单表-编辑")
	@ApiOperation(value="酒店客房订单表-编辑", notes="酒店客房订单表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HotelGuestRoomOrder hotelGuestRoomOrder) {
		hotelGuestRoomOrderService.updateById(hotelGuestRoomOrder);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "酒店客房订单表-通过id删除")
	@ApiOperation(value="酒店客房订单表-通过id删除", notes="酒店客房订单表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hotelGuestRoomOrderService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "酒店客房订单表-批量删除")
	@ApiOperation(value="酒店客房订单表-批量删除", notes="酒店客房订单表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hotelGuestRoomOrderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "酒店客房订单表-通过id查询")
	@ApiOperation(value="酒店客房订单表-通过id查询", notes="酒店客房订单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HotelGuestRoomOrder hotelGuestRoomOrder = hotelGuestRoomOrderService.getById(id);
		if(hotelGuestRoomOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(hotelGuestRoomOrder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hotelGuestRoomOrder
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HotelGuestRoomOrder hotelGuestRoomOrder) {
        return super.exportXls(request, hotelGuestRoomOrder, HotelGuestRoomOrder.class, "酒店客房订单表");
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
        return super.importExcel(request, response, HotelGuestRoomOrder.class);
    }

}
