package com.jk.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@Document(collection = "t_stu")
public class StudentBean {
    private String id;
    private String stuname;
    private Integer sex;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signup;


}
