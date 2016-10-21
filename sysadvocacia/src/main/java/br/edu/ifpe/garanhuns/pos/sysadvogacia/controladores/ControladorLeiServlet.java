/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.pos.sysadvogacia.controladores;

import br.edu.ifpe.garanhuns.pos.sysadvogacia.entidades.Lei;
import br.edu.ifpe.garanhuns.pos.sysadvogacia.entidades.Processo;
import br.edu.ifpe.garanhuns.pos.sysadvogacia.negocio.NegocioLei;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ewerton
 */
@WebServlet(name = "ControladorLeiServlet", urlPatterns = {"/SalvarLei", "/RemoverLei", "/ListarLeis","/ListarLeis"})
public class ControladorLeiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        
        NegocioLei negocioLei = new NegocioLei();
        
        Processo proc = new Processo();
        proc.setCodigo(1);
                
        Lei lei;
        
        
        if (userPath.equals("/SalvarLei")) {
            
            lei = new Lei();
            
            if (!request.getParameter("codigo").isEmpty()) {
                lei.setCodigo(Integer.parseInt(request.getParameter("codigo")));
            } else {                
                lei.setCodigo(0);           
            }
            lei.setArtigo(Integer.parseInt(request.getParameter("artigo"))); 
            lei.setDescricao(request.getParameter("descricao"));
            lei.setTipo(request.getParameter("tipo"));                      
            lei.setCapitulo(Integer.parseInt(request.getParameter("capitulo")));   
           
            lei.setProcessoCodigo(proc);
            
            String json = new Gson().toJson(negocioLei.salvar(lei));
            
            response.getWriter().print(json);
        }
        
        
        if (userPath.equals("/ListarLeis")){
            String json = new Gson().toJson(negocioLei.listarLeis());
            response.getWriter().print(json);            
        }
        if(userPath.equals("/RemoverLei")){
            String id = request.getParameter("id");
            lei = negocioLei.LeiPorCodigo(Integer.parseInt(id));
            negocioLei.remover(lei);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("sucess", "true");
            response.getWriter().print(new Gson().toJson(jsonObject));
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
