package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.form.InsertEmployeeForm;
import com.example.form.SearchByNameForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@GetMapping("/showList")
	public String showList(Model model, SearchByNameForm form) {
		String keyword = form.getKeyword();
		List<Employee> employeeList = employeeService.searchByName(keyword);

		if ((keyword != null && !keyword.isEmpty()) && employeeList.isEmpty()) {
			model.addAttribute("message", "1件もありませんでした");
			employeeList = employeeService.showList(); // 全件再取得
		}

		List<String> nameCandidates = employeeService.showAllEmployeeNames();
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("nameCandidates", nameCandidates);
		model.addAttribute("keyword", keyword);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@GetMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@PostMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

	@GetMapping("/to-insert")
	public String toInsert(Model model) {
		model.addAttribute("insertEmployeeForm", new InsertEmployeeForm());
		return "employee/insert";
	}

	@PostMapping("/insert")
	public String insert(
			@Validated InsertEmployeeForm form
			, BindingResult result
			, Model model){
		if(result.hasErrors()){
			return "employee/insert";
		}

		// 画像ファイルの保存処理
		MultipartFile imageFile = form.getImageFile();
		String imageName = null;
		if (imageFile != null && !imageFile.isEmpty()) {
			try {
				String originalFilename = imageFile.getOriginalFilename();
				if (originalFilename != null && (originalFilename.endsWith(".jpg") || originalFilename.endsWith(".png"))) {
					imageName = originalFilename;
					Path imagePath = Paths.get("src/main/resources/static/img/" + imageName);
					Files.write(imagePath, imageFile.getBytes());
				} else {
					result.rejectValue("imageFile", null, "画像はjpgまたはpng形式でアップロードしてください");
					return "employee/insert";
				}
			} catch (IOException e) {
				result.rejectValue("imageFile", null, "画像ファイルの保存に失敗しました");
				return "employee/insert";
			}
		}
		//System.out.println(form);
		System.out.println(imageName);
		// フォーム → ドメイン変換
		Employee employee = new Employee();
		employee.setName(form.getName());
		employee.setImage(imageName);
		//employee.setImage("スクリーンショット 2025-05-28 153224.png");
		employee.setGender(form.getGender());
		employee.setHireDate(form.getHireDate());
		employee.setMailAddress(form.getMailAddress());
		employee.setZipCode(form.getZipCode());
		employee.setAddress(form.getAddress());
		employee.setTelephone(form.getTelephone());
		employee.setSalary(form.getSalary());
		employee.setCharacteristics(form.getCharacteristics());
		employee.setDependentsCount(form.getDependentsCount());
		System.out.println(employee);
		// サービス経由で登録
		employeeService.registerEmployee(employee);

		return "redirect:/employee/showList";
	}
}
