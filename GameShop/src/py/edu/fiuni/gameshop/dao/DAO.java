/**
 *UNIVERSIDAD NACIONAL DE ITAPUA
 *PROJECT GAMESHOP
 *
 * 2020 - SEGUNDO SEMESTRE
 *
 *  Jorge Tyrakowski & Pamela Horn
 * */
package py.edu.fiuni.gameshop.dao;


public interface DAO <M>{
    
    public boolean add(M m);
    
    public boolean remove(M m);
    
    public boolean modify(M m);
   
    public M getById(int id);

    
}
