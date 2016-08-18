import java.security.*;
import java.io.*;
public class ComputeSHA
{
   public static void main(String[] args)throws Exception
   {
   String pathname=args[0];
  // System.out.println(pathname);
   File file = new File(pathname);
   FileInputStream inputStream = new FileInputStream(file);
   byte[] buffer = new byte [(int)file.length()];
   inputStream.read(buffer);
   inputStream.close();
   MessageDigest MD = MessageDigest.getInstance("SHA-1");
   byte[] byteDigest = MD.digest(buffer);
   StringBuffer hexDigest = new StringBuffer();
     for (int i=0;i<byteDigest.length;i++)
     {
      hexDigest.append(Integer.toString((byteDigest[i] & 0xff) + 0x100, 16).substring(1));
     }
   System.out.println(hexDigest.toString());
   }  


}
