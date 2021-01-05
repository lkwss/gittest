package com.jk.service.Impl;

import com.jk.pojo.StudentBean;
import com.jk.pojo.TreeBean;
import com.jk.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addStu(StudentBean studentBean) {
        mongoTemplate.save(studentBean);
    }

    @Override
    public void deletestu(String id) {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query,StudentBean.class);
    }

    @Override
    public HashMap<String, Object> findstudent(Integer page, Integer rows, StudentBean studentBean) {
        Query query=new Query();
        long count = mongoTemplate.count(query, StudentBean.class);

        if(studentBean.getStuname()!=null &&studentBean.getStuname()!=""){
            query.addCriteria(Criteria.where("stuname").is(studentBean.getStuname()));
        }
        Sort orders =Sort.by(new Sort.Order(Sort.Direction.DESC,"age"));
        query.with(orders);
        Integer open=(page-1)*rows;
        query.skip(open).limit(rows);

        List<StudentBean> list = mongoTemplate.find(query, StudentBean.class);
        HashMap<String, Object> map=new HashMap<>();
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    @Override
    public List<TreeBean> findtree() {
        String pid="0";
        List<TreeBean> list = finds(pid);
        return list;
    }

    private List<TreeBean> finds(String pid) {
        Query query=new Query();
        query.addCriteria(Criteria.where("pid").is(pid));
        List<TreeBean> treeBeans = mongoTemplate.find(query, TreeBean.class);
        for (TreeBean tree:treeBeans) {
            String id = tree.getId();
            List<TreeBean> lists = finds(id);
            tree.setChildren(lists);
        }
        return treeBeans;
    }
    @Override
    public StudentBean findstubyid(String id) {
        StudentBean byId = mongoTemplate.findById(id, StudentBean.class);
        return byId;
    }

    @Override
    public void delBill(String array) {
        String[] split = array.split(",");
        for(int i=0;i<split.length;i++){
            deletestu(split[i]);
        }
    }
}
