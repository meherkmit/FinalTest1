package can.can.Final.server;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import can.can.Final.client.GreetingService;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.util.ContentType;
import com.google.gdata.util.ServiceException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	
	public String greetServer(String token) throws IllegalArgumentException {	
		//Creating the DocsService used to upload and setting the Authorized token.
		DocsService client = new DocsService("UploadDemo");
	    client.setAuthSubToken(token);
	    
	    String result= new String("File Uploaded successfully");
	    
        try {
         
         //Hard coding the file to be uploaded.
	     File f =new File("D:/GGWeb/FinalTest/war/Sunset.jpg");
	     
	     
	     //FirstItemImple class that implements an interface org.apache.commons.fileupload.FileItem. 
	     FileItemImpl file= new FileItemImpl(f);
	     
	     //creating the new entry, this will be the file we upload.
	     DocumentListEntry newDocument = new DocumentListEntry();
	     
	     String mimeType = DocumentListEntry.MediaType.fromFileName(file.getName()).getMimeType(); 
	     System.out.println("Content type: "+mimeType);
	     	
	     	//setting the MediaSource to the entry by converting the file to its byte array and setting the content type(eg: image/png etc)
            newDocument.setMediaSource(new MediaByteArraySource(file.get(),mimeType));
            //setting the title to the entry. This file will be displayed with this title.
            newDocument.setTitle(new PlainTextConstruct(file.getName()));
            //giving some description to the entry
            String description="UploadDemo";
            newDocument.setDescription(description);
            
            System.out.println("Uploaded document with description " + description);
            String DOCUMENT_URL="https://docs.google.com/feeds/default/private/full/";
            
            //Sets the default wait timeout (in milliseconds) for a connection to the remote GData service
            client.setConnectTimeout(0);
            System.out.println("b4: "+client.getContentType().getMediaType()); 
            //setting the DocsService content type to render http request and response. 
            //client.setContentType(new ContentType("text/html"));
            //System.out.println("after: "+client.getContentType().getMediaType());
            //insert's the entry to google docs
            client.insert(new URL(DOCUMENT_URL),newDocument);

        } 
        catch (MalformedURLException e) {
            e.printStackTrace();
        	System.out.println("MalformedURLException");
        	result="MalformedURLException";
        } catch (IOException e) {
            e.printStackTrace();
        	System.out.println("IOException");
        	result="IOException";
        } catch (ServiceException e) {
            e.printStackTrace();
        	System.out.println("ServiceException");
        	result="ServiceException";
        }
   	 	       
		return result;
        
	}

}
