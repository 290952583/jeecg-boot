package org.jeecg.modules.hotel.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.hotel.entity.HotelGuestRoom;
import org.jeecg.modules.hotel.entity.HotelAttachment;
import org.jeecg.modules.hotel.entity.Hotel;
import org.jeecg.modules.hotel.vo.HotelPage;
import org.jeecg.modules.hotel.service.IHotelService;
import org.jeecg.modules.hotel.service.IHotelGuestRoomService;
import org.jeecg.modules.hotel.service.IHotelAttachmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 商家/酒店信息
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Api(tags="商家/酒店信息")
@RestController
@RequestMapping("/hotel/hotel")
@Slf4j
public class HotelController {
	@Autowired
	private IHotelService hotelService;
	@Autowired
	private IHotelGuestRoomService hotelGuestRoomService;
	@Autowired
	private IHotelAttachmentService hotelAttachmentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hotel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商家/酒店信息-分页列表查询")
	@ApiOperation(value="商家/酒店信息-分页列表查询", notes="商家/酒店信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Hotel hotel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Hotel> queryWrapper = QueryGenerator.initQueryWrapper(hotel, req.getParameterMap());
		Page<Hotel> page = new Page<Hotel>(pageNo, pageSize);
		IPage<Hotel> pageList = hotelService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hotelPage
	 * @return
	 */
	@AutoLog(value = "商家/酒店信息-添加")
	@ApiOperation(value="商家/酒店信息-添加", notes="商家/酒店信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HotelPage hotelPage) {
		Hotel hotel = new Hotel();
		BeanUtils.copyProperties(hotelPage, hotel);
		hotelService.saveMain(hotel, hotelPage.getHotelGuestRoomList(),hotelPage.getHotelAttachmentList());
		return Result.ok("添加成功！");
	}

	 /**
	  *   修改状态
	  *
	  * @param hotelId id
	  * @param status 状态
	  * @return
	  */
	 @AutoLog(value = "商家/酒店信息-修改状态")
	 @ApiOperation(value="商家/酒店信息-修改状态", notes="商家/酒店信息-修改状态")
	 @PostMapping(value = "/status")
	 public Result<?> status(String hotelId, Integer status) {
		 hotelService.updateStatus(hotelId, status);
		 return Result.ok("！");
	 }


	
	/**
	 *  编辑
	 *
	 * @param hotelPage
	 * @return
	 */
	@AutoLog(value = "商家/酒店信息-编辑")
	@ApiOperation(value="商家/酒店信息-编辑", notes="商家/酒店信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HotelPage hotelPage) {
		Hotel hotel = new Hotel();
		BeanUtils.copyProperties(hotelPage, hotel);
		Hotel hotelEntity = hotelService.getById(hotel.getId());
		if(hotelEntity==null) {
			return Result.error("未找到对应数据");
		}
		hotelService.updateMain(hotel, hotelPage.getHotelGuestRoomList(),hotelPage.getHotelAttachmentList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商家/酒店信息-通过id删除")
	@ApiOperation(value="商家/酒店信息-通过id删除", notes="商家/酒店信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hotelService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商家/酒店信息-批量删除")
	@ApiOperation(value="商家/酒店信息-批量删除", notes="商家/酒店信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hotelService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商家/酒店信息-通过id查询")
	@ApiOperation(value="商家/酒店信息-通过id查询", notes="商家/酒店信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Hotel hotel = hotelService.getById(id);
		if(hotel==null) {
			return Result.error("未找到对应数据");
		}
		if(!Hotel.HotelStatus.ALREADY_EXAMINE.getStatus().equals(hotel.getStatus())){
			return Result.error("账号异常：" + Hotel.HotelStatus.valueByStatus(hotel.getStatus()).getName());
		}
		return Result.ok(hotel);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "酒店客房信息集合-通过id查询")
	@ApiOperation(value="酒店客房信息集合-通过id查询", notes="酒店客房信息-通过id查询")
	@GetMapping(value = "/queryHotelGuestRoomByMainId")
	public Result<?> queryHotelGuestRoomListByMainId(@RequestParam(name="id",required=true) String id) {
		List<HotelGuestRoom> hotelGuestRoomList = hotelGuestRoomService.selectByMainId(id);
		return Result.ok(hotelGuestRoomList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "酒店上传的附件(图片)信息集合-通过id查询")
	@ApiOperation(value="酒店上传的附件(图片)信息集合-通过id查询", notes="酒店上传的附件(图片)信息-通过id查询")
	@GetMapping(value = "/queryHotelAttachmentByMainId")
	public Result<?> queryHotelAttachmentListByMainId(@RequestParam(name="id",required=true) String id) {
		List<HotelAttachment> hotelAttachmentList = hotelAttachmentService.selectByMainId(id);
		return Result.ok(hotelAttachmentList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hotel
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Hotel hotel) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<Hotel> queryWrapper = QueryGenerator.initQueryWrapper(hotel, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<Hotel> queryList = hotelService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<Hotel> hotelList = new ArrayList<Hotel>();
      if(oConvertUtils.isEmpty(selections)) {
          hotelList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          hotelList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<HotelPage> pageList = new ArrayList<HotelPage>();
      for (Hotel main : hotelList) {
          HotelPage vo = new HotelPage();
          BeanUtils.copyProperties(main, vo);
          List<HotelGuestRoom> hotelGuestRoomList = hotelGuestRoomService.selectByMainId(main.getId());
          vo.setHotelGuestRoomList(hotelGuestRoomList);
          List<HotelAttachment> hotelAttachmentList = hotelAttachmentService.selectByMainId(main.getId());
          vo.setHotelAttachmentList(hotelAttachmentList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "商家/酒店信息列表");
      mv.addObject(NormalExcelConstants.CLASS, HotelPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商家/酒店信息数据", "导出人:"+sysUser.getRealname(), "商家/酒店信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<HotelPage> list = ExcelImportUtil.importExcel(file.getInputStream(), HotelPage.class, params);
              for (HotelPage page : list) {
                  Hotel po = new Hotel();
                  BeanUtils.copyProperties(page, po);
                  hotelService.saveMain(po, page.getHotelGuestRoomList(),page.getHotelAttachmentList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
    }

}
