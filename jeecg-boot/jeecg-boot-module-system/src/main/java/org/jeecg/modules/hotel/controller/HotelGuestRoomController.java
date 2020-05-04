package org.jeecg.modules.hotel.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.service.IHotelGuestRoomService;

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
 * @Description: 酒店客房信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Api(tags="酒店客房信息")
@RestController
@RequestMapping("/hotel/hotelGuestRoom")
@Slf4j
public class HotelGuestRoomController extends JeecgController<HotelGuestRoom, IHotelGuestRoomService> {
	@Autowired
	private IHotelGuestRoomService hotelGuestRoomService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hotelGuestRoom
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "酒店客房信息-分页列表查询")
	@ApiOperation(value="酒店客房信息-分页列表查询", notes="酒店客房信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HotelGuestRoom hotelGuestRoom,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HotelGuestRoom> queryWrapper = QueryGenerator.initQueryWrapper(hotelGuestRoom, req.getParameterMap());
		Page<HotelGuestRoom> page = new Page<HotelGuestRoom>(pageNo, pageSize);
		IPage<HotelGuestRoom> pageList = hotelGuestRoomService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hotelGuestRoom
	 * @return
	 */
	@AutoLog(value = "酒店客房信息-添加")
	@ApiOperation(value="酒店客房信息-添加", notes="酒店客房信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HotelGuestRoom hotelGuestRoom) {
		hotelGuestRoomService.save(hotelGuestRoom);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param hotelGuestRoom
	 * @return
	 */
	@AutoLog(value = "酒店客房信息-编辑")
	@ApiOperation(value="酒店客房信息-编辑", notes="酒店客房信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HotelGuestRoom hotelGuestRoom) {
		hotelGuestRoomService.updateById(hotelGuestRoom);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "酒店客房信息-通过id删除")
	@ApiOperation(value="酒店客房信息-通过id删除", notes="酒店客房信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hotelGuestRoomService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "酒店客房信息-批量删除")
	@ApiOperation(value="酒店客房信息-批量删除", notes="酒店客房信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hotelGuestRoomService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "酒店客房信息-通过id查询")
	@ApiOperation(value="酒店客房信息-通过id查询", notes="酒店客房信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HotelGuestRoom hotelGuestRoom = hotelGuestRoomService.getById(id);
		if(hotelGuestRoom==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(hotelGuestRoom);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hotelGuestRoom
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HotelGuestRoom hotelGuestRoom) {
        return super.exportXls(request, hotelGuestRoom, HotelGuestRoom.class, "酒店客房信息");
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
        return super.importExcel(request, response, HotelGuestRoom.class);
    }

}
