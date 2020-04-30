package com.example.helloworld.excelController;

import com.example.helloworld.excelService.SpringbootExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Author:aijiaxiang
 * Date:2020/3/9
 * Description:
 */
@Controller
public class SpringBootExcelController {
    @Autowired
    private SpringbootExcel springbootExcel;

    @RequestMapping("/exportExcel")
    public void export(HttpServletResponse httpServletResponse){
        try {
            springbootExcel.exportExcel(httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
