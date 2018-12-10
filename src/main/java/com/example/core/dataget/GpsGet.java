package com.example.core.dataget;
/**
 * 说明： 从串口中提取接收的GPS经纬度数据
 * @author  zre
 * @version 创建时间：xxxx-x-xx 上午xx:xx:xx
 */

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class GpsGet implements SerialPortEventListener {
    private CommPortIdentifier portId;
    private Enumeration<CommPortIdentifier> portList;
    private static SerialPort serialPort;
    private static InputStream inputStream;
    private OutputStream outputStream;

    public static String GPSFrame = "";
    private static GpsGet uniqueInstance = new GpsGet();

    /**
     * 初始化串口
     * @return
     */
    @SuppressWarnings("unchecked")
    public Boolean init() {
        // 获取系统中所有的通讯端口
        portList = CommPortIdentifier.getPortIdentifiers();
        // 循环通讯端口
        while (portList.hasMoreElements()) {
            portId = portList.nextElement();
            // 判断是否是串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 比较串口名称是否是"COM1"
                if (("COM1").equals(portId.getName())||("COM2").equals(portId.getName())||("COM3").equals(portId.getName())||("COM4").equals(portId.getName())||("COM5").equals(portId.getName())) {
                    System.out.println("找到串口");
                    // 打开串口
                    try {
                        // open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
                        serialPort = (SerialPort) portId.open(Object.class.getSimpleName(), 2000);
                        System.out.println("获取到串口对象");
                        // 设置串口监听
                        serialPort.addEventListener(this);
                        // 设置串口数据时间有效(可监听)
                        serialPort.notifyOnDataAvailable(true);
                        // 设置串口通讯参数
                        // 波特率，数据位，停止位和校验方式
                        // 波特率2400,偶校验
                        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//                        GPSFrame = "";
                        outputStream = serialPort.getOutputStream();
                        return true;
                    } catch (PortInUseException e) {
//                        e.printStackTrace();
                        closeSerialPort();
                        return false;
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        return false;
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        return false;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return null;
    }

    // 实现接口SerialPortEventListener中的方法 读取从串口中接收的数据
    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI: // 通讯中断
            case SerialPortEvent.OE: // 溢位错误
            case SerialPortEvent.FE: // 帧错误
            case SerialPortEvent.PE: // 奇偶校验错误
            case SerialPortEvent.CD: // 载波检测
            case SerialPortEvent.CTS: // 清除发送
            case SerialPortEvent.DSR: // 数据设备准备好
            case SerialPortEvent.RI: // 响铃侦测
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:// 输出缓冲区已清空
                break;
            case SerialPortEvent.DATA_AVAILABLE:// 有数据到达
                readComm();
                break;
            default:
                break;
        }
    }

    // 读取串口返回信息
    public static void readComm() {
        byte[] readBuffer = new byte[1024];
        try {
            inputStream = serialPort.getInputStream();
            int len = 0;
            while ((len = inputStream.read(readBuffer)) != -1) {
                GPSFrame += new String(readBuffer, 0, len).trim();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 关闭串口
    public Boolean closeSerialPort() {
        if (serialPort != null) {
            serialPort.notifyOnDataAvailable(false);
            serialPort.removeEventListener();
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException e) {
                }
            }
            serialPort.close();
            serialPort = null;
        }
        System.out.println("关闭串口");
        return true;
    }

//    public static String parserGPSList(String str) throws InterruptedException {
//        System.out.println(str);
//        if (null == str || str.equals("")) {
//             System.out.println("GPS数据为空，请检查！");
//            return ",,,,,,,";
//        } else {
//            int beginPosition = str.indexOf('$');
//            if (-1 != beginPosition) {
//                while (-1 != beginPosition && '$' == str.charAt(beginPosition)) {
//                    String temp = str.substring(beginPosition, beginPosition + 6);
//                    if ("$GPGGA".equals(temp)) {
//                        int endPosition = str.indexOf('*', beginPosition);
//                        if (endPosition != -1) {
//                            String gpsStr = str.substring(beginPosition, endPosition + 1);
//                            System.out.println(gpsStr);
//                            return gpsStr;
//                        } else {
//                            readComm();
//                        }
//                            beginPosition = str.indexOf('$', endPosition);
//                    } else {
//                            beginPosition = str.indexOf('$', beginPosition + 1);
//                    }
//                }
//            } else {
//                System.out.println("数据中不包含$GPGGA数据！");
//            }
//        }
//        return null;
//    }
public static String parseGPRMC(String str) throws InterruptedException {
    System.out.println(str);
    if (null == str || str.equals("")) {
        System.out.println("GPS数据为空，请检查！");
        return null;
    } else {
        int beginPosition = str.indexOf('$');
        if (-1 != beginPosition) {
            while (-1 != beginPosition && '$' == str.charAt(beginPosition)) {
                String temp = str.substring(beginPosition, beginPosition + 6);
                if ("$GPRMC".equals(temp)) {
                    int endPosition = str.indexOf('*', beginPosition);
                    if (endPosition != -1) {
                        String gpsStr = str.substring(beginPosition, endPosition + 1);
                        System.out.println(gpsStr);
                        return gpsStr;
                    } else {
                        readComm();
                    }
                    beginPosition = str.indexOf('$', endPosition);
                } else {
                    beginPosition = str.indexOf('$', beginPosition + 1);
                }
            }
        } else {
            System.out.println("数据中不包含$GPRMC数据！");
        }
    }
    return null;
}

}
