import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;


public class FTPOperations {


	public static void main(String[] args) {
		String ipAddress ="ftp.fu-berlin.de";
		String dirAddress = "/misc/movies/database";
		FTPClient client = null;
		
        try
        {
                // Get the FTP Connection from the Utility class
                client = FTPUtility.connect(ipAddress,"anonymous","foo");
                client.changeDirectory(dirAddress);
                if (client != null)
                {
                        /* List all file inside the directory */
                        FTPFile[] fileArray = client.list();
                        
                        for (int i = 0; i < fileArray.length; i++)
                        {
                                FTPFile file = fileArray[i];

                                if (file != null)
                                {
                                	String fileName = file.getName(); 
                                        
                                        if(fileName.equalsIgnoreCase("ratings.list.gz"))
                                        {
                                        	client.download("ratings.list.gz", new java.io.File("E:/ratings.list.gz"), new MyTransferListener());
                                        }
                                }
                        }
                }
        }
        catch (Exception e)
        {
                System.err.println("ERROR : Error in Connecting to Remote Machine... Hence exitting...");
                // e.printStackTrace();
                System.exit(2);
        }

        finally
        {
                try
                {
                        client.disconnect(true);
                }
                catch (Exception e)
                {
                	System.err.println("ERROR: Error in disconnecting Remote Machine");
                }
        }
        System.exit(0);
	}

}
