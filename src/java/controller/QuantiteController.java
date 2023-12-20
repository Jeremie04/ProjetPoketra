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
import model.sac.MatiereLook;
import model.sac.Modele;
import model.sac.Quantite;
import model.sac.Taille;
import model.sac.V_Matiere_Look;

/**
 *
 * @author itu
 */
@WebServlet(name = "QuantiteController", urlPatterns = {"/QuantiteController"})
public class QuantiteController extends HttpServlet {

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
            Modele modele = new Modele();
            modele.setOrder("DESC","id");
            modele = (Modele)modele.Select(null, true, 0, 1)[0];
            request.setAttribute("modele", modele);
            
            V_Matiere_Look matiereLook = new V_Matiere_Look();
            matiereLook.setIdLook(modele.getId_look());
            V_Matiere_Look[] matiereLooks = (V_Matiere_Look[])matiereLook.Select(null, true, 0, 100);
            request.setAttribute("matiereLook", matiereLooks);
            
            Taille[] tailles = (Taille[])new Taille().Select(null, true, 0, 100);
            request.setAttribute("taille", tailles);
            
            request.setAttribute("page", "Quantite.jsp");
            RequestDispatcher dispat = request.getRequestDispatcher("accueil.jsp");
            dispat.forward(request, response);
        }catch(Exception e){
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
            Quantite q = new Quantite();
            int idMatiereLook = Integer.parseInt(request.getParameter("idMatiereLook"));
            System.out.print(idMatiereLook);
            q.setIdMatiereLook(idMatiereLook);
            int idModele = Integer.parseInt(request.getParameter("idModele"));
            q.setIdModele(idModele);
            int idTaille = Integer.parseInt(request.getParameter("idTaille"));
            q.setIdTaille(idTaille);
            double quantite = Double.parseDouble(request.getParameter("quantite"));
            q.setQuantite(quantite);
            
            q.Save(null, true);
            
            response.sendRedirect(request.getContextPath() + "/QuantiteController");
        }catch(Exception e){
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
