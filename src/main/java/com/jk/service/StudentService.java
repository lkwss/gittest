package com.jk.service;

import com.jk.pojo.StudentBean;
import com.jk.pojo.TreeBean;

import java.util.HashMap;
import java.util.List;

public interface StudentService {
    void addStu(StudentBean studentBean);

    void deletestu(String id);

    HashMap<String, Object> findstudent(Integer page, Integer rows, StudentBean studentBean);

    List<TreeBean> findtree();

    StudentBean findstubyid(String id);

    void delBill(String array);
}
