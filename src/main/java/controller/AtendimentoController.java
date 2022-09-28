
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.AtendimentoModel;
import util.ConnectionFactory;


public class AtendimentoController {
    
    public int save(AtendimentoModel atendimentoModel) {
        
       
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        entityManager.persist(atendimentoModel);
        
        entityManager.getTransaction().commit();
        //RollbackOnly serve para não salvar alterações de teste
        //entityManager.getTransaction().getRollbackOnly();
        
        entityManager.close();
        entityManagerFactory.close();
        
        return atendimentoModel.getId();
                
        
    }
    
    public  List<AtendimentoModel> getAll() {
        
       EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       
       try{
           Query query = entityManager.createQuery("from AtendimentoModel");
           return query.getResultList();
           
           
       }finally{
           entityManager.close();
           entityManagerFactory.close();
           
       }
    }
    
    public AtendimentoModel getFirst() throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
         EntityManager entityManager = entityManagerFactory.createEntityManager();
       
       try{
           Query query = entityManager.createQuery("from AtendimentoModel where status = 0 order by id asc");
           query.setMaxResults(1);
           
           return (AtendimentoModel) query.getSingleResult();
           
           
       }finally{
           entityManager.close();
           entityManagerFactory.close();
    }
    }
    
    public List<AtendimentoModel> getNextList() throws SQLException {
        
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
         EntityManager entityManager = entityManagerFactory.createEntityManager();
       
       try{
           Query query = entityManager.createQuery("from AtendimentoModel where status = 0 order by id asc");
          
           query.setMaxResults(3);
           
           return query.getResultList();
           
           
       }finally{
           entityManager.close();
           entityManagerFactory.close();
    }
    }
    
    

       
    
    public AtendimentoModel getChamado() throws SQLException {
        
         EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
         EntityManager entityManager = entityManagerFactory.createEntityManager();
       
       try{
           Query query = entityManager.createQuery("from AtendimentoModel where status = 1 order by id asc 1");
          
           query.setMaxResults(1);
           
           return (AtendimentoModel) query.getSingleResult();
           
       }finally{
           entityManager.close();
           entityManagerFactory.close();
    }
    }
    
    public List <AtendimentoModel> getChamadosList() throws SQLException{
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
         EntityManager entityManager = entityManagerFactory.createEntityManager();
       
       try{
           Query query = entityManager.createQuery("from AtendimentoModel where status = 2 order by id desc limit 3");
          
           query.setMaxResults(3);
           
           return query.getResultList();
           
           
       }finally{
           entityManager.close();
           entityManagerFactory.close();
    }
    }
    
        
    

    
    public void updateJaAtendido() throws SQLException {
        
        /* EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
         EntityManager entityManager = entityManagerFactory.createEntityManager();
       
       try{
           Query query = entityManager.createQuery("update AtendimentoModel set status = 2 where status = 1");
          
           query.getResultList();
        
           
           
           
       }finally{
           entityManager.close();
           entityManagerFactory.close();*/
        
        
        String sql = "UPDATE ATENDIMENTO SET STATUS = 2 "
                + "WHERE STATUS = 1";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar atualizar para clientes já atendidos" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    

    public void update(AtendimentoModel atendimentoModel) throws SQLException {
        
         EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        entityManager.merge(atendimentoModel);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        entityManagerFactory.close();
        
      
    }
}