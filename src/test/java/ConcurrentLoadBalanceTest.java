import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
* Benchmarking the service on how well it handles many concurrent requests.
* */
public class ConcurrentLoadBalanceTest {
    private static final int MAX_CLIENTS = 100;
    static CountDownLatch startLatch = new CountDownLatch(1);
    static String baseUrl = "http://localhost:8000/lab-escape";

    public static void main(String[] args) {
        TestLoadBalanceClient testLoadBalanceClient;

        Thread clientThread;
        ArrayList<Thread> clientThreads = new ArrayList<Thread>(MAX_CLIENTS);
        for(int i = 0; i < MAX_CLIENTS; i++) {
            testLoadBalanceClient = new TestLoadBalanceClient();
            clientThread = new Thread(testLoadBalanceClient);
            clientThreads.add(clientThread);
            clientThread.setDaemon(true);
            clientThread.start();
        }

        startLatch.countDown();
        for(Thread clThread : clientThreads) {
            try {
                clThread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Exiting test");
    }
}

class TestLoadBalanceClient implements Runnable {
    private String baseUrl = ConcurrentLoadBalanceTest.baseUrl;
    static String testRequestBody = "{\"startX\":2,\"startY\":1," +
                                        "\"labyrinth\":[" +
                                        "[\"O\",\"O\",\"O\",\"O\"]," +
                                        "[\"O\",\" \",\" \",\" \"]," +
                                        "[\"O\",\" \",\"O\",\"O\"]," +
                                        "[\"O\",\" \",\" \",\"O\"]"  +
                                        "]}";

    private void readResponse(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(inputStreamReader);
        StringBuffer strBuf = new StringBuffer();
        String line;

        while((line = in.readLine()) != null) {
            strBuf.append(line);
            System.out.println(line);
        }
    }

    public void run() {
        Scanner in  = new Scanner(System.in);
        String urlLink = baseUrl;
        InputStream inputStream = null;
        try {
            ConcurrentLoadBalanceTest.startLatch.await();
            URL url = new URL(urlLink);
            inputStream = null;
            HttpURLConnection con;
            int i = 0;
            long startTime = System.nanoTime();
            while(i < 100) {
                con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                byte[] outputInBytes = testRequestBody.getBytes("UTF-8");
                OutputStream os = con.getOutputStream();
                os.write( outputInBytes );
                inputStream =  con.getInputStream();
                readResponse(inputStream);
                i++;
            }
            long endTime = System.nanoTime();

            long testDuration = endTime - startTime;
            // Test duration in nanoseconds
             System.out.println("test duration = " + testDuration);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        in.close();
    }
}