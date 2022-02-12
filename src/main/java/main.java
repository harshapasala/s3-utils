import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.pasala.S3Utils;

import java.io.IOException;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        // Upload
        System.out.println("Hello");
        S3Utils s3Utils = new S3Utils();
        String bucket = "karthik.pasala";
        String filePath = "/Users/karthikpasala/Downloads/file.txt";
        s3Utils.upload(bucket, filePath);

        // List of objects in a bucket
        S3ObjectSummary s3DownloadFile = null;
        List<S3ObjectSummary> listObjects =  s3Utils.listFiles(bucket);
        for(S3ObjectSummary objectSummary : listObjects) {
            s3DownloadFile = objectSummary;
            System.out.println(objectSummary.getKey());
        }

        // download the objects
        System.out.println("I am downlaoding " + s3DownloadFile.getKey());
        S3Object s3Object = s3Utils.download(bucket,s3DownloadFile.getKey());
        System.out.println(new String(s3Object.getObjectContent().readAllBytes()));
    }

       /*public static void main(String[] args) throws IOException {
           // Upload
           S3Utils s3Utils = new S3Utils();
           String bucket = "karthik.pasala";
           String filePath = "/Users/karthikpasala/Downloads/Past Perfect Tense _ ENGLISH PAGE.pdf";
           s3Utils.upload(bucket, filePath);

           String key = FilenameUtils.getName(filePath);
           S3Object s3Object = s3Utils.download(bucket, key);
           InputStream inputStream = s3Object.getObjectContent();
           ByteArrayOutputStream byteArrayOutputStream = null;
           try {
               byteArrayOutputStream = new ByteArrayOutputStream();
               int read;
               byte[] bytes = new byte[10000];
               while((read=inputStream.read(bytes)) != -1) {
                   byteArrayOutputStream.write(bytes, 0, read);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           inputStream.close();



           try(OutputStream outputStream = new FileOutputStream("a.pdf")) {
               byteArrayOutputStream.writeTo(outputStream);
           }
           byteArrayOutputStream.close();
       }*/
}
