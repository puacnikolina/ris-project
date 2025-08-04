package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/report/")
public class ReportController {


	@Autowired
	ReportService rs;
	
	@GetMapping("downloadOrderReport/{orderId}")
	public ResponseEntity<byte[]> downloadOrderReport(@PathVariable Integer orderId) {
	    try {
	        JasperPrint jasperPrint = rs.generateOrderReport(orderId);
	        byte[] reportBytes = rs.convertToPDF(jasperPrint);
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("attachment", "order_report_" + orderId + ".pdf");
	        headers.setContentLength(reportBytes.length);
	        
	        return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
	        
	    } catch (JRException | IOException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
