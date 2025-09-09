package Tests;

import java.io.*;

public class ReadFile {

    public static long fileReaderTest(File measurements){
        long fileReaderStartTime = System.currentTimeMillis();

        try {
            Reader reader = new FileReader(measurements);
            BufferedReader bReader = new BufferedReader(reader);

            int linecount = 0;
            try {
                while(true) {
                    bReader.readLine();
                    linecount++;
                }

            } catch (IOException endOfFile) {
                System.out.println("Filereader: End of file. Number of lines: "+linecount);
            }

        } catch (Exception readerException) {
            readerException.printStackTrace();
        }

        return System.currentTimeMillis() - fileReaderStartTime;
    }

    public static long bufferedFileInputStreamTest(File measurements){
        long fileLength = measurements.length();
        long startTime = System.currentTimeMillis();
        System.out.println("File length:"+fileLength);
        try {
            FileInputStream inputStream = new FileInputStream(measurements);
            BufferedInputStream bInputStream = new BufferedInputStream(inputStream);

            long byteCount = 0;
            try {
                while(byteCount < fileLength) {
                    bInputStream.readNBytes(10_000_000); //File too long .readNBytes
                    byteCount += 10_000_000;
                    System.out.println(fileLength-byteCount); //Debug print
                }

            } catch (IOException streamException) {
                System.out.println("BufferedInputStream: " +streamException.getMessage());
            }

        } catch (Exception readerException) {
            readerException.printStackTrace();
        }

        return System.currentTimeMillis() - startTime;
    }




    public static void main(String[] args) {
        //Test times
        long fileReaderTime = 0;
        long bufferedFileInputStreamTime = 0;

        //File definition
        File measurements = new File("measurements.txt");
        long fileLength = measurements.length();

        //fileReader test: SLOW comment out!
        //fileReaderTime = fileReaderTest(measurements);

        //FileInputStream: Bad. Integer index for bytes too small
        bufferedFileInputStreamTime = bufferedFileInputStreamTest(measurements);

        //Java NIO java.nio.file


        //Print times
        System.out.println("Filereader time: "+fileReaderTime);
        System.out.println("BufferedInputStream time:" +bufferedFileInputStreamTime +" "+(fileLength/bufferedFileInputStreamTime)+" B/s" );



    }

}
