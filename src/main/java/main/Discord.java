/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package main;

import controller.AtendimentoController;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AtendimentoModel;

/**
 *
 * @author jonathandamasiomedeiros
 */
public class Discord {

    public static void main(String[] args) throws SQLException {
        
        AtendimentoModel atendimentoModel = new AtendimentoModel();
//        
//        atendimentoModel.setNome("Hector");
//        atendimentoModel.setData(new Date());
//        atendimentoModel.setStatus(0);
        
        AtendimentoController atController = new AtendimentoController();
        List<AtendimentoModel> atendimentoList = null;
        
        
        try {
            atendimentoList = atController.getAll();
            
//            for (int i = 0; i < atendimentoList.size(); i++){
//                atendimentoModel = atendimentoList.get(i);
//                System.out.println(atendimentoModel);
//            }

            for (AtendimentoModel atend : atendimentoList){
                System.out.println(atend);
                
            }
            
            
        } catch (SQLException ex) {
            throw new SQLException("Interface não conseguiu inserir senha: " + ex.getMessage(),ex);
        }
        
        
        
    }
}
