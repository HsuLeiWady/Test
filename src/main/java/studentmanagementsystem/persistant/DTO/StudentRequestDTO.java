package studentmanagementsystem.persistant.DTO;

import java.util.List;

public class StudentRequestDTO {
	private int id;
    private String name;
    private String email;
    private String dob;
    private String gender;
    private String phno;
    private String education;
    private String attend;
    private byte[] photo;
    private List<String> course_id;

    // Getters and Setters
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
    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    public List<String> getCourse_id() {
        return course_id;
    }
    public void setCourse_id(List<String> course_id) {
        this.course_id = course_id;
    }
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}
	public void setCourse_id(String[] courseIds) {
		// TODO Auto-generated method stub
		
	}
}
