package com.zjs;

import lombok.Data;

import javax.persistence.*;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.AttachmentType;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.CodeEditorType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

//必须有@Erupt注解，才可以使用这个注解
//@EruptFlowForm
//@EruptDataSource("default")
@Erupt(name = "数据审核服务")
@Table(name = "data_audit_service")
@Entity
@Data
public class DataAuditService extends BaseModel {

//    //主键
//    @Id
//    @GeneratedValue(generator = "generator")
//    @GenericGenerator(name = "generator", strategy = "native")
//    @Column(name = "id")
//    @EruptField
//    private Long id; //如果继承BaseModel则不能再重复声明id

    @EruptField(
            views = @View(title = "工单编号"),
            edit = @Edit(title = "工单编号", search = @Search)
    )
    private String orderno;

    //TODO
    @EruptField(
            views = @View(title = "目标数据库"),
            edit = @Edit(
                    search = @Search,
                    title = "目标数据库", type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            fetchHandlerParams = {"select id, name from demo_ops_server", "5000"}
                    )
            )
    )
    private String dbname;

    @EruptField(
            views = @View(title = "SQL脚本文件"),
            edit = @Edit(title = "SQL脚本文件", type = EditType.ATTACHMENT,
                    attachmentType = @AttachmentType(fileTypes = ".sql"))
    )
    private String attachment;

    @Lob
    @EruptField(
            edit = @Edit(title = "SQL脚本",
                    type = EditType.CODE_EDITOR,
                    codeEditType = @CodeEditorType(language = "sql", height = 200))
    )
    private String content;
}
