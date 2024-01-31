package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.DealerInventory;

/**
 * @author Jeff Johnson - jjohnson190
 * CIS175 - Spring 2024
 * Jan 26, 2024
 */
public class InventoryHelper {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CarDealership");
	
		public void addInventory(DealerInventory dInv) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(dInv);
			em.getTransaction().commit();
			em.close();
		}
		
		public void editInventory(DealerInventory toEdit) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.merge(toEdit);
			em.getTransaction().commit();
			em.close();
		}
		
		public List<DealerInventory> searchForVehicleByMake(String vehicleMake) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<DealerInventory> typedQuery = em.createQuery
	("SELECT di FROM DealerInventory di WHERE di.vehicleMake = :selectedMake", DealerInventory.class);
			
			typedQuery.setParameter("selectedMake", vehicleMake);
			List<DealerInventory> foundInventory = typedQuery.getResultList();
			em.close();
			
			return foundInventory;
		}
		
		public List<DealerInventory> searchForVehicleByModel(String vehicleModel) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<DealerInventory> typedQuery = em.createQuery
	("SELECT di FROM DealerInventory di WHERE di.vehicleModel = :selectedModel", DealerInventory.class);
			
			typedQuery.setParameter("selectedModel", vehicleModel);
			List<DealerInventory> foundInventory = typedQuery.getResultList();
			em.close();
			
			return foundInventory;
		}
		
		public DealerInventory searchForItemByID(int vehicleIdToEdit) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			DealerInventory foundID = em.find(DealerInventory.class, vehicleIdToEdit);			
			em.close();
			
			return foundID;
		}
		
		public void deleteInventory(DealerInventory toRemove) {
			// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			
			// have to make sure to match class instance variable names in statement
			TypedQuery<DealerInventory> typedQuery = em.createQuery
	("SELECT di FROM DealerInventory di WHERE di.vehicleMake = :selectedMake AND di.vehicleModel = :selectedModel", DealerInventory.class);
			
			typedQuery.setParameter("selectedMake", toRemove.getVehicleMake());
			typedQuery.setParameter("selectedModel", toRemove.getVehicleModel());
			typedQuery.setMaxResults(1);
			
			DealerInventory searchResult = typedQuery.getSingleResult();
			
			em.remove(searchResult);
			em.getTransaction().commit();
			em.close();
		}

	public List<DealerInventory> showAllInventory() {
		// TODO Auto-generated method stub
			EntityManager em = emfactory.createEntityManager();
			
			List<DealerInventory> allInventory =
					em.createQuery("SELECT i FROM DealerInventory i").getResultList();
			
			em.close();
			
		return allInventory;
	}
	
		public void closeIH() {
		// TODO Auto-generated method stub
		emfactory.close();
	}



}
