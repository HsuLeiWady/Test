package studentmanagementsystem.models;

import java.util.List;

import studentmanagementsystem.persistant.DTO.CourseResponseDTO;

public class StudentBean {

	private int id;
	private String name;
	private String dob;
	private String gender;
	private String phno;
	private String education;
	private String attend;
	private String photo;
	private List<CourseResponseDTO>courses;
	private List<Integer> course;

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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;		
	}
	public List<CourseResponseDTO> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseResponseDTO> courses) {
		this.courses = courses;
	}
	public List<Integer> getCourse() {
		return course;
	}
	public void setCourse(List<Integer> course) {
		this.course = course;
	}
}
