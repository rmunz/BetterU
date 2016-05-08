/*
 * Created by Ojas Mhetar on 2016.02.27  * 
 * Copyright © 2016 Ojas Mhetar. All rights reserved. * 
 */
package com.betteru.managers;

import com.betteru.sourcepackage.User;
import com.betteru.sessionbeanpackage.PhotoFacade;
import com.betteru.sessionbeanpackage.UserFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.UploadedFile;

@Named(value = "fileManager")
@ManagedBean
@SessionScoped
/**
 *
 * @author Mhetar
 */
public class FileManager implements Serializable{

    // Instance Variables (Properties)
    private UploadedFile file;
    private String message = "";
    
    /**
     * The instance variable 'userFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @Stateless session bean UserFacade.
     */
    @EJB
    private UserFacade userFacade;

    /**
     * The instance variable 'photoFacade' is annotated with the @EJB annotation.
     * This means that the GlassFish application server, at runtime, will inject in
     * this instance variable a reference to the @Stateless session bean PhotoFacade.
     */
    @EJB
    private PhotoFacade photoFacade;

    // Returns the uploaded file
    public UploadedFile getFile() {
        return file;
    }

    // Obtains the uploaded file
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    // Returns the message
    public String getMessage() {
        return message;
    }

    // Obtains the message
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * "Profile?faces-redirect=true" asks the web browser to display the
     * Profile.xhtml page and update the URL corresponding to that page.
     * @return Profile.xhtml or nothing
     */

    public String upload() {
        if (file.getSize() != 0) {
            copyFile(file);
            message = "";
            return "MyAccount?faces-redirect=true";
        } else {
            message = "You need to upload a file first!";
            return "";
        }
    }
    
    
    
    public String cancel() {
        message = "";
        return "MyAccount?faces-redirect=true";
    }

    public FacesMessage copyFile(UploadedFile file) {
        try {
            
            
            //deletePhoto();
            
            InputStream in = file.getInputstream();

            System.out.println("\n\n\n\n\n\n\n\n HELLO TEST");
            
            File tempFile = inputStreamToFile(in, Constants.TEMP_FILE);
            byte[] bytes = loadFile(tempFile);
            byte[] encoded = Base64.encodeBase64(bytes);
            System.out.println(encoded);
            String encodedString = new String(encoded);
            
             System.out.println(encodedString);
            
            System.out.println("\n\n\n\n\n\n\n\n Upto");
            int user_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user_id");
            User editUser = userFacade.getUser(user_id);
            //System.out.println(user_name);
            //User user = userFacade.findByUsername(user_name);
            
            
            
            try {
                System.out.println("\n\n\n\n\n\n\n\n IN TRY");
                editUser.setPhoto(encodedString);
                userFacade.edit(editUser);
            } catch (EJBException e) {
                System.out.println("\n\n\n\n\n\n\n\n IN CATCH");
                
                //statusMessage = "Something went wrong while editing your profile!";
                //return "";
                return new FacesMessage("Failure!", "Something went wrong while editing your profile!");
            }
            return new FacesMessage("Success!", "File Successfully Uploaded!");
            /*
            in.close();

            FacesMessage resultMsg;

            String user_name = (String) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("username");

            User user = userFacade.findByUsername(user_name);

            // Insert photo record into database
            String extension = file.getContentType();
            extension = extension.startsWith("image/") ? extension.subSequence(6, extension.length()).toString() : "png";
            List<Photo> photoList = photoFacade.findPhotosByUserID(user.getId());
            if (!photoList.isEmpty()) {
                photoFacade.remove(photoList.get(0));
            }
            
            //photoFacade.create(new Photo(extension, user));
            //Photo photo = photoFacade.findPhotosByUserID(user.getId()).get(0);
            in = file.getInputstream();
            
            File uploadedFile = inputStreamToFile(in, photo.getFilename());
            //saveThumbnail(uploadedFile, photo);
            resultMsg = new FacesMessage("Success!", "File Successfully Uploaded!");
            return resultMsg;*/
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FacesMessage("Upload failure!",
            "There was a problem reading the image file. Please try again with a new photo file.");
    }

    private File inputStreamToFile(InputStream inputStream, String childName)
            throws IOException {
        // Read in the series of bytes from the input stream
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        // Write the series of bytes on file.
        File targetFile = new File(Constants.ROOT_DIRECTORY, childName);

        OutputStream outStream;
        outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
        outStream.close();

        // Save reference to the current image.
        return targetFile;
    }

    
    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }
    
//    private void saveThumbnail(File inputFile, Photo inputPhoto) {
//        try {
//            BufferedImage original = ImageIO.read(inputFile);
//            BufferedImage thumbnail = Scalr.resize(original, Constants.THUMBNAIL_SZ);
//            ImageIO.write(thumbnail, inputPhoto.getExtension(),
//                new File(Constants.ROOT_DIRECTORY, inputPhoto.getThumbnailName()));
//        } catch (IOException ex) {
//            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void deletePhoto() {
        /*
        FacesMessage resultMsg;
        String user_name = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("username");

        User user = userFacade.findByUsername(user_name);

        List<Photo> photoList = photoFacade.findPhotosByUserID(user.getId());
        if (photoList.isEmpty()) {
            resultMsg = new FacesMessage("Error", "You do not have a photo to delete.");
        } else {
            Photo photo = photoList.get(0);
            try {
                Files.deleteIfExists(Paths.get(photo.getFilePath()));
                Files.deleteIfExists(Paths.get(photo.getThumbnailFilePath()));
                
                Files.deleteIfExists(Paths.get(Constants.ROOT_DIRECTORY+"tmp_file"));
                 
                photoFacade.remove(photo);
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            resultMsg = new FacesMessage("Success", "Photo successfully deleted!");
        }
        FacesContext.getCurrentInstance().addMessage(null, resultMsg);
        */
    }
}