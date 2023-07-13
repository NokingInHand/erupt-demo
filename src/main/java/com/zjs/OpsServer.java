package com.zjs;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.LinkTree;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.ViewType;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTreeType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.upms.handler.DictCodeChoiceFetchHandler;
import xyz.erupt.upms.model.base.HyperModel;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "demo_ops_server")
@Erupt(
        name = "OpsServer",
        linkTree = @LinkTree(field = "tree", dependNode = false)
)
public class OpsServer extends HyperModel {

    @ManyToOne
    //如果 linkTree 配置中 dependNode 值为 true 可以不声明 @EruptField 新增时会自动填充当前选择树节点
    @EruptField(
            views =  @View(title = "所属组别", column = "name"),
            edit = @Edit(
                    title = "所属组别", type = EditType.REFERENCE_TREE,
                    referenceTreeType = @ReferenceTreeType(pid = "parent.id")
            )
    )
    private OpsServerGroup tree;

    @EruptField(
            views = @View(title = "名称"),
            edit = @Edit(title = "名称", notNull = true, search = @Search)
    )
    private String name;

    @EruptField(
            views = @View(title = "地址"),
            edit = @Edit(title = "地址", notNull = true, search = @Search)
    )
    private String address;

    @EruptField(
            views = @View(title = "端口"),
            edit = @Edit(title = "端口", notNull = true)
    )
    private Integer port;

    @EruptField(
            views = @View(title = "驱动包名"),
            edit = @Edit(title = "驱动包名",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = DictCodeChoiceFetchHandler.class,
                            //参数一必填，需替换成实际的字典编码
                            //参数二可不填，表示缓存时间，默认为3000毫秒
                            fetchHandlerParams = {"dbDriverType", "5000"}
                    ))
    )
    private String dbdrivertype;

    @EruptField(
            views = @View(title = "用户名"),
            edit = @Edit(title = "用户名", notNull = true)
    )
    private String username;

    @EruptField(
            edit = @Edit(title = "密码", inputType = @InputType(type = "password"))
    )
    private String password;

    @EruptField(
            views = @View(title = "显示顺序"),
            edit = @Edit(title = "显示顺序")
    )
    private Integer sort;

    @Lob
    @EruptField(
            views = @View(title = "备注", type = ViewType.HTML),
            edit = @Edit(
                    title = "备注"
            )
    )
    private String remark;
}