package com.example.helloworld.excelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:aijiaxiang
 * Date:2020/3/9
 * Description:
 */
@Controller
public class ExportController {
    @Autowired
    private ExportService exportService;

    @RequestMapping("/down")
    public void down(HttpServletResponse response) throws Exception {

        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();





        for (int i=0;i<3;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("姓名",i);
            map.put("性别","男"+i);
            list.add(map);
        }


//        Map<String,Object> map1 = new HashMap<>();
//        map.put("姓名","李四");
//        map.put("性别","女");




        System.out.println(list.size()+"..."+list.toString());

        exportService.ExportExc(list,response);

    }
}
