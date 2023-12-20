/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.sac.Look;
import model.sac.Modele;
import model.sac.Taille;
import model.sac.Type;

/**
 *
 * @author itu
 */
@WebServlet(name = "ModeleController", urlPatterns = {"/ModeleController"})
public class ModeleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        try{
            Type[] types = (Type[])new Type().Select(null, true, 0, 100);
            request.setAttribute("types", types);
            
            Look[] looks = (Look[])new Look().Select(null, true, 0, 100);
            request.setAttribute("looks", looks);
            request.setAttribute("page", "creationModele.jsp");
            
            RequestDispatcher dispat = request.getRequestDispatcher("accueil.jsp");
            dispat.forward(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        try{
            Modele modele = new Modele();
            String nom = request.getParameter("nom");
            modele.setNom(nom);
            int idType = Integer.parseInt(request.getParameter("type"));
            modele.setId_type(idType);
            int idLook = Integer.parseInt(request.getParameter("look"));
            modele.setId_look(idLook);
            modele.Save(null, true);
            
            response.sendRedirect(request.getContextPath() + "/QuantiteController");
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
