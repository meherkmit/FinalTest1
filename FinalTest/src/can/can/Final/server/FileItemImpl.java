package can.can.Final.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class FileItemImpl implements org.apache.commons.fileupload.FileItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static File file;
	public FileItemImpl(File file)
	{
		this.file=file;
	}

	@Override
	public void delete() {
		file.delete();
		
	}

	@Override
	public byte[] get() {
		
		InputStream is;
		long length = file.length();

	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    	System.out.println("File too large");
	    	System.exit(1);
	    }

	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];

		try {
			is = new FileInputStream(file);
		
	    
	    // Get the size of the file
	    
	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    // Close the input stream and return bytes
	    is.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    return bytes;
	}

	@Override
	public String getContentType() {
		
		return null;
	}

	@Override
	public String getFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return file.getName();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String arg0) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFormField() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInMemory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFieldName(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFormField(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(File arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
