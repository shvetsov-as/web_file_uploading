/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

import com.ibm.useful.http.FileData;
import com.ibm.useful.http.PostData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class UploadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String contentType = request.getContentType();
        String path=null;
        String finalPath = null;
        if(contentType.contains("multipart/form-data")){// iz body zaprosa
            PostData multidata = new PostData(request);//biblioteka IBM
            path = multidata.getParameter("path") ;
            FileData tempFile = multidata.getFileData("fileToUpload");//izvl file
            
            if(tempFile !=null){
                finalPath = saveFile(tempFile, path);
            }
            //String path = new
            
            
        }//if end
        
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Content type " + contentType + "</h1>");
            out.println("<h1>File Save path " + path + "</h1>");
            out.println("<h1>File Save path " + finalPath + "</h1>");
            out.println("<h1>Servlet UploadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String saveFile(FileData tempFile, String path) {
        String fileName = path + File.separator + tempFile.getFileName();//separat - razdelitel sistemniy (/)
        File f = new File(fileName);
        try(FileOutputStream fos = new FileOutputStream(f)){
            
            fos.write(tempFile.getByteData());
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("path file error" + ex.getMessage());
            return null;
        } catch (IOException ex) {
            Logger.getLogger("IO Exception " + ex.getMessage());
            return null;
        }
        return f.getAbsolutePath();
    }

}
