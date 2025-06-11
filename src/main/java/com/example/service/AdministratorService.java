package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		String hashedPassword = passwordEncoder.encode(administrator.getPassword());
		administrator.setPassword(hashedPassword);
		administratorRepository.insert(administrator);
	}

	/**
	 * ログインをします.
	 *
	 * @param mailAddress メールアドレス
	 * @param rawPassword　平文パスワード
	 * @return ログインするときの管理者情報
	 */
	public Administrator login(String mailAddress, String rawPassword) {
		// 入力されたメールアドレスから管理者情報を取得
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		// ユーザが存在しない or パスワードが不一致の場合はnullを返す
		if (administrator == null || !passwordEncoder.matches(rawPassword, administrator.getPassword())) {
			return null;
		}
		return administrator;
	}

	/**
	 * メールアドレスで検索します.
	 *
	 * @param mailAddress メールアドレス
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
    public Administrator findByMailAddress(String mailAddress) {
		return administratorRepository.findByMailAddress(mailAddress);
    }
}
