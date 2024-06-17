package studentmanagementsystem.persistant.DTO;

import java.io.InputStream;
import java.util.List;

public class StudentResponseDTO {
	private int id;
	private String name;
	private String email;
	private String DOB;
	private String gender;
	private String phno;
	private String education;
	private String attend;
	private List<CourseResponseDTO> courses;	
	private InputStream photo;
	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

	public StudentResponseDTO() {}
	
	public InputStream getphoto() {
		return photo;
	}

	public void setphoto(InputStream photo) {
		this.photo = photo;
	}
	public List<CourseResponseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseResponseDTO> courses) {
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	public String getAttend() {
		return attend;
	}
	public void setAttend(String attend) {
		this.attend = attend;
	
	}
}