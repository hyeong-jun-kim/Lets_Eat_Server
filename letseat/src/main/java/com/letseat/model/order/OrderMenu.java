package com.letseat.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.letseat.model.restaurant.ResMenu;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class OrderMenu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderMenuId;
	
	@Column(nullable = false)
	private int amount;
	
    @ManyToOne
    @JoinColumn(name = "orderId")
    @NotNull
    private OrderList orderList;
	
	@ManyToOne
	@JoinColumn(name = "resMenuId")
	private ResMenu resMenu;
		
	public void setorderMenu(ResMenu resMenu) {
		this.resMenu = resMenu;
	}
}
