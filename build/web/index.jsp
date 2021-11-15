<%-- 
    Document   : index
    Created on : Mar 20, 2021, 10:16:11 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Example</title>
    </head>
    <body>
        
        <form action="UploadServlet" method="POST" enctype="multipart/form-data">
            
            <input type="text" name="path" value="" /><br/><br/>
            
            <input type="file" name="fileToUpload" value="" size="70"/><br/><br/>
            
            <input type="submit" value="Закачать" name="uploadButton" />
            
        </form>
        
        
        
        
    </body>
</html>
