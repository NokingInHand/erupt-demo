package com.zjs;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.NumberType;
import xyz.erupt.flow.core.annotation.EruptFlowForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//必须有@Erupt注解，才可以使用这个注解
@EruptFlowForm
@Erupt(name="请假")
@Entity
@Data
public class DemoLeave {

    //主键
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id; //如果继承BaseModel则不能再重复声明id

    @EruptField(
            views = @View(title = "请假人"),
            edit = @Edit(title = "请假人")
    )
    private String person;

    @EruptField(
            views = @View(title = "请假天数"),
            edit = @Edit(title = "请假天数", numberType = @NumberType(min = 1))
    )
    private Integer days = 1;
}