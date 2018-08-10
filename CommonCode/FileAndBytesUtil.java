
import java.io.*;

/**
 * @ClassName: FileToBytesUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016年7月26日 下午7:24:55
 * @since JDK 1.7
 * @see
 */
public class FileAndBytesUtil {

	/**
	 * @Title: fileToBytes
	 * @date 2016年7月26日 下午7:28:06
	 */
	public static byte[] fileToBytes(File file){
		byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return buffer;
	}
	/**
	 * @Title: bytesToFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @date 2016年7月26日 下午7:31:24
	 */
	public static void bytesToFile(String filePath,byte[] bytes){
		BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            file = new File(filePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
