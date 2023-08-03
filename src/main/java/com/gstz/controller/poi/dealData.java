package com.gstz.controller.poi;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.gstz.entity.enums.ParamOfTzywxmxx;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @Author : yimu
 *   poi 读取表格数据 拼接JSON数据
 *   循环调用接口初始化流程
 * @create 2023/08/02
 */
public class dealData {

    static Logger logger = Logger.getLogger(dealData.class);


    public static void main(String[] args) {
        try {
            String path = "C:\\Users\\yimu\\Desktop\\统计结果202306V1.xlsx";
            String fileName = "统计结果202306V1.xlsx";
            List<HashMap<String, Object>> list = readMergeExcel(
                    new FileInputStream(path), 0, 1, 0, fileName);
            Gson gson = new Gson();
            String json = gson.toJson(list);
//            System.out.println(json);
//            System.out.println("运行结束");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取excel数据 支持xls格式
     * @param sheetIndex    sheet页下标：从0开始
     * @param startReadLine 开始读取的行:从0开始
     * @param tailLine      去除最后读取的行数
     * @return
     */
    public static List<HashMap<String, Object>> readMergeExcel(FileInputStream file, int sheetIndex, int startReadLine, int tailLine, String fileName) {
        Workbook wb = null;
        List<HashMap<String, Object>> paramValues = new ArrayList<>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(file);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(file);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Sheet sheet = wb.getSheetAt(sheetIndex);
            Row row;
            Row rowStat = sheet.getRow(0);
            HashMap<Integer, String> param = new HashMap<>();
            for (Cell c : rowStat) {
                String temp = getCellValue(c).replaceAll(" ","").trim();
                if (!"".equals(temp)) {
                    param.put(c.getColumnIndex(), ParamOfTzywxmxx.getParam(getCellValue(c)));
                }
            }
            for (int i = startReadLine; i <= sheet.getLastRowNum() - tailLine; i++) {
                row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                HashMap<String, Object> values = new HashMap<>();
                for (Cell c : row) {
                    String temp = getCellValue(c).replaceAll(" ","").trim();
                    if (param.get(c.getColumnIndex()) != null) {
                        values.put(param.get(c.getColumnIndex()),temp);
                    }
                }
                HashMap<Object, Object> lcInfo = new HashMap<>();
                lcInfo.put("workflowCode","tzywxmxx");
                lcInfo.put("finishStart",1);
                lcInfo.put("paramValues",values);
                lcInfo.put("userId","12843"); //用户中心唯一标识

                Gson gson = new Gson();
                String json = gson.toJson(lcInfo);
                System.out.println(json);
                paramValues.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramValues;
    }

    /**
     * 获取单元格的值
     */
    public static String getCellValue(Cell cell) {
        if (cell == null) {return "";}
        if (cell.getCellTypeEnum() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }
        if (cell.getCellTypeEnum() ==  CellType.FORMULA) {
            return cell.getCellFormula();
        }
        if (cell.getCellTypeEnum() ==  CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }


    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            //验证EXCEL文件
            logger.error("文件名不是excel格式");
            return false;
        }
        return true;
    }
    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
