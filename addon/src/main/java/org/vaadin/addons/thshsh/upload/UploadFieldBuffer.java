package org.vaadin.addons.thshsh.upload;

import java.io.InputStream;

import com.vaadin.flow.component.upload.receivers.FileData;

/**
 * All Custom {@link com.vaadin.flow.component.upload.Receiver}s should implement this to prevent 
 * having to use Reflection
 *
 */
public interface UploadFieldBuffer {

    public static final String GET_FILE_DATA = "getFileData";
    
    public static final String GET_INPUT_STREAM = "getInputStream";
    
    public FileData getFileData(String name);
    
    public InputStream getInputStream(String name);
    
}
