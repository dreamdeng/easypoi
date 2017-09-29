package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import domain.entity.User;
import service.IUserService;

/**
 * 
 * @author xiaodeng
 *
 */

@Controller
@RequestMapping(value = "")
public class UserController {

    @Autowired
    private IUserService mUserService;

    /**
     * 上传Excel
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String upload(ModelMap map) {
        map.addAttribute("list", mUserService.getList());
        return "index";
    }

    /**
     * 导入Excel
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importExcel(MultipartFile file) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedSave(false);
        List<User> list = ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);
        mUserService.save(list);
        return "redirect:/";
    }

    /**
     * 导出Excel
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("content-Type", "application/data-excel");
        response.setHeader("Content-Disposition", "attachment;filename=Comment.xls");
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setAge(18);
        user.setId(1);
        user.setName("xiaodeng");
        user.setCreateTime(null);
        list.add(user);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), User.class, list);
        workbook.write(response.getOutputStream());
    }

}
