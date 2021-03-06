package com.message.common;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.message.pojo.SampleInfo;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelDemo2 {

    public static void main(String[] args) throws  Exception {

        List<SampleInfo> sampleInfos = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("ID"+ i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市 "+i);
            sampleInfos.add(sampleInfo);
        }

        /**
         * sheetNo
         * headLineMun
         * 表头信息的位置
         */
        Sheet sheet = new Sheet(0,1,SampleInfo.class);
        OutputStream outputStream = new FileOutputStream("D://导出信息1.xls");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLS, true);
        writer.write(sampleInfos,sheet);
        writer.finish();
        outputStream.close();
    }
}
