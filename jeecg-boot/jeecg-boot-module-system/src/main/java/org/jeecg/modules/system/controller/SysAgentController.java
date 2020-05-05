package org.jeecg.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.imap.protocol.ID;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysAgentModel;
import org.jeecg.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.entity.SysAgent;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.model.SysAgentTreeModel;
import org.jeecg.modules.system.model.SysDepartTreeModel;
import org.jeecg.modules.system.service.ISysAgentService;
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
 * @Description: 代理商
 * @Author: jeecg-boot
 * @Date:   2020-04-24
 * @Version: V1.0
 */
@Api(tags="代理商")
@RestController
@RequestMapping("/system/sysAgent")
@Slf4j
public class SysAgentController extends JeecgController<SysAgent, ISysAgentService> {
	@Autowired
	private ISysAgentService sysAgentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysAgent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "代理商-分页列表查询")
	@ApiOperation(value="代理商-分页列表查询", notes="代理商-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysAgent sysAgent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysAgent> queryWrapper = QueryGenerator.initQueryWrapper(sysAgent, req.getParameterMap());
		Page<SysAgent> page = new Page<SysAgent>(pageNo, pageSize);
		IPage<SysAgent> pageList = sysAgentService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 查询数据 查出我的代理商,并以树结构数据格式响应给前端
	  *
	  * @return
	  */
	 @RequestMapping(value = "/queryMyAgentTreeList", method = RequestMethod.GET)
	 public Result<List<SysAgentTreeModel>> queryMyDeptTreeList() {
		 Result<List<SysAgentTreeModel>> result = new Result<>();
		 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 try {
		 	// 只有系统管理员和代理商才可查看代理商信息
			 if(oConvertUtils.isNotEmpty(user.getIdentity()) && (user.getIdentity() == CommonConstant.USER_IDENTITY_1  || user.getIdentity() == CommonConstant.USER_IDENTITY_2)){
				 List<SysAgentTreeModel> list = sysAgentService.queryMyDeptTreeList(user.getId(), "");
				 result.setResult(list);
				 result.setMessage(String.valueOf(user.getIdentity()));
				 result.setSuccess(true);
			 }else{
				 result.setMessage(CommonConstant.USER_IDENTITY_3.toString());
				 result.setSuccess(true);
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 return result;
	 }
	
	/**
	 *   添加
	 *
	 * @param sysAgent
	 * @return
	 */
	@AutoLog(value = "代理商-添加")
	@ApiOperation(value="代理商-添加", notes="代理商-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysAgentModel sysAgent, HttpServletRequest request) {
		Result<SysDepart> result = new Result<SysDepart>();
		String username = JwtUtil.getUserNameByToken(request);
		sysAgent.setUserName(username);
		sysAgentService.add(sysAgent);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param sysAgent
	 * @return
	 */
	@AutoLog(value = "代理商-编辑")
	@ApiOperation(value="代理商-编辑", notes="代理商-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysAgent sysAgent) {
		boolean existAgentName = sysAgentService.isExistAgentName(sysAgent.getId(), sysAgent.getAreaId(), sysAgent.getAgentName());
		if (existAgentName) throw new JeecgBootException("代理商名称已存在");
		sysAgentService.updateById(sysAgent);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代理商-通过id删除")
	@ApiOperation(value="代理商-通过id删除", notes="代理商-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysAgentService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "代理商-批量删除")
	@ApiOperation(value="代理商-批量删除", notes="代理商-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysAgentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代理商-通过id查询")
	@ApiOperation(value="代理商-通过id查询", notes="代理商-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysAgentModel sysAgentModel = sysAgentService.findById(id);
		if(sysAgentModel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(sysAgentModel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param sysAgent
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SysAgent sysAgent) {
        return super.exportXls(request, sysAgent, SysAgent.class, "代理商");
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
        return super.importExcel(request, response, SysAgent.class);
    }

}
