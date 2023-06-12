package org.vaadin.addons.thshsh.upload;

import java.io.File;
import java.io.InputStream;
import java.util.function.Supplier;

import com.vaadin.flow.component.upload.receivers.FileData;


/**
 * Bean class to hold properties of an uploaded file
 *
 */
public class UploadFile {

	protected String mimeType;
	protected String name;
	protected File file;
	//Use a supplier to avoid creating unnecessary streams
	protected Supplier<InputStream> streamProvider;
	
	public UploadFile() {}

	public UploadFile(FileData fileData, Supplier<InputStream> stream) {
		super();
		this.name = fileData.getFileName();
		this.mimeType = fileData.getMimeType();
		this.streamProvider = stream;
		try {
            this.file = fileData.getFile();
        } 
		catch (UnsupportedOperationException e) {
            //the receiver is a in-memory implementation
        }
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getName() {
		return name;
	}

	public Supplier<InputStream> getStream() {
        return streamProvider;
    }

    public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadFile [mimeType=");
		builder.append(mimeType);
		builder.append(", name=");
		builder.append(name);
		builder.append(", file=");
		builder.append(file);
		builder.append("]");
		return builder.toString();
	}
	
	
}
