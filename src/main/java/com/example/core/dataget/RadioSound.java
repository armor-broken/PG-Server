package com.example.core.dataget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RadioSound implements Runnable {

    public static String destUrl;

    public void saveToFile(String destUrl) {
//	System.out.print(1);
        this.destUrl = destUrl;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int t = 0; t < t + 1; t++) {
            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            HttpURLConnection httpUrl = null;
            URL url = null;
            int BUFFER_SIZE = 1024;
            byte[] buf = new byte[BUFFER_SIZE];
            int size = 0;
            long startTime = System.currentTimeMillis();
            try {
                url = new URL(destUrl);
                httpUrl = (HttpURLConnection) url.openConnection();
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-SS");
                String date = df.format(new Date());
                fos = new FileOutputStream("C:/Users/patapon/Desktop/音频/" + date + ".mp3");
                while ((size = bis.read(buf)) != -1 && System.currentTimeMillis() - startTime <= 60000) {
                    fos.write(buf, 0, size);
                    fos.flush();
                }
            } catch (IOException e) {

            } catch (ClassCastException e) {

            } finally {
                try {
                    fos.close();
                    bis.close();
                    httpUrl.disconnect();
                } catch (IOException e) {

                } catch (NullPointerException e) {

                }
            }
        }
    }
}