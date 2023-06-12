package org.vaadin.addons.thshsh.easyrender;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.thshsh.upload.UploadField;
import org.vaadin.addons.thshsh.upload.UploadFile;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route("")
public class AddonView extends VerticalLayout {

    private static final long serialVersionUID = -5828371605864015426L;
    
   
    public AddonView() {
        
        UploadField field;
        
        add(new Span(MemoryBuffer.class.getSimpleName()+", AutoUpload=true, MaxFiles=1"));
        field = addUploadField(new UploadField(new MemoryBuffer()));
        
        add(new Span(MultiFileBuffer.class.getSimpleName()+", AutoUpload=true, MaxFiles=4"));
        field = addUploadField(new UploadField());
        field.setMaxFiles(4);
        
        add(new Span(MultiFileBuffer.class.getSimpleName()+", AutoUpload=true, MaxFiles=1"));
        field = addUploadField(new UploadField());
        field.setMaxFiles(1);
        
        add(new Span(MultiFileMemoryBuffer.class.getSimpleName()+", AutoUpload=false, MaxFiles=3"));
        field = addUploadField(new UploadField(new MultiFileMemoryBuffer()));
        field.setAutoUpload(false);
        field.setMaxFiles(3);
        

    }
    
    protected UploadField addUploadField(UploadField field) {
       
        Bean bean = new Bean();
        Binder<Bean> binder = new Binder<>();
        
        //UploadField field = new UploadField(c.getDeclaredConstructor().newInstance());
        add(field);
        binder.forField(field).bind(Bean::getFiles, Bean::setFiles);
        
        Span message = new Span();
        add(message);
        
        Button bind = new Button("Bind",click -> {
            try {
                binder.writeBean(bean);
                message.setText("Bean Files set to: "+bean.getFiles());
            } 
            catch (ValidationException e) {
            }
        });
        add(bind);
        
        binder.readBean(bean);
        add(new Hr());
        
        return field;
        
    }
    
    public static class Bean {
        
        List<UploadFile> files;

        public Bean() {
            super();
        }

        public List<UploadFile> getFiles() {
            if(files == null) files = new ArrayList<>();
            return files;
        }

        public void setFiles(List<UploadFile> files) {
            this.files = files;
        }
    	
		
		
    	
		
    }
}
