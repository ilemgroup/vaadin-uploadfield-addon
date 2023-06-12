package org.vaadin.addons.thshsh.upload;

import com.vaadin.flow.component.upload.Receiver;

public class ReceiverClassUnsupportedException extends IllegalArgumentException {

    private static final long serialVersionUID = -4086226504833536883L;

    public ReceiverClassUnsupportedException(Receiver r) {
        super("Receiver class "+r.getClass()+"not supported");
    }
    
    public ReceiverClassUnsupportedException() {
        super();
    }

    public ReceiverClassUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceiverClassUnsupportedException(String s) {
        super(s);
    }

    public ReceiverClassUnsupportedException(Throwable cause) {
        super(cause);
    }
    
    

}
