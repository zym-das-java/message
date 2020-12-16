package com.message.web;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.message.common.PageUtil;
import com.message.common.ReaderExcel;
import com.message.common.ResultUtil;
import com.message.common.WriteExcelDemo05;
import com.message.dao.SampleInfoMapper;
import com.message.pojo.*;
import com.message.service.SampleInfoService;
import com.message.vo.SampleInfoVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("find")
public class SampleInfoController {

    @Autowired
    private SampleInfoService sampleInfoService;

    @RequestMapping("All")
    public String findAllSampleInfo(Model model,
                                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                   String search,String pollutionRate,  Integer toxinType, Integer breed,
                                    Integer province, Integer city, Integer county,String year,String month, String day){

        PageUtil list = sampleInfoService.findAllSampleInfo(pageNum,
                pageSize,search,pollutionRate, toxinType,breed,province,city,county,year,month,day);
        model.addAttribute("list",list);
        return "IM";
    }

    @RequestMapping("findToxinType")
    @ResponseBody
    public List<SampleToxinInfo> findToxinType(){

        return  sampleInfoService.findAllSampleToxinInfo();
    }

    @RequestMapping("findProvince")
    @ResponseBody
    public List<AddressProvince> findProvince(){

        return  sampleInfoService.findAllAddressProvince();

    }
    @RequestMapping("findCity")
    @ResponseBody
    public List<AddressCity> findCity(String code){

        return  sampleInfoService.findAllAddressCity(code);

    }
    @RequestMapping("findTown")
    @ResponseBody
    public List<AddressTown> findTown(String code){
        return  sampleInfoService.findAllAddressTown(code);
    }

    @RequestMapping("findAllCropSpecies")
    @ResponseBody
    public List<CropSpecies> findCropSpecies(){
        return  sampleInfoService.findAllCropSpecies();
    }

    @RequestMapping("selectByCropCategoryId")
    @ResponseBody
    public List<CropSpecies> selectByCropCategoryId(String id){
        return  sampleInfoService.selectByCropCategoryId(id);
    }
    @RequestMapping("findAllCropCategory")
    @ResponseBody
    public List<CropCategory> findAllCropCategory(){
        return  sampleInfoService.findAllCropCategory();
    }

    @RequestMapping("findAll")
    @ResponseBody
    public PageUtil findAllSampleInfoAjax(Integer pageNum, Integer pageSize,
                                     String search,String pollutionRate,  Integer toxinType, Integer breed,
                                    Integer province, Integer city, Integer county,String year,String month, String day){

        PageUtil list = sampleInfoService.findAllSampleInfo(pageNum,
                pageSize,search,pollutionRate, toxinType,breed,province,city,county,year,month,day);

        return list;
    }
    @RequestMapping("Export")
    public void findIdSampleInfo(@RequestParam("ids") String[] ids, String fileName
            , HttpServletResponse response)throws  Exception{
        if (fileName != null && !"".equals(fileName)){
            //不为空情况下按照后缀名创建工作簿
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
             fileName = fileName + ".xls";
            }

        }else{//如果为空，创建默认的工作部
           fileName = "导出数据.xls";
        }

        List<SampleInfoVO> sampleInfos = sampleInfoService.exportInfo1(ids);


        fileName = URLEncoder.encode(fileName,"UTF-8");

        //后台上传页面下载
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        //以流的形式进行读取操作文件
        response.setContentType("multipart/form-data");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());


        try {
            Sheet sheet = new Sheet(0,1,SampleInfoVO.class);
            // OutputStream outputStream = new FileOutputStream("D://导出信息.xls");
            ExcelWriter writer = null;
            if (fileName.endsWith(".xls")){
                writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLS, true);
            }else {
                writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLSX, true);
            }

            writer.write(sampleInfos,sheet);
            writer.finish();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/upload")
    @ResponseBody
    public ResultUtil upload(@RequestParam("fileFirst")MultipartFile file)throws Exception{
        ResultUtil resultUtil = new ResultUtil();
            //获取文件名称
            String originalFilename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            ReaderExcel readerExcel = new ReaderExcel();
            List<List<String>> reader = readerExcel.reader(originalFilename, inputStream);
            resultUtil =  sampleInfoService.upload (reader);
        return  resultUtil;
    }



    /**
     * 查询修改的ID
     * @param id
     * @return
     */
    @RequestMapping("/findbyidupdata")
    @ResponseBody
    public SampleInfo findByIdupdata(Integer id){
        SampleInfo sampleInfo = sampleInfoService.findById(id);
        return sampleInfo;
    }

    /**
     * 修改
     * @param sampleInfo
     * @return
     */
    @RequestMapping("/upSampleInfo")
    @ResponseBody
    public ResultUtil update(SampleInfo sampleInfo){
        ResultUtil resultUtil = sampleInfoService.set(sampleInfo);
        return resultUtil;

    }
    /**
     * 添加
     * @param sampleInfo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultUtil add(@RequestBody SampleInfo sampleInfo){
        ResultUtil resultUtil = sampleInfoService.add(sampleInfo);
        return resultUtil;
    }

    @RequestMapping("/getSampleInfoById")
    @ResponseBody
    public SampleInfo getSampleInfoById(Integer id){

        return  sampleInfoService.findSampleinfoInId(id);
    }
    @RequestMapping("/delectById")
    @ResponseBody
    public ResultUtil delectById(Integer id){
       ResultUtil result = new ResultUtil();
       try {
           sampleInfoService.delect(id);
           result.setSuccess(true);
           result.setMessage("");
       }catch (Exception e){


       }
        return  sampleInfoService.delect(id);
    }

}
