package com.example.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.example.model.CollectionInfo;
import com.example.model.InitResponse;
import com.example.model.GPSInfo;
import com.example.model.IDListInfo;
import com.example.model.RequestMessage;
import com.example.service.CollectionInfoService;
import com.example.core.dataget.*;
import com.github.pagehelper.Page;
import org.omg.CORBA.SetOverrideType;
import org.snmp4j.PDU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WsController {
    private static Boolean flag = false;
    @Autowired
    private CollectionInfoService collectionInfoService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //实例化SNMP对象
    SNMPtest util = new SNMPtest();
    //    数据库查询频率表
    private static List<String> fretableString = new ArrayList();
    //    配置设备的频率表
    private static List<Integer> fretable = new ArrayList();
    private static List<Integer> templist = new ArrayList();

    private static String device1IP = "";
    private static String device2IP = "";


    private static int count = 0;
    private static boolean first = true;
    private static boolean satelliteFlag = false;
    private static int frelength = 0;

//    //获取频率表
//    @GetMapping(value = "/collectionInit")
//    @ResponseBody
//    public InitResponse selectFrequency() throws Exception{
//        InitResponse response = null;
//        GPSInfo gpsInfo = null;
//        Boolean portFlag = null;
//        Boolean satelliteFlag = null;
//        //GPS串口初始化
//        portFlag = util.gps.init();
//        //GPS卫星数量
//        gpsInfo = util.getGPSData();
//        System.out.println(gpsInfo.getSatellitenum());
//        if (gpsInfo.getSatellitenum() >= 4){
//            satelliteFlag = true;
//        }else {
//            satelliteFlag = false;
//        }
//        //获取频率表
//        fretable = collectionInfoService.selectFrequency();
//        response = new InitResponse(portFlag,satelliteFlag,fretable);
//        return response;
//    }

    //获取频率表
    @PostMapping(value = "/collectionInit")
    @ResponseBody
    public InitResponse collectionInit(@RequestBody IDListInfo idListInfo) throws Exception {
        fretable.clear();
        InitResponse response = new InitResponse();
        GPSInfo gpsInfo = null;
        Boolean portFlag = null;
        List idList = null;
        //GPS串口初始化
        portFlag = util.gps.init();
        if (portFlag==true){
            response.setPortState("1");
            util.gps.readComm();
//            Thread.sleep(1250);
        }else {
            response.setPortState("0");
        }
        //获取频率表
        idList = idListInfo.getId();
        for (int i = 0; i < idList.size(); i++) {
            int id = Integer.parseInt(idList.get(i).toString());
            templist = collectionInfoService.findFrequency1(id);
            for (int j = 0; j < templist.size(); j++) {
                fretable.add(templist.get(j));
            }
        }
//        返回频率表
        response.setList(fretable);
//        设置前端返回IP
        device1IP = idListInfo.getDevice1IP();
        util.initComm(device1IP);
        System.out.println(device1IP);
        System.out.println(util.getIPPDU());
        if (device1IP.equals(util.getIPPDU())){
            response.setDeviceIPState("1");
        }else {
            response.setDeviceIPState("0");
        }


        if (fretable.size() == 1) {
            util.initComm("192.168.8.3");
            util.setPDU(fretable.get(0));
            Thread.sleep(1250);
        }

        gpsInfo = util.getGPSData();
        System.out.println(gpsInfo.getPostionstate());
        if (gpsInfo.getPostionstate().equals("A")){
            response.setSatelliteState("1");
        }else {
            response.setSatelliteState("0");
        }

        return response;
    }

    //获取当前GPS信息
    @GetMapping(value = "/currentGPSData")
    @ResponseBody
    public GPSInfo collectionInit() throws Exception {
        GPSInfo gpsInfo = null;
        InitResponse response = new InitResponse();
        //GPS卫星数量
        gpsInfo = util.getGPSData();
            if (gpsInfo.getPostionstate() == "A"){
                response.setSatelliteState("1");
            }else {
                response.setSatelliteState("0");
            }
        return gpsInfo;
    }

    //关闭串口
    @GetMapping(value = "/collectionClose")
    public @ResponseBody
    Boolean closeProcess() {
        Boolean closeFlag = null;
        //GPS串口初始化
        closeFlag = util.gps.closeSerialPort();
        return closeFlag;
    }

    /**
     * 设备数据返回接口
     * 注：频率为单一频率
     * @param request
     * @throws Exception
     */
    @MessageMapping("/collectiondata")
    @SendTo("/topic/getResponse")
    public void getsCollectionData(RequestMessage request) throws Exception {
        CollectionInfo info = null;
        flag = request.getState();
        System.out.println(fretable);
        System.out.println(request.getState());
        while (flag) {
            //        设置设备频率
            util.initComm("192.168.8.3");
            info = util.getCollectionData();
            info.setClcDev("设备1");
            util.getIPPDU();
            templateTest(info);
            collectionInfoService.insertCollection1(info);
            Thread.sleep(500);
        }
    }

    /**
     * 设备数据返回接口
     * 注：频率为多个频率
     *
     * @param request
     * @throws Exception
     */
    @MessageMapping("/collectiondatamultiple")
    @SendTo("/topic/getResponse")
    public void getsCollectionDataMultiple(RequestMessage request) throws Exception {
        CollectionInfo info = null;
        flag = request.getState();
        System.out.println(fretable);
        System.out.println(request.getState());
        while (flag) {
            for (int i = 0; i < fretable.size(); i++) {
                util.initComm("192.168.8.3");
                util.setPDU(fretable.get(i));
                Thread.sleep(1250);
                info = util.getCollectionData();
                info.setClcDev("设备1");
                util.getIPPDU();
                templateTest(info);
            }
        }
    }

    //客户端只要订阅了/topic/getResponse主题，调用这个方法即可
    public void templateTest(CollectionInfo cInfo) {
        messagingTemplate.convertAndSend("/topic/getResponse", cInfo);
    }

    //    //    设备数据返回
//    @MessageMapping("/collectiondata")
//    @SendTo("/topic/getResponse")
//    public @ResponseBody CollectionInfo getsCollectionData() throws Exception {
//        CollectionInfo info = null;
//        System.out.println(fretable);
////        设置设备频率
//        util.initComm("192.168.8.3");
//        util.setPDU(fretable.get(0));
//        Thread.sleep(1250);
//        info = util.getCollectionData();
//        info.setClcDev("设备1");
////        PDU pdu0 = util.createGetBulkPdu();
////        info = util.readResponse2(util.sendPDU(pdu0), "1");
//
//        frelength = fretable.size();
//        System.out.println(frelength);
////        if ((frelength == 1) && first) {
////            util.setPDU(fretable.get(0));
////            Thread.sleep(1250);
////            PDU pdu0 = util.createGetBulkPdu();
////            info = util.readResponse2(util.sendPDU(pdu0), "1");
////            first = false;
////        }
////        else if (frelength == 1 && !first) {
////            PDU pdu0 = util.createGetBulkPdu();
////            info = util.readResponse2(util.sendPDU(pdu0), "1");
////        }
////        else if (frelength > 1) {
////            if (count < frelength) {
////                switch (count) {
////                    case 0:
////                        util.setPDU(fretable.get(0));
////                        Thread.sleep(1250);
////                        PDU pdu0 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu0), "1");
////                        break;
////                    case 1:
////                        util.setPDU(fretable.get(1));
////                        Thread.sleep(1250);
////                        PDU pdu1 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu1), "1");
////                        break;
////                    case 2:
////                        util.setPDU(fretable.get(2));
////                        Thread.sleep(1250);
////                        PDU pdu2 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu2), "1");
////                        break;
////                    case 3:
////                        util.setPDU(fretable.get(3));
////                        Thread.sleep(1250);
////                        PDU pdu3 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu3), "1");
////                        break;
////                    case 4:
////                        util.setPDU(fretable.get(4));
////                        Thread.sleep(1250);
////                        PDU pdu4 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu4), "1");
////                        break;
////                    case 5:
////                        util.setPDU(fretable.get(5));
////                        Thread.sleep(1250);
////                        PDU pdu5 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu5), "1");
////                        break;
////                    case 6:
////                        util.setPDU(fretable.get(6));
////                        Thread.sleep(1250);
////                        PDU pdu6 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu6), "1");
////                        break;
////                    case 7:
////                        util.setPDU(fretable.get(7));
////                        Thread.sleep(1250);
////                        PDU pdu7 = util.createGetBulkPdu();
////                        info = util.readResponse2(util.sendPDU(pdu7), "1");
////                        break;
////                    default:
////                        break;
////                }
////                count++;
////                if (count == frelength) {
////                    count = 0;
////                }
////            }
////        }
//        util.getIPPDU();
//        return info;
//    }

//    //    设备数据返回
//    @MessageMapping("/collectiondata")
//    @SendTo("/topic/getResponse")
//    @Scheduled(cron = "* * 0/1 * * ?")
//    public @ResponseBody CollectionInfo getsCollectionData(RequestMessage request) throws Exception {
//        CollectionInfo info = null;
//        System.out.println(fretable);
////        设置设备频率
//        util.initComm("192.168.8.3");
////        util.setPDU(fretable.get(0));
////        Thread.sleep(1250);
//        info = util.getCollectionData();
//        info.setClcDev("设备1");
//        util.getIPPDU();
//        return info;
//    }


//    //    设备一
//    @MessageMapping("/sdata1")
//    @SendTo("/topic/getResponse1")
//    public Map getsData1() throws Exception {
//        System.out.println(fretable);
//        Map mulmap = null;
//        util.initComm("192.168.8.3");
////        util.getIpPDU();
//        frelength1 = setfre1.size();
//        System.out.println(frelength1);
//        if ((frelength1 == 1) && first) {
//            util.setPDU(setfre1.get(0));
//            Thread.sleep(1250);
//            PDU pdu0 = util.createGetBulkPdu();
//            mulmap = util.readResponse2(util.sendPDU(pdu0), "1");
//            first = false;
//        } else if (frelength1 == 1 && !first) {
//            PDU pdu0 = util.createGetBulkPdu();
//            mulmap = util.readResponse2(util.sendPDU(pdu0), "1");
//        } else if (frelength1 > 1) {
//            if (count1 < frelength1) {
//                switch (count1) {
//                    case 0:
//                        util.setPDU(setfre1.get(0));
//                        Thread.sleep(1250);
//                        PDU pdu0 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu0), "1");
//                        break;
//                    case 1:
//                        util.setPDU(setfre1.get(1));
//                        Thread.sleep(1250);
//                        PDU pdu1 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu1), "1");
//                        break;
//                    case 2:
//                        util.setPDU(setfre1.get(2));
//                        Thread.sleep(1250);
//                        PDU pdu2 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu2), "1");
//                        break;
//                    case 3:
//                        util.setPDU(setfre1.get(3));
//                        Thread.sleep(1250);
//                        PDU pdu3 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu3), "1");
//                        break;
//                    case 4:
//                        util.setPDU(setfre1.get(4));
//                        Thread.sleep(1250);
//                        PDU pdu4 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu4), "1");
//                        break;
//                    case 5:
//                        util.setPDU(setfre1.get(5));
//                        Thread.sleep(1250);
//                        PDU pdu5 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu5), "1");
//                        break;
//                    case 6:
//                        util.setPDU(setfre1.get(6));
//                        Thread.sleep(1250);
//                        PDU pdu6 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu6), "1");
//                        break;
//                    case 7:
//                        util.setPDU(setfre1.get(7));
//                        Thread.sleep(1250);
//                        PDU pdu7 = util.createGetBulkPdu();
//                        mulmap = util.readResponse2(util.sendPDU(pdu7), "1");
//                        break;
//                    default:
//                        break;
//                }
//                count1++;
//                if (count1 == frelength1) {
//                    count1 = 0;
//                }
//            }
//        }
//        util.getIpPDU();
//        return mulmap;
//    }
//
//    //    设备二
//    @MessageMapping("/sdatad2")
//    @SendTo("/topic/getResponse2")
//    public Map getsData2() throws Exception {
//        Map mulmap = null;
//        util2.initComm("192.168.8.9");
//        frelength2 = setfre2.size();
//        System.out.println(frelength2);
//        if ((frelength2 == 1) && first) {
//            util2.setPDU(setfre2.get(0));
//            Thread.sleep(1250);
//            PDU pdu0 = util2.createGetBulkPdu();
//            mulmap = util2.readResponse2(util2.sendPDU(pdu0), "2");
//            first = false;
//        } else if (frelength2 == 1 && !first) {
//            PDU pdu0 = util2.createGetBulkPdu();
//            mulmap = util2.readResponse2(util2.sendPDU(pdu0), "2");
//        } else if (frelength2 > 1) {
//            if (count2 < frelength2) {
//                switch (count2) {
//                    case 0:
//                        util2.setPDU(setfre2.get(0));
//                        Thread.sleep(1250);
//                        PDU pdu0 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu0), "2");
//                        break;
//                    case 1:
//                        util2.setPDU(setfre2.get(1));
//                        Thread.sleep(1250);
//                        PDU pdu1 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu1), "2");
//                        break;
//                    case 2:
//                        util2.setPDU(setfre2.get(2));
//                        Thread.sleep(1250);
//                        PDU pdu2 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu2), "2");
//                        break;
//                    case 3:
//                        util2.setPDU(setfre2.get(3));
//                        Thread.sleep(1250);
//                        PDU pdu3 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu3), "2");
//                        break;
//                    case 4:
//                        util2.setPDU(setfre2.get(4));
//                        Thread.sleep(1250);
//                        PDU pdu4 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu4), "2");
//                        break;
//                    case 5:
//                        util2.setPDU(setfre2.get(5));
//                        Thread.sleep(1250);
//                        PDU pdu5 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu5), "2");
//                        break;
//                    case 6:
//                        util2.setPDU(setfre2.get(6));
//                        Thread.sleep(1250);
//                        PDU pdu6 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu6), "2");
//                        break;
//                    case 7:
//                        util2.setPDU(setfre2.get(7));
//                        Thread.sleep(1250);
//                        PDU pdu7 = util2.createGetBulkPdu();
//                        mulmap = util2.readResponse2(util2.sendPDU(pdu7), "2");
//                        break;
//                    default:
//                        break;
//                }
//                count2++;
//                if (count2 == frelength2) {
//                    count2 = 0;
//                }
//            }
//        }
//        util2.getIpPDU();
//        return mulmap;
//    }

}
