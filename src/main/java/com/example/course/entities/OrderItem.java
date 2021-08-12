package com.example.course.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;
	private Double price;

	public OrderItem() {

	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		this.quantity = quantity;
		this.price = price;
		this.id.setOrder(order);
		this.id.setProduct(product);
	}

	@JsonIgnore
	public Order getOrder() {
		return this.id.getOrder();
	}

	public void setOrder(Order order) {
		this.id.setOrder(order);
	}

	public Product getProduct() {
		return this.id.getProduct();
	}

	public void setProduct(Product product) {
		this.id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Double getSubTotal() {
		return this.price * this.quantity;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

}
