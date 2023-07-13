package com.zjs;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.LinkTree;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTreeType;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "demo_tree_erupt_test")
@Erupt(
        name = "Erupt",
        linkTree = @LinkTree(field = "tree") //field 的值为类中支持树组件字段
)
public class EruptTest extends BaseModel {

    @ManyToOne
    //如果 linkTree 配置中 dependNode 值为 true 可以不声明 @EruptField 新增时会自动填充当前选择树节点
    @EruptField(
            views =  @View(title = "树节点选择", column = "name"),
            edit = @Edit(
                    title = "树节点选择", type = EditType.REFERENCE_TREE,
                    referenceTreeType = @ReferenceTreeType(pid = "parent.id", expandLevel = 1)
            )
    )
    private TreeDemo tree;

}