package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */

public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message = "名前は必須です")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	private String name;

	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	@Size(max = 100, message = "メールアドレスは100文字以内で入力してください")
	private String mailAddress;

	/** パスワード */
	@NotBlank(message = "パスワードは必須です")
	@Size(min = 8, max = 20, message = "パスワードは8〜20文字で入力してください")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
