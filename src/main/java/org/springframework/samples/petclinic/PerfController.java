package org.springframework.samples.petclinic;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
public class PerfController {

	@Autowired
	JdbcTemplate jdbc;

	@GetMapping("/process")
	public String process(@RequestParam(defaultValue="300") int work) {

		// 1. Record the exact start time
		long startTime = System.currentTimeMillis();

		int n = Math.max(50, work);
		double[][] a = new double[n][n];
		double[][] b = new double[n][n];

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				a[i][j] = i + j + 1.0;
				b[i][j] = i - j + 1.0;
			}
		}

		double sum = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				for(int k = 0; k < n; k++){
					sum += a[i][k] * b[k][j];
				}
			}
		}

		// 2. Record the exact end time
		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;

		// 3. Print the result in the terminal and on the webpage
		System.out.println("Endpoint hit! CPU burn took: " + timeTaken + " ms");
		return "Processed with work=" + n + ". Time taken: " + timeTaken + " ms";
	}

}
