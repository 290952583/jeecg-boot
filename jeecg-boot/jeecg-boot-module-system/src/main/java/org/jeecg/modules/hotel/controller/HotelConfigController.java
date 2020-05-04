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
import org.jeecg.modules.hotel.entity.HotelConfig;
import org.jeecg.modules.hotel.vo.HotelConfigPage;
import org.jeecg.modules.hotel.service.IHotelConfigService;
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
 * @Description: 商品的客房配置主表
 * @Author: jeecg-boot
 * @Date:   2020-05-04
 * @Version: V1.0
 */
@Api(tags="商品的客房配置主表")
@RestController
@RequestMapping("/hotel/hotelConfig")
@Slf4j
public class HotelConfigController {
	@Autowired
	private IHotelConfigService hotelConfigService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hotelConfig
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商品的客房配置主表-分页列表查询")
	@ApiOperation(value="商品的客房配置主表-分页列表查询", notes="商品的客房配置主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(HotelConfig hotelConfig,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HotelConfig> queryWrapper = QueryGenerator.initQueryWrapper(hotelConfig, req.getParameterMap());
		Page<HotelConfig> page = new Page<HotelConfig>(pageNo, pageSize);
		IPage<HotelConfig> pageList = hotelConfigService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param hotelConfigPage
	 * @return
	 */
	@AutoLog(value = "商品的客房配置主表-添加")
	@ApiOperation(value="商品的客房配置主表-添加", notes="商品的客房配置主表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody HotelConfigPage hotelConfigPage) {
		HotelConfig hotelConfig = new HotelConfig();
		BeanUtils.copyProperties(hotelConfigPage, hotelConfig);
		hotelConfigService.save(hotelConfig);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param hotelConfigPage
	 * @return
	 */
	@AutoLog(value = "商品的客房配置主表-编辑")
	@ApiOperation(value="商品的客房配置主表-编辑", notes="商品的客房配置主表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody HotelConfigPage hotelConfigPage) {
		HotelConfig hotelConfig = new HotelConfig();
		BeanUtils.copyProperties(hotelConfigPage, hotelConfig);
		HotelConfig hotelConfigEntity = hotelConfigService.getById(hotelConfig.getId());
		if(hotelConfigEntity==null) {
			return Result.error("未找到对应数据");
		}
		hotelConfigService.updateById(hotelConfig);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品的客房配置主表-通过id删除")
	@ApiOperation(value="商品的客房配置主表-通过id删除", notes="商品的客房配置主表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hotelConfigService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品的客房配置主表-批量删除")
	@ApiOperation(value="商品的客房配置主表-批量删除", notes="商品的客房配置主表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hotelConfigService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品的客房配置主表-通过id查询")
	@ApiOperation(value="商品的客房配置主表-通过id查询", notes="商品的客房配置主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		HotelConfig hotelConfig = hotelConfigService.getById(id);
		if(hotelConfig==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(hotelConfig);

	}
	

    /**
    * 导出excel
    *
    * @param request
    * @param hotelConfig
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HotelConfig hotelConfig) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<HotelConfig> queryWrapper = QueryGenerator.initQueryWrapper(hotelConfig, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<HotelConfig> queryList = hotelConfigService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<HotelConfig> hotelConfigList = new ArrayList<HotelConfig>();
      if(oConvertUtils.isEmpty(selections)) {
          hotelConfigList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          hotelConfigList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<HotelConfigPage> pageList = new ArrayList<HotelConfigPage>();
      for (HotelConfig main : hotelConfigList) {
          HotelConfigPage vo = new HotelConfigPage();
          BeanUtils.copyProperties(main, vo);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "商品的客房配置主表列表");
      mv.addObject(NormalExcelConstants.CLASS, HotelConfigPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商品的客房配置主表数据", "导出人:"+sysUser.getRealname(), "商品的客房配置主表"));
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
              List<HotelConfigPage> list = ExcelImportUtil.importExcel(file.getInputStream(), HotelConfigPage.class, params);
              for (HotelConfigPage page : list) {
                  HotelConfig po = new HotelConfig();
                  BeanUtils.copyProperties(page, po);
                  hotelConfigService.save(po);
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
