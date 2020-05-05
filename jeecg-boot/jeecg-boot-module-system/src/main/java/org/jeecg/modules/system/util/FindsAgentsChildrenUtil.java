package org.jeecg.modules.system.util;

import org.jeecg.common.system.vo.SysAgentModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysAgent;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.model.AgentIdModel;
import org.jeecg.modules.system.model.DepartIdModel;
import org.jeecg.modules.system.model.SysAgentTreeModel;
import org.jeecg.modules.system.model.SysDepartTreeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>
 * 对应代理商的表,处理并查找树级数据
 * <P>
 * 
 * @Author: 徐信
 * @Date: 2020-05-01
 */
public class FindsAgentsChildrenUtil {



    /**
     * queryTreeList的子方法 ====1=====
     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    public static List<SysAgentTreeModel> wrapTreeDataToTreeList(List<SysAgentModel> agentList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
    	    List<AgentIdModel> idList = new ArrayList<AgentIdModel>();
        List<SysAgentTreeModel> records = new ArrayList<>();
        for (int i = 0; i < agentList.size(); i++) {
            SysAgentModel sysAgentModel = agentList.get(i);
            records.add(new SysAgentTreeModel(sysAgentModel));
        }
        List<SysAgentTreeModel> tree = findChildren(records, idList);
        setEmptyChildrenAsNull(tree);
        return tree;
    }

    /**
     * 获取 DepartIdModel
     * @param recordList
     * @return
     */
    public static List<AgentIdModel> wrapTreeDataToDepartIdTreeList(List<SysAgentTreeModel> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
        List<AgentIdModel> idList = new ArrayList<AgentIdModel>();
        findChildren(recordList, idList);
        return idList;
    }

    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<SysAgentTreeModel> findChildren(List<SysAgentTreeModel> recordList,
                                                         List<AgentIdModel> agentIdList) {

        List<SysAgentTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysAgentTreeModel branch = recordList.get(i);
            if (oConvertUtils.isEmpty(branch.getParentId())) {
                treeList.add(branch);
                AgentIdModel agentIdModel = new AgentIdModel().convert(branch);
                agentIdList.add(agentIdModel);
            }
        }
        getGrandChildren(treeList,recordList,agentIdList);
        
        //idList = departIdList;
        return treeList;
    }

    /**
     * queryTreeList的子方法====3====
     *该方法是找到顶级父类下的所有子节点集合并封装到TreeList集合
     */
    private static void getGrandChildren(List<SysAgentTreeModel> treeList, List<SysAgentTreeModel> recordList, List<AgentIdModel> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysAgentTreeModel model = treeList.get(i);
            AgentIdModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                SysAgentTreeModel m = recordList.get(i1);
                if (m.getParentId()!=null && m.getParentId().equals(model.getId())) {
                    model.getChildren().add(m);
                    AgentIdModel dim = new AgentIdModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildren(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }

    }
    

    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNull(List<SysAgentTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysAgentTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setIsLeaf(true);
            }else{
                setEmptyChildrenAsNull(model.getChildren());
                model.setIsLeaf(false);
            }
        }
        // sysDepartTreeList = treeList;
    }
}
