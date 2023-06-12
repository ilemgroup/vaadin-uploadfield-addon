package org.vaadin.addons.thshsh.upload;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.receivers.FileData;

/**
 * Wraps the standard {@link com.vaadin.flow.component.upload.Receiver} classes with the addon interface
 *
 */
public class ReflectionUploadFieldBuffer implements UploadFieldBuffer {

    protected Receiver receiver;
    protected Method getFileData;
    protected Method getInputStream;
    
    public ReflectionUploadFieldBuffer(Receiver receiver) {
        super();
        this.receiver = receiver;
        
        try {
            getFileData = receiver.getClass().getMethod(UploadFieldBuffer.GET_FILE_DATA, String.class);
        } 
        catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) { 
            try {
                getFileData = receiver.getClass().getMethod(UploadFieldBuffer.GET_FILE_DATA);
            } catch (NoSuchMethodException | SecurityException e1) {
                throw new ReceiverClassUnsupportedException(receiver);
            }
        }
        
        try {
            getInputStream = receiver.getClass().getMethod(UploadFieldBuffer.GET_INPUT_STREAM, String.class);
        } 
        catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) { 
            try {
                getInputStream = receiver.getClass().getMethod(UploadFieldBuffer.GET_INPUT_STREAM);
            } catch (NoSuchMethodException | SecurityException e1) {
                throw new ReceiverClassUnsupportedException(receiver);
            }
        }
      
        
    }
    
    @Override
    public FileData getFileData(String name) {
        try {
            return (FileData) getFileData.invoke(receiver,getFileData.getParameterCount()==0? new Object[0] : new Object[] {name});
        } 
        catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReceiverClassUnsupportedException(receiver);
        }
        
    }

    @Override
    public InputStream getInputStream(String name) {
        try {
            return (InputStream) getFileData.invoke(receiver,getInputStream.getParameterCount()==0? new Object[0] : new Object[] {name});
        } 
        catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReceiverClassUnsupportedException(receiver);
        }
    }

}
