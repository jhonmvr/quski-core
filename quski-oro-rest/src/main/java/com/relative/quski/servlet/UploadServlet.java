package com.relative.quski.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.relative.core.util.main.Constantes;


@WebServlet("/uploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	
	private static final Log log = LogFactory.getLog(UploadServlet.class);
	
	//private Map<String, FileWrapper> files;

//  private static final long serialVersionUID = 1L;
  private File fileUploadPath;

  @Override
  public void init(ServletConfig config) {
	  
	  
      fileUploadPath = new File("C:/Users/root/AppData/Local/Temp/");
  }
      
  /**
      * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
      * 
      */
 

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  log.info("==============> entra a metodo get en servlet con get file " + request.getParameter("getfile"));
	  log.info("==============> entra a metodo get en servlet con referencia " + request.getParameter("referencia"));
	  
		/*
		 * files =(Map<String, FileWrapper>)request.getSession(false).getAttribute(
		 * Constantes.FILE_UPLOAD_SESSION_ATTRIB ); if( files == null ){ files = new
		 * HashMap<String, FileWrapper>(); }
		 */
	  //log.info("==============> Files registrado " + files.toString());
      
     
  }
  
  /**
      * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
      * 
      */
  @SuppressWarnings("unchecked")
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  log.info("==============> entra a metodo post en servlet");
	  log.info("==============> entra a metodo post en servlet con referencia " + request.getParameter("referencia"));
	  log.info("==============> entra a metodo post en servlet 1 " + request.getSession() );
	  log.info("==============> entra a metodo post en servlet 1 " + request.getSession().getAttribute( Constantes.FILE_UPLOAD_SESSION_ATTRIB ) );
		/*
		 * files =(Map<String, FileWrapper>)request.getSession().getAttribute(
		 * Constantes.FILE_UPLOAD_SESSION_ATTRIB );
		 * log.info("==============> entra a metodo post en servlet " + files); if(
		 * files == null ){ files = new HashMap<String, FileWrapper>(); }
		 * log.info("==============> Files registrado " + files.toString());
		 */
	  
    

  }
  
 

  private String getMimeType(File file) {
      String mimetype = "";
      if (file.exists()) {
          if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
              mimetype = "image/png";
          } else {
              javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
              mimetype  = mtMap.getContentType(file);
          }
      }
      System.out.println("mimetype: " + mimetype);
      return mimetype;
  }

  private String getMimeType(String file) {
      String mimetype = "";
      if (getSuffix(file).equalsIgnoreCase("png")) {
          mimetype = "image/png";
      } else {
          javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
          mimetype  = mtMap.getContentType(file);
      }
      log.info("mimetype: " + mimetype);
      return mimetype;
  }

  private String getSuffix(String filename) {
      String suffix = "";
      int pos = filename.lastIndexOf('.');
      if (pos > 0 && pos < filename.length() - 1) {
          suffix = filename.substring(pos + 1);
      }
      System.out.println("suffix: " + suffix);
      return suffix;
  }
	
}
