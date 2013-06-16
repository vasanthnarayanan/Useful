import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class MyTransferListener implements FTPDataTransferListener
{

        public void started()
        {
                // Transfer started
                System.out.println("TRANSFER-STATUS: File transfer started...");
        }

        public void transferred(int length)
        {
                // Yet other length bytes has been transferred since the last time this
                // method was called
        }

        public void completed()
        {
                // Transfer completed
                System.out.println("TRANSFER-STATUS: File transfer completed...");
                UnZip unZip = new UnZip();
                unZip.unZipIt("E:/ratings.list.gz", "E:/");
                ReadFile readFile = new ReadFile();
                readFile.readFileFromIMDB("");
        }

        public void aborted()
        {
                // Transfer aborted
                System.err.println("TRANSFER-STATUS: File transfer aborted...");
        }

        public void failed()
        {
                // Transfer failed
                System.err.println("TRANSFER-STATUS: File transfer failed...");
        }
}