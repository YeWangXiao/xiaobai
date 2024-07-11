package com.tianlan.tlproject.demos.Domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class ProjectInfo {

    @TableId("pmid")
    private String pmid;


    @TableField("pmname")
    private String pmname;

    @TableField("pmcode")
    private String pmcode;

    @TableField("pmmanager")
    private String pmmanager;

    @TableField("pmmanagerid")
    private int pmmanagerid;

    @TableField("pmdate")
    private Date pmdate;

}
