/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.sac.Matiere;
import model.sac.Taille;
import model.sac.Unite;
import rc.config.__Connection;

/**
 *
 * @author Ranto Jeremy
 */
@WebServlet(name = "MatiereController", urlPatterns = {"/MatiereController"})
public class MatiereController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try{
            
            __Connection __co = new __Connection();
            Connection co = __co.getConnect();
            try{
                Unite[] unites = (Unite[]) new Unite().Select(co, false, 0, 100);
                request.setAttribute("unites", unites);
                
                if(request.getParameter("nom_matiere") != null && request.getParameter("id_unite") != null){
                    Matiere matiere = new Matiere();
                    matiere.setNom(request.getParameter("nom_matiere"));
                    matiere.setId_unite(Integer.parseInt(request.getParameter("id_unite")));
                    matiere.Save(co, false);
                }
            }finally{
                co.close();
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.setAttribute("page", "ajoutMatiere.jsp");
        RequestDispatcher dispat = request.getRequestDispatcher("accueil.jsp");
        dispat.forward(request, response);
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

}
