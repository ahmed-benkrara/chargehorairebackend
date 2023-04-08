package com.fssm.chargehoraire;

import com.fssm.chargehoraire.Models.*;
import com.fssm.chargehoraire.Repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChargehoraireApplicationTests {

	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private AdminTaskRepository adminTaskRepository;
	@Autowired
	private YearRepository yearRepository;
	@Autowired
	private AdminRepository adminRepository;

	@Test
	void contextLoads() {
		/*Teacher teacher = new Teacher();
		teacher.setFirstName("test");
		teacher.setLastName("test");
		teacher.setEmail("test@test.com");
		teacher.setPassword("1234");
		teacher.setPicture("");
		teacher.setCin("HH123");
		teacherRepository.save(teacher);*/

		/*Admin admin = new Admin();
		admin.setEmail("admin@admin.com");
		admin.setPassword("1234");
		admin.setFirstName("admin");
		admin.setLastName("admin");
		admin.setTheme(0);
		adminRepository.save(admin);*/

/*		Department department = new Department();
		department.setName("Department1");
		departmentRepository.save(department);*/

/*		Year year = new Year();
		year.setName("2022-2023");
		yearRepository.save(year);*/
		/*Year year = yearRepository.findById(Long.valueOf("2")).get();

	  	AdminTaskKey key = new AdminTaskKey();
		key.setDepartment_id(1);
		key.setTeacher_id(1);
		key.setYear(year);
		AdminTask adminTask = new AdminTask();
		adminTask.setKey(key);
		adminTask.setHours(15);
		adminTaskRepository.save(adminTask);*/
	}

}
