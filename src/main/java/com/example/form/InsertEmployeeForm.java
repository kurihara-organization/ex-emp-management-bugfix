package com.example.form;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

/**
 * 従業員情報登録時に使用するフォーム.
 *
 * @author koki.kurihara
 *
 */
public class InsertEmployeeForm {
    /** 従業員名 */
    @NotBlank(message = "名前は必須です")
    @Size(max = 50, message = "名前は50文字以内で入力してください")
    private String name;

    /**
     * 画像
     */
    @NotBlank(message = "入力必須です")
    private MultipartFile imageFile;
    /** 性別 */
    @NotBlank(message = "性別は必須です")
    @Pattern(regexp = "^(男性|女性)$", message = "性別は「男性」または「女性」を選択してください")
    private String gender;

    /** 入社日 */
    @NotNull(message = "入社日は必須です")
    @PastOrPresent(message = "入社日は今日以前の日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    /** メールアドレス */
    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレスの形式が正しくありません")
    @Size(max = 100, message = "メールアドレスは100文字以内で入力してください")
    private String mailAddress;

    /** 郵便番号（数字3桁+ハイフン+数字4桁） */
    @NotBlank(message = "郵便番号は必須です")
    @Pattern(regexp = "^\\d{3}-\\d{4}$", message = "郵便番号の形式はXXX-XXXXで入力してください")
    private String zipCode;

    /** 住所 */
    @NotBlank(message = "住所は必須です")
    @Size(max = 200, message = "住所は200文字以内で入力してください")
    private String address;

    /** 電話番号（ハイフン区切り） */
    @NotBlank(message = "電話番号は必須です")
    @Pattern(regexp = "^\\d{2,4}-\\d{2,4}-\\d{3,4}$", message = "電話番号の形式が正しくありません（例: 03-1234-5678）")
    private String telephone;

    /** 給料 */
    @NotNull(message = "給料は必須です")
    @Min(value = 0, message = "給料は0以上で入力してください")
    private Integer salary;

    /** 特性（自由記述） */
    @Size(max = 500, message = "特性は500文字以内で入力してください")
    private String characteristics;

    /** 扶養人数 */
    @NotNull(message = "扶養人数は必須です")
    @Min(value = 0, message = "扶養人数は0人以上で入力してください")
    @Max(value = 20, message = "扶養人数は20人以下で入力してください")
    private Integer dependentsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Integer getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(Integer dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "InsertEmployeeForm{" +
                "name='" + name + '\'' +
                ", imageFile=" + imageFile +
                ", gender='" + gender + '\'' +
                ", hireDate=" + hireDate +
                ", mailAddress='" + mailAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", characteristics='" + characteristics + '\'' +
                ", dependentsCount=" + dependentsCount +
                '}';
    }
}
