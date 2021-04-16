package servlet;

import bll.BLLException;
import bll.UtilisateurManager;
import bll.UtilisateurManagerImpl;
import bo.Utilisateur;
import dal.DALException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletMotDePasse", value = "/MotDePasse")
public class ServletMotDePasse extends HttpServlet {

    UtilisateurManager usr = new UtilisateurManagerImpl();

    public ServletMotDePasse() throws BLLException, DALException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/MotDePasseOublie.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");

        try {
            Utilisateur user = usr.selectByPseudo(pseudo);
            request.setAttribute("usr", user);

            if (request.getParameter("mdp") != null){
                int nouser = Integer.parseInt(request.getParameter("nouser"));
                Utilisateur user1 = usr.selectById(nouser);
                user1.setMotDePasse(request.getParameter("mdp"));
                usr.updateUser(user1);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
                rd.forward(request, response);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/NouveauMotDePasse.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
