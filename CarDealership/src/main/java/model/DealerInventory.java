package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jeff Johnson - jjohnson190
 * CIS175 - Spring 2024
 * Jan 26, 2024
 */

@Entity
@Table(name = "cars")
public class DealerInventory {
	
	@Id
	@GeneratedValue
	private int id;
	private String vehicleMake;
	private String vehicleModel;
	
	public DealerInventory() {

	}

	public DealerInventory(String vehicleMake, String vehicleModel) {
		super();
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	@Override
	public String toString() {
		return "DealerInventory [id=" + id + ", vehicleMake=" + vehicleMake + ", vehicleModel=" + vehicleModel + "]";
	}
	
	

}
