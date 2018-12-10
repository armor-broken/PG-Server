package com.example.core.dataget;


import com.example.model.BroadcastInfo;
import com.example.model.CollectionInfo;
import com.example.model.GPSInfo;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;


public class SNMPtest {
    private static Snmp snmp;
    private static Address targetAddress;

    public static GpsGet gps = new GpsGet();

    /**
     * 设置Agent方的IP和端口
     * @param url
     * @throws IOException
     */
    public static void initComm(String url) throws IOException {
        String str = "udp:" + url + "/161";
        targetAddress = GenericAddress.parse(str);
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }

    /**
     * 设置 target
     * @param pdu
     * @return
     * @throws IOException
     */
    public static ResponseEvent sendPDU(PDU pdu) throws IOException {
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("DEVA44"));
        target.setAddress(targetAddress);
        target.setRetries(2);// 通信不成功时的重试次数
        target.setTimeout(1500);// 超时时间
        target.setVersion(SnmpConstants.version2c);
        ResponseEvent event = snmp.send(pdu, target);
        return event;
    }

    /**
     * 设置PDU
     * @param fre
     * @throws IOException
     */
    public static void setPDU(int fre) throws IOException {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(".1.3.6.1.4.1.35833.5.3.1.0"), new Integer32(fre)));
        pdu.setType(PDU.SET);
        sendPDU(pdu);
    }

    /**
     * 获取 ipAddress PDU
     * @throws IOException
     */
    public String getIPPDU() throws IOException {
        PDU pduIP = new PDU();
        pduIP.add(new VariableBinding(new OID(new int[]{1, 3, 6, 1, 4, 1, 35833, 5, 2, 1, 1, 0})));
        pduIP.setType(PDU.GET);
        return readResponseGetIP(sendPDU(pduIP));
    }

    /**
     * 获取带SNMP数据的PDU（bulk PDU）
     * @return
     * @throws IOException
     */
    public static PDU createGetBulkPdu() throws IOException {
        PDU pdu = new PDU();
        pdu.setType(PDU.GETBULK);
        pdu.setMaxRepetitions(13); // must set it, default is 0
        pdu.setNonRepeaters(0);
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.35833.5.3")));
//        readResponse2(sendPDU(pdu)); // system
        return pdu;
    }

    /**
     * 获取设备IP
     * @param respEvnt
     */
    public static String readResponseGetIP(ResponseEvent respEvnt) {
        // 解析Response
        VariableBinding recVB = null;
        if (respEvnt != null && respEvnt.getResponse() != null) {
            Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt
                    .getResponse().getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                recVB = recVBs.elementAt(i);
            }
        }
        return recVB.getVariable().toString();
    }

    /**
     * 获取GPS数据
     * @return
     * @throws Exception
     */
    public static GPSInfo getGPSData() throws Exception {
        GPSInfo gInfo = new GPSInfo();
        int satellitenum;
        String longitude;
        String latitude;
        int longi;
        int lati;
        String atti;
        String gpsFrame = gps.parseGPRMC(gps.GPSFrame);
        if (gpsFrame != null) {
            System.out.println(gpsFrame);
            String[] gpsOutput = gpsFrame.split(",");
            System.out.println(gpsOutput.length);
            System.out.println(gpsOutput[2]);
            if (gpsOutput[2].equals("V")) {
                System.out.println("123");
                gInfo.setSatellitenum(0);
                gInfo.setUTC("");
                gInfo.setLongitude("");
                gInfo.setLatitude("");
                gInfo.setPostionstate("V");
            } else {
                String UTC = gpsOutput[1] + " " + gpsOutput[9];
                //UTC
                gInfo.setUTC(UTC);
                String longi1 = gpsOutput[5].substring(0, 3);
                String longi2 = gpsOutput[5].substring(3, gpsOutput[5].length());
                float longi11 = Float.parseFloat(longi1);
                float longi22 = Float.parseFloat(longi2) / 60;
                longi = (int) ((longi11 + longi22) * 1000000);
                longitude = longi1 + new DecimalFormat("#.000000").format(longi22) + "";
                //经度
                gInfo.setLongitude(longitude);
                System.out.println("经度：" + longitude);
                String lati1 = gpsOutput[3].substring(0, 2);
                String lati2 = gpsOutput[3].substring(2, gpsOutput[3].length());
                float lati11 = Float.parseFloat(lati1);
                float lati22 = Float.parseFloat(lati2) / 60;
                lati = (int) ((lati11 + lati22) * 1000000);
                latitude = lati1 + new DecimalFormat("#.000000").format(lati22) + "";
                //纬度
                gInfo.setLatitude(latitude);
                System.out.println("纬度：" + latitude);
                //定位状态
                gInfo.setPostionstate(gpsOutput[2]);
//                satellitenum = (int) (Float.parseFloat(posState));
                //卫星数量
//                gInfo.setSatellitenum(satellitenum);
//                System.out.println("卫星数量：" + satellitenum);
                //海拔
//                gInfo.setAltitude(gpsOutput[9]);
//                System.out.println("海拔：" + gpsOutput[9]);
                gps.GPSFrame="";
            }
        } else {
            gps.closeSerialPort();
        }
        return gInfo;
    }
//    public static GPSInfo getGPSData() throws Exception {
//        GPSInfo gInfo = new GPSInfo();
//        int satellitenum;
//        String longitude;
//        String latitude;
//        int longi;
//        int lati;
//        String atti;
//        if (gps.parserGPSList(gps.GPSFrame) != null) {
//            String[] gpsOutput = gps.parserGPSList(gps.GPSFrame).split(",");
//            if (gpsOutput.length == 0) {
//                System.out.println("123");
//                gInfo.setSatellitenum(0);
//            } else {
//                //UTC
//                gInfo.setUTC(gpsOutput[1]);
//                String longi1 = gpsOutput[4].substring(0, 3);
//                String longi2 = gpsOutput[4].substring(3, gpsOutput[4].length());
//                float longi11 = Float.parseFloat(longi1);
//                float longi22 = Float.parseFloat(longi2) / 60;
//                longi = (int) ((longi11 + longi22) * 1000000);
//                longitude = longi1 + new DecimalFormat("#.000000").format(longi22) + "";
//                //经度
//                 gInfo.setLongitude(longitude);
//                System.out.println("经度：" + longitude);
//                String lati1 = gpsOutput[2].substring(0, 2);
//                String lati2 = gpsOutput[2].substring(2, gpsOutput[2].length());
//                float lati11 = Float.parseFloat(lati1);
//                float lati22 = Float.parseFloat(lati2) / 60;
//                lati = (int) ((lati11 + lati22) * 1000000);
//                latitude = lati1 + new DecimalFormat("#.000000").format(lati22) + "";
//                //纬度
//                gInfo.setLatitude(latitude);
//                System.out.println("纬度：" + latitude);
//                String satenum = gpsOutput[7];
//                satellitenum = (int) (Float.parseFloat(satenum));
//                //卫星数量
//                gInfo.setSatellitenum(satellitenum);
//                System.out.println("卫星数量：" + satellitenum);
//                //海拔
//                gInfo.setAltitude(gpsOutput[9]);
//                System.out.println("海拔：" + gpsOutput[9]);
//                gps.GPSFrame="";
//            }
//        } else {
//            gps.init();
//        }
//        return gInfo;
//    }

    /**
     * 解析广播PDU方法
     * @param respEvnt
     * @return
     */
    public static BroadcastInfo analysisBroadcastData(ResponseEvent respEvnt) {
        BroadcastInfo bInfo = new BroadcastInfo();
        String[] arr = new String[13];
        if (respEvnt != null && respEvnt.getResponse() != null) {
            Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                VariableBinding recVB = recVBs.get(i);
                String str11 = recVB.toValueString();
                for (int k = 0; k < arr.length; k++) {
                    if (k == i) {
                        arr[k] = str11;
                    }
                }
            }
            try {
                double fre1 = Integer.parseInt(arr[0]);
                double rf = Integer.parseInt(arr[1]);
                double mpath = Integer.parseInt(arr[2]);
                double rds = Integer.parseInt(arr[12]);
                fre1 = fre1 / 1000;
                String frequency = fre1 + "";
                String RF = rf / 10 + "";
                String MPATH = mpath / 10 + "";
                rds = rds / 10;
                //设置频率
                bInfo.setFrequency(frequency);
                //设置射频
                bInfo.setRF(RF);
                //设置多径
                bInfo.setMPATH(MPATH);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bInfo;
    }

    //

    /**
     * 设置getbulk的PDU对象并解析发送
     * @return
     * @throws IOException
     */
    private static BroadcastInfo createGetBroadcastData() throws IOException {
        PDU pdu = new PDU();
        pdu.setType(PDU.GETBULK);
        pdu.setMaxRepetitions(13);
        pdu.setNonRepeaters(0);
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.35833.5.3")));
        ResponseEvent getbulkEvent = sendPDU(pdu);
        return analysisBroadcastData(getbulkEvent);
    }

    /**
     * 获取采集数据
     * @return
     * @throws Exception
     */
    public static CollectionInfo getCollectionData() throws Exception{
        CollectionInfo cInfo = null;
        BroadcastInfo bInfo = null;
        GPSInfo gInfo = null;
        gInfo = getGPSData();
        bInfo = createGetBroadcastData();
        cInfo = new CollectionInfo(bInfo.getFrequency(),bInfo.getRF(),bInfo.getMPATH(),gInfo.getLongitude(),gInfo.getLatitude(),gInfo.getAltitude(),gInfo.getSatellitenum(),gInfo.getUTC());
        return cInfo;
    }

//    // 解析Response
//    public static CollectionInfo readResponse2(ResponseEvent respEvnt, String deviceid) {
//        CollectionInfo info = null;
//        String[] arr = new String[13];
//        String satellitenum;
//        String altitude;
//        String longitude;
//        String latitude;
//        String UTC;
//        int satenum;
//        int longi;
//        int lati;
//        String atti;
//        if (respEvnt != null && respEvnt.getResponse() != null) {
//            Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
//            for (int i = 0; i < recVBs.size(); i++) {
//                VariableBinding recVB = recVBs.get(i);
//                String str11 = recVB.toValueString();
//                for (int k = 0; k < arr.length; k++) {
//                    if (k == i) {
//                        arr[k] = str11;
//                    }
//                }
//            }
//            try {
//                double fre1 = Integer.parseInt(arr[0]);
//                double rf = Integer.parseInt(arr[1]);
//                double mpath = Integer.parseInt(arr[2]);
//                // double pos = Integer.parseInt(arr[3]);
//                // double neg = Integer.parseInt(arr[4]);
//                // double mpxdev = Integer.parseInt(arr[5]);
//                // double L = Integer.parseInt(arr[7]);
//                // double R = Integer.parseInt(arr[8]);
//                // double LpR = Integer.parseInt(arr[9]);
//                // double LmR = Integer.parseInt(arr[10]);
//                // double pilot = Integer.parseInt(arr[11]);
//                double rds = Integer.parseInt(arr[12]);
//                fre1 = fre1 / 1000;
//                String frequency = fre1 + "";
//                String RF = rf / 10 + "";
//                String MPATH = mpath / 10 + "";
//                rds = rds / 10;
//                info = new CollectionInfo(frequency, RF, MPATH);
//                DecimalFormat df = new DecimalFormat("#.0");
//                String fre = df.format(fre1);
////                if (gp.parserGPSList(gp.GPSFrame) != null) {
////                    String[] gpsshuchu = gp.parserGPSList(gp.GPSFrame).split(",");
////                    if (gpsshuchu.length == 0) {
////                        System.out.println("123");
////                        longi = 0;
////                        lati = 0;
////                        atti = "0";
////                        lat = "0";
////                        lon = "0";
////                        satenum = 0;
////                    } else {
////                        UTC = gpsshuchu[1];
////                        String longi1 = gpsshuchu[4].substring(0, 3);
////                        String longi2 = gpsshuchu[4].substring(3, gpsshuchu[4].length());
////                        float longi11 = Float.parseFloat(longi1);
////                        float longi22 = Float.parseFloat(longi2) / 60;
////                        longi = (int) ((longi11 + longi22) * 1000000);
////                        longitude = longi1 + new DecimalFormat("#.000000").format(longi22) + "";
////                        info.setClcLon(longitude);
////                        System.out.println("经度："+longitude);
////                        String lati1 = gpsshuchu[2].substring(0, 2);
////                        String lati2 = gpsshuchu[2].substring(2, gpsshuchu[2].length());
////                        float lati11 = Float.parseFloat(lati1);
////                        float lati22 = Float.parseFloat(lati2) / 60;
////                        lati = (int) ((lati11 + lati22) * 1000000);
////                        latitude = lati1 + new DecimalFormat("#.000000").format(lati22) + "";
////                        info.setClcLat(latitude);
////                        System.out.println("纬度："+latitude);
////                        satellitenum = gpsshuchu[7];
////                        satenum = (int) (Float.parseFloat(satellitenum)) ;
////                        info.setClcSatNum(satenum);
////                        info.setClcAlt(gpsshuchu[9]);
////                        System.out.println("卫星数量："+satenum);
////                        float atti1 = Float.parseFloat(gpsshuchu[9]);
////                        int atti2 = (int) (atti1);
////                        atti = String.valueOf(atti2);
////                        if (atti2 < 10) {
////                            atti = "00" + atti;
////                        } else if (atti2 >= 10 && atti2 < 99) {
////                            atti = "0" + atti;
////                        } else if (atti2 >= 100) {
////                            atti = atti;
////                        }
////                    }
////                }else{
////                    gp.init();
////                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return info;
//    }
}
