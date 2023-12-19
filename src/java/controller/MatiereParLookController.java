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
import model.sac.Look;
import model.sac.Matiere;
import model.sac.MatiereLook;
import rc.config.__Connection;

/**
 *
 * @author Ranto Jeremy
 */
@WebServlet(name = "MatiereParLookController", urlPatterns = {"/MatiereParLookController"})
public class MatiereParLookController extends HttpServlet {

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
                Matiere[] matieres = new Matiere[0];
                
                Look[] looks = (Look[]) new Look().Select(co, false, 0, 100);
                request.setAttribute("looks", looks);
                
                if(request.getParameter("id_look") != null) {
                    Look l = new Look();
                    l.setId(Integer.parseInt(request.getParameter("id_look")));
                    
                    matieres = l.getMatieres(co);
                }
                
                request.setAttribute("matieres", matieres);
            }finally{
                co.close();
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.setAttribute("page", "matiereParLook.jsp");
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
