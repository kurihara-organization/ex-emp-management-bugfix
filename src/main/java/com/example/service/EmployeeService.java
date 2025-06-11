package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * keyword曖昧検索された従業員一覧情報を入社日順で取得します.
	 *
	 * @param keyword キーワード
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
	 */
	public List<Employee> searchByName(String keyword) {
		return employeeRepository.findByNameLike(keyword);
	}

	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return 従業員情報一覧
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	public List<String> showAllEmployeeNames() {
		List<Employee> employees = employeeRepository.findAll();
		List<String> employeeNamesList = new ArrayList<>();

		for (Employee employee : employees) {
			employeeNamesList.add(employee.getName());
		}
///		//メモ:StreamAPIバージョン
//		List<Employee> employeeList = employeeRepository.findAll();
//		List<String> employeeNamesList = employeeList.stream()// Stream<Employee>
//				.map(emp -> emp.getName())// Stream<String>
//				.collect(Collectors.toList());// List<String>

		return employeeNamesList;
	}

	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws org.springframework.dao.DataAccessException 検索されない場合は例外が発生します
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}

	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee 更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
}
