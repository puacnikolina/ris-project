package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.OrderRepository;

import model.Order;
import model.OrdersHasProduct;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	private OrderRepository orderRepository;

	public JasperPrint generateOrderReport(Integer orderId) throws JRException, IOException {
		System.out.println("OrderId: " + orderId);

		Order order = orderRepository.findById(orderId).orElse(null);
		if (order == null) {
			throw new IllegalArgumentException("Order not found with id: " + orderId);
		}

		System.out.println("Number of products in order: " + order.getOrdersHasProducts().size());

		List<Map<String, Object>> reportData = new ArrayList<>();

		for (OrdersHasProduct ohp : order.getOrdersHasProducts()) {
			Map<String, Object> row = new HashMap<>();
			row.put("order", order);
			row.put("product", ohp.getProduct());
			row.put("quantity", ohp.getQuantity());
			reportData.add(row);
		}

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);

		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/Receipt.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order", order);
		params.put("orderDate", order.getOrderDate());
		params.put("deliveryDate", order.getDeliveryDate());
		params.put("totalPrice", order.getTotalPrice());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();

		return jasperPrint;
	}

	public byte[] convertToPDF(JasperPrint jasperPrint) throws JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		return baos.toByteArray();
	}
}