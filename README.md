# Vaadin UploadField Add-on

Extension of the Vaadin Upload component to allow usage in forms and enhance multi file uploads.

Core class is org.vaadin.addons.thshsh.upload.UploadField. By default this class uses the com.vaadin.flow.component.upload.receivers.MultiFileBuffer receiver, which can be overridden with other receivers like so:

```
new UploadField(new MultiFileMemoryBuffer())
```

There is very little reason to use any Receiver other than MultiFileBuffer or MultiFileMemoryBuffer, as the UploadField itself supports limiting the maxFiles to 1 regardless of the underlying receiver implementation.

### Features and Enhancements
* Field can be used with Binder to read/write a collection of org.vaadin.addons.thshsh.upload.UploadFile objects on an entity.
* UploadFile POJO holds all necessary properties to work with the uploaded data
* Disallow duplicate file names
* Field Value can be set on sever side even if no data has been uploaded to allow user to delete file references
* Temp Directory can be overridden
* Temp files can preserve upload file name and extension

```
        UploadField field = new UploadField();
        
        //override some defaults
        field.setAllowDuplicateNames(true); //default is false
        field.setTempDirectory(Files.createTempDirectory("mytempdir").toFile());   //default auto generates temp dir
        field.setPreserveFileNames(true); //default is false, which uses temp file names
        field.setMaxFiles(5);  //default is null (no-limit)
        field.setAutoUpload(false);  //default is true
        
        //Can set values without data to allow user to manipulate
        List<UploadFile> files = List.of(new UploadFile(new FileData("serverfile.txt","txt",new ByteArrayOutputStream()),null));
        field.setValue(files);
```



 
