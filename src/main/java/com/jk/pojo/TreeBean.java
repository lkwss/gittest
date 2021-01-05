package com.jk.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "t_tree")
public class TreeBean {
    @Id
    private String id;
    private String text;
    private String url;
    private String pid;
    private List<TreeBean> children;
    private Boolean checked;
}
