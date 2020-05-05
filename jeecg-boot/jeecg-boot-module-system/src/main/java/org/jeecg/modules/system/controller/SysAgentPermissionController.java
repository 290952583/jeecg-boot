package org.jeecg.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.*;
import org.jeecg.modules.system.model.TreeModel;
import org.jeecg.modules.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
* @Description: 代理商权限表
* @Author: jeecg-boot
* @Date:   2020-02-11
* @Version: V1.0
*/
@Slf4j
@Api(tags="代理商权限表")
@RestController
@RequestMapping("/sys/sysAgentPermission")
public class SysAgentPermissionController extends JeecgController<SysAgentPermission, ISysAgentPermissionService> {
   @Autowired
   private ISysAgentPermissionService sysAgentPermissionService;

    @Autowired
    private ISysPermissionDataRuleService sysPermissionDataRuleService;

    @Autowired
    private ISysPermissionService sysPermissionService;

   /**
    * 分页列表查询
    *
    * @param sysAgentPermission
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "代理商权限表-分页列表查询")
   @ApiOperation(value="代理商权限表-分页列表查询", notes="代理商权限表-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(SysAgentPermission sysAgentPermission,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<SysAgentPermission> queryWrapper = QueryGenerator.initQueryWrapper(sysAgentPermission, req.getParameterMap());
       Page<SysAgentPermission> page = new Page<SysAgentPermission>(pageNo, pageSize);
       IPage<SysAgentPermission> pageList = sysAgentPermissionService.page(page, queryWrapper);
       return Result.ok(pageList);
   }

   /**
   * 代理商管理授权查询数据规则数据
   */
   @GetMapping(value = "/datarule/{permissionId}/{agentId}")
   public Result<?> loadDatarule(@PathVariable("permissionId") String permissionId,@PathVariable("agentId") String agentId) {
       List<SysPermissionDataRule> list = sysPermissionDataRuleService.getPermRuleListByPermId(permissionId);
       if(list==null || list.size()==0) {
           return Result.error("未找到权限配置信息");
       }else {
           Map<String,Object> map = new HashMap<>();
           map.put("datarule", list);
           LambdaQueryWrapper<SysAgentPermission> query = new LambdaQueryWrapper<SysAgentPermission>()
                .eq(SysAgentPermission::getPermissionId, permissionId)
                .eq(SysAgentPermission::getAgentId,agentId);
           SysAgentPermission sysAgentPermission = sysAgentPermissionService.getOne(query);
           if(sysAgentPermission==null) {
            //return Result.error("未找到角色菜单配置信息");
           }else {
               String drChecked = sysAgentPermission.getDataRuleIds();
               if(oConvertUtils.isNotEmpty(drChecked)) {
                   map.put("drChecked", drChecked.endsWith(",")?drChecked.substring(0, drChecked.length()-1):drChecked);
               }
           }
           return Result.ok(map);
           //TODO 以后按钮权限的查询也走这个请求 无非在map中多加两个key
       }
   }

   /**
   * 保存数据规则至代理商菜单关联表
   */
   @PostMapping(value = "/datarule")
   public Result<?> saveDatarule(@RequestBody JSONObject jsonObject) {
       try {
           String permissionId = jsonObject.getString("permissionId");
           String agentId = jsonObject.getString("agentId");
           String dataRuleIds = jsonObject.getString("dataRuleIds");
           log.info("保存数据规则>>"+"菜单ID:"+permissionId+"代理商ID:"+ agentId+"数据权限ID:"+dataRuleIds);
           LambdaQueryWrapper<SysAgentPermission> query = new LambdaQueryWrapper<SysAgentPermission>()
                .eq(SysAgentPermission::getPermissionId, permissionId)
                .eq(SysAgentPermission::getAgentId, agentId);
           SysAgentPermission sysAgentPermission = sysAgentPermissionService.getOne(query);
           if(sysAgentPermission==null) {
               return Result.error("请先保存代理商菜单权限!");
           }else {
               sysAgentPermission.setDataRuleIds(dataRuleIds);
                this.sysAgentPermissionService.updateById(sysAgentPermission);
           }
       } catch (Exception e) {
            log.error("SysDepartPermissionController.saveDatarule()发生异常：" + e.getMessage(),e);
            return Result.error("保存失败");
       }
       return Result.ok("保存成功!");
   }

    /**
     * 用户角色授权功能，查询菜单权限树
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryTreeListForDeptRole", method = RequestMethod.GET)
    public Result<Map<String,Object>> queryTreeListForDeptRole(@RequestParam(name="agentId",required=true) String agentId,HttpServletRequest request) {
        Result<Map<String,Object>> result = new Result<>();
        //全部权限ids
        List<String> ids = new ArrayList<>();
        try {
            LambdaQueryWrapper<SysPermission> query = new LambdaQueryWrapper<SysPermission>();
            query.eq(SysPermission::getDelFlag, CommonConstant.DEL_FLAG_0);
            query.orderByAsc(SysPermission::getSortNo);
            query.inSql(SysPermission::getId,"select permission_id  from sys_agent_permission where agent_id='"+agentId+"'");
            List<SysPermission> list = sysPermissionService.list(query);
            for(SysPermission sysPer : list) {
                ids.add(sysPer.getId());
            }
            List<TreeModel> treeList = new ArrayList<>();
            getTreeModelList(treeList, list, null);
            Map<String,Object> resMap = new HashMap<String,Object>();
            resMap.put("treeList", treeList); //全部树节点数据
            resMap.put("ids", ids);//全部树ids
            result.setResult(resMap);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private void getTreeModelList(List<TreeModel> treeList, List<SysPermission> metaList, TreeModel temp) {
        for (SysPermission permission : metaList) {
            String tempPid = permission.getParentId();
            TreeModel tree = new TreeModel(permission.getId(), tempPid, permission.getName(),permission.getRuleFlag(), permission.isLeaf());
            if(temp==null && oConvertUtils.isEmpty(tempPid)) {
                treeList.add(tree);
                if(!tree.getIsLeaf()) {
                    getTreeModelList(treeList, metaList, tree);
                }
            }else if(temp!=null && tempPid!=null && tempPid.equals(temp.getKey())){
                temp.getChildren().add(tree);
                if(!tree.getIsLeaf()) {
                    getTreeModelList(treeList, metaList, tree);
                }
            }

        }
    }

}
