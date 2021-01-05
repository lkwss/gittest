package com.jk.controller;

import com.jk.pojo.StudentBean;
import com.jk.pojo.TreeBean;
import com.jk.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/goss")
    public String goss(){
        return "ss";
    }

    @RequestMapping("/gosignup")
    public String gosignup(){
        return "signup";
    }
    @RequestMapping("/findstu")
    public String findstu(){
        return "userlist";
    }
    @RequestMapping("/addstu")
    @ResponseBody
    public void addStu(StudentBean studentBean){
        studentService.addStu(studentBean);
    }
    @RequestMapping("/deletestu")
    @ResponseBody
    public void deletestu(String id){
        studentService.deletestu(id);
    }
    @RequestMapping("/findstudent")
    @ResponseBody
    public HashMap<String,Object> findstudent(Integer page,Integer rows,StudentBean studentBean){
        return studentService.findstudent(page,rows,studentBean);
    }
    @RequestMapping("/findtree")
    @ResponseBody
    public List<TreeBean> findtree(){
        return studentService.findtree();
    }
    @RequestMapping("/findstubyid")
    @ResponseBody
    public StudentBean findstubyid(String id){
        return studentService.findstubyid(id);
    }
    @RequestMapping("/delBill")
    @ResponseBody
    public void delBill(String array){
        studentService.delBill(array);
    }
}
