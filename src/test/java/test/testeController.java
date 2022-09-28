
package test;

import controller.AtendimentoController;
import java.util.Date;
import java.util.List;
import model.AtendimentoModel;
import org.junit.Test;

public class testeController {
    
    
    @Test
    public void testeControllerInsert(){
        
        AtendimentoModel atendimentoModel = new AtendimentoModel();
        
        atendimentoModel.setNome("teste" + new Date());
        atendimentoModel.setData(new Date());
        
        AtendimentoController atendimentoController = new AtendimentoController();
        
        atendimentoController.save(atendimentoModel);
        
        Integer cod = atendimentoModel.getId();
        
        if (cod != null){
            System.out.println("registro inserido" + atendimentoModel.toString());
            
        }else{
            System.err.println("falha ao inserir o registro");
        }
           
        
    }
    
    @Test
    public void testeGetAll(){
        AtendimentoController atendimentoController = new AtendimentoController();
        
        List<AtendimentoModel> atendimentoModels = new AtendimentoController().getAll();
        
        for (AtendimentoModel atendimentoModel : atendimentoModels) {
            System.out.println("ok" + atendimentoModel.toString());
            
        }
        
        
    }
}
