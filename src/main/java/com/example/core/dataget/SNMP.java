package com.example.core.dataget;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class SNMP {
    private static Snmp snmp1 = null;
    private static Address targetAddress = null;
    private static String targetIp = null;
    private static CommunityTarget target = new CommunityTarget();
    public static GpsGet gp = new GpsGet();

    static {
        target.setCommunity(new OctetString("DEVA44"));
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
    }

    //初始化端口和ip
    public void initComm(String str) throws IOException {
        targetIp = str;
        targetAddress = GenericAddress.parse("udp:" + targetIp + "/161");
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp1 = new Snmp(transport);
        transport.listen();
    }

    //设置getbulk的PDU对象并发送
    private void createGetBulkPdu() throws IOException {
        PDU pdu = new PDU();
        pdu.setType(PDU.GETBULK);
        pdu.setMaxRepetitions(13);
        pdu.setNonRepeaters(0);
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.35833.5.3")));
        ResponseEvent getbulkEvent = sendPDU(pdu);
        readResponse2(getbulkEvent);
    }

    //设置set的PDU对象并发送
    public void setPDU() throws IOException {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(".1.3.6.1.4.1.35833.5.3.1.0"), new Integer32(103900)));
        pdu.setType(PDU.SET);
        sendPDU(pdu);
    }

    //发送PDU
    public ResponseEvent sendPDU(PDU pdu) throws IOException {
        target.setAddress(targetAddress);
        ResponseEvent event = snmp1.send(pdu, target);
        return event;
    }


    public static Map readResponse2(ResponseEvent respEvnt) {
        Map<String, Object> smap = new HashMap();
        String[] arr = new String[13];
        String longi1;
        String longi2;
        String lati1;
        String lati2;
        int longi;
        int lati;
        String atti;
        if (respEvnt != null && respEvnt.getResponse() != null) {
            Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                VariableBinding recVB = recVBs.get(i);
                String str11 = recVB.toValueString();
                for (int k = 0; k < arr.length; k++) {
                    if (k == i) {
                        arr[k] = str11;
                        smap.put("fre", arr[0]);
                        smap.put("raFre", arr[1]);
                        smap.put("mpath", arr[2]);
                    }
                }
            }

            try {

                double fre1 = Integer.parseInt(arr[0]);
                double rf = Integer.parseInt(arr[1]);
//        		   double mpath = Integer.parseInt(arr[2]);
//        		   double pos = Integer.parseInt(arr[3]);
//        		   double neg = Integer.parseInt(arr[4]);
//        		   double mpxdev = Integer.parseInt(arr[5]);
//        		   double L = Integer.parseInt(arr[7]);
//        		   double R = Integer.parseInt(arr[8]);
//        		   double LpR = Integer.parseInt(arr[9]);
//        		   double LmR = Integer.parseInt(arr[10]);
//        		   double pilot = Integer.parseInt(arr[11]);
                double rds = Integer.parseInt(arr[12]);
                fre1 = fre1 / 1000;
                DecimalFormat df = new DecimalFormat("#.00");
//        		   String fre=df.format(fre1);
                rf = rf / 10;
                rds = rds / 10;
//                String[] gpsshuchu = gp.parserGPSList(gp.GPSFrame).split(",");
                String[] gpsshuchu = gp.parseGPRMC(gp.GPSFrame).split(",");
                System.out.println(1);
                if (gpsshuchu[4] != null) {
                    longi = 0;
                    lati = 0;
                    atti = "0";
                } else {
                    longi1 = gpsshuchu[4].substring(0, 3);
                    longi2 = gpsshuchu[4].substring(3, gpsshuchu[4].length());
                    float longi11 = Float.parseFloat(longi1);
                    float longi22 = Float.parseFloat(longi2) / 60;
                    longi = (int) ((longi11 + longi22) * 1000000);
                    smap.put("lon", longi);
                    lati1 = gpsshuchu[2].substring(0, 2);
                    lati2 = gpsshuchu[2].substring(2, gpsshuchu[2].length());
                    float lati11 = Float.parseFloat(lati1);
                    float lati22 = Float.parseFloat(lati2) / 60;
                    lati = (int) ((lati11 + lati22) * 1000000);
                    smap.put("lat", lati);
                    float atti1 = Float.parseFloat(gpsshuchu[9]);
                    int atti2 = (int) (atti1);
                    atti = String.valueOf(atti2);
                    smap.put("satellitenum", gpsshuchu[7]);
                    if (atti2 < 10) {
                        atti = "00" + atti;
                    } else if (atti2 >= 10 && atti2 < 99) {
                        atti = "0" + atti;
                    } else if (atti2 >= 100) {
                        atti = atti;
                    }
                }
            } catch (Exception e) {

            }
        }
        return smap;
    }
}

